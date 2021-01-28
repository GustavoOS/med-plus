package com.medplus.adapter.interfaces;
import java.time.LocalDateTime;

import com.medplus.entities.AppointmentDS;
import com.medplus.useCases.Bookable;

public class BookController {
	private Bookable useCase;
	private AppointmentDS appointment;

	void schedule()
	{
		useCase.book(appointment);
	}

	// Setters
	void setAppointment(String patientId,String providerId, LocalDateTime dateTime)
	{
		appointment = new AppointmentDS();
		appointment.setPatientID(patientId);
		appointment.setProviderID(providerId);
		appointment.setDateTime(dateTime);
	}

	public void setUseCase(Bookable useCase) {
		this.useCase = useCase;
	}


	
	
}
