package com.medplus.useCases.book;

import com.medplus.entities.Appointment;
import com.medplus.entities.DayScheduler;
import com.medplus.entities.User;
import com.medplus.useCases.Bookable;
import com.medplus.useCases.ProviderGateway;
import com.medplus.useCases.SchedulePresenter;
import com.medplus.useCases.UserGateway;

public class BookAppointmentUseCase implements Bookable {
	private UserGateway patientGW;
	private ProviderGateway providerGW;
	private SchedulePresenter presenter;
	private DayScheduler dayScheduler;

	private User provider;
	private User patient;
	private Appointment appointment;

	@Override
	public void book(Appointment a) {
		appointment = a;
		if(appointmentIsInvalid() || !involvedPeopleAreNotBusy())
		{
			presenter.fail();
			return;
		}
		provider.getAppointments().add(appointment);
		patient.getAppointments().add(appointment);
		providerGW.put(provider);
		patientGW.put(patient);
		presenter.succeed();
	}

	private Boolean appointmentIsInvalid()
	{		
		return  appointment.getDateTime() == null ||
				appointment.getPatientID() == null ||
				appointment.getProviderID() == null ||
				providerWasNotFound() ||
				patientWasNotFound();
	}

	private boolean patientWasNotFound() {
		patient = patientGW.getUser(appointment.getPatientID());
		return patient == null;
	}

	private Boolean involvedPeopleAreNotBusy()
	{
		return
			dayScheduler.isAvailable(appointment, provider.getAppointments()) &&
			dayScheduler.isAvailable(appointment, patient.getAppointments());
	}

	private Boolean providerWasNotFound()
	{
		provider = providerGW.getUser(appointment.getProviderID());
		return provider == null;
	}
	// Setters

	public void setProviderGW(ProviderGateway providerGW) {
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

}
