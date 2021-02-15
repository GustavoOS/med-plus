package com.medplus.useCases;

import java.time.LocalDateTime;

import com.medplus.entities.DayScheduler;

public interface Bookable {
	void book(String providerID, String patientID, LocalDateTime dateTime);

	// Setters
	void setAppointmentFactory(AppointmentFactory factory);
	public void setProviderGW(UserGateway providerGW);
	public void setPatientGW(UserGateway patientGW);
	public void setPresenter(SchedulePresenter presenter);
	public void setDaySchedule(DayScheduler dayScheduler);
}
