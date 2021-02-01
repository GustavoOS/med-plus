package com.medplus.useCases;

import com.medplus.entities.Appointment;
import com.medplus.entities.DayScheduler;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Patient;

public class BookAppointmentUseCase implements Bookable {
	private PatientGateway patientGW;
	private ProviderGateway providerGW;
	private SchedulePresenter presenter;
	private DayScheduler dayScheduler;

	private HealthProvider provider;
	private Patient patient;
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
		patient = patientGW.getPatient(appointment.getPatientID());
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
		provider = providerGW.getProvider(appointment.getProviderID());
		return provider == null;
	}
	// Setters

	public void setProviderGW(ProviderGateway providerGW) {
		this.providerGW = providerGW;
	}

	public void setPatientGW(PatientGateway patientGW) {
		this.patientGW = patientGW;
	}

	public void setPresenter(SchedulePresenter presenter) {
		this.presenter = presenter;
	}

	public void setDaySchedule(DayScheduler dayScheduler) {
		this.dayScheduler = dayScheduler;
	}

}
