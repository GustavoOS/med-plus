package com.medplus.adapter.interfaces;
import java.time.LocalDateTime;

import com.medplus.entities.Appointment;
import com.medplus.factories.AppointmentFactory;
import com.medplus.useCases.Bookable;

public class BookController {
	private Bookable useCase;
	private Appointment appointment;
	private AppointmentFactory appointmentFactory;

	void schedule()
	{
		useCase.book(appointment);
	}

	// Setters
	void setAppointment(String patientId,String providerId, LocalDateTime dateTime)
	{
		appointment = appointmentFactory.make();
		appointment.setPatientID(patientId);
		appointment.setProviderID(providerId);
		appointment.setDateTime(dateTime);
	}

	public void setUseCase(Bookable useCase) {
		this.useCase = useCase;
	}


	public void setAppointmentFactory(AppointmentFactory appointmentFactory) {
		this.appointmentFactory = appointmentFactory;
	}
	
}
