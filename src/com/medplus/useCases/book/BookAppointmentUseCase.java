package com.medplus.useCases.book;

import java.time.LocalDateTime;

import com.medplus.entities.domain.User;
import com.medplus.entities.scheduler.DayScheduler;
import com.medplus.useCases.AppointmentFactory;
import com.medplus.useCases.Bookable;
import com.medplus.useCases.SchedulePresenter;
import com.medplus.useCases.UserGateway;

public class BookAppointmentUseCase implements Bookable {
	private UserGateway patientGW, providerGW;
	private SchedulePresenter presenter;
	private DayScheduler dayScheduler;

	private User provider;
	private User patient;
	private AppointmentFactory factory;

	private LocalDateTime dateTime;

	@Override
	public void book(String providerID, String patientID, LocalDateTime dateTime) {
		this.dateTime = dateTime;
		if(appointmentIsInvalid(providerID, patientID) || !involvedPeopleAreNotBusy())
		{
			presenter.fail();
			return;
		}
		provider.getAppointments().add(
				factory.make()
					.withDateTime(dateTime)
					.withPeerID(patientID));

		patient.getAppointments().add(
				factory.make()
					.withDateTime(dateTime)
					.withPeerID(providerID));

		providerGW.put(provider);
		patientGW.put(patient);
		presenter.succeed();
	}

	private Boolean appointmentIsInvalid(String providerID, String patientID)
	{		
		return  dateTime == null ||
				providerID == null ||
				patientID == null ||
				providerWasNotFound(providerID) ||
				patientWasNotFound(patientID);
	}

	private boolean patientWasNotFound(String patientID) {
		patient = patientGW.getUser(patientID);
		return patient == null;
	}

	private Boolean involvedPeopleAreNotBusy()
	{
		return
			dayScheduler.isAvailable(dateTime, provider.getAppointments()) &&
			dayScheduler.isAvailable(dateTime, patient.getAppointments());
	}

	private Boolean providerWasNotFound(String providerID)
	{
		provider = providerGW.getUser(providerID);
		return provider == null;
	}
	// Setters

	public void setProviderGW(UserGateway providerGW) {
		this.providerGW = providerGW;
	}

	public void setPatientGW(UserGateway patientGW) {
		this.patientGW = patientGW;
	}

	public void setPresenter(SchedulePresenter presenter) {
		this.presenter = presenter;
	}

	public void setDaySchedule(DayScheduler dayScheduler) {
		this.dayScheduler = dayScheduler;
	}

	public void setAppointmentFactory(AppointmentFactory factory) {
		this.factory = factory;
	}

}
