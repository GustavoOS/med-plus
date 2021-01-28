package com.medplus.adapter.interfaces;
import java.time.LocalDateTime;

import com.medplus.entities.AppointmentDS;
import com.medplus.useCases.Bookable;

public class BookController {
	private Bookable useCase;
	private AppointmentDS appointment;
	private LocalDateTime dateTime;

	void schedule()
	{
		useCase.book(appointment, dateTime);
	}

	// Setters
	void setAppointment(String patientId,String providerId)
	{
		appointment = new AppointmentDS();
		appointment.setPatientID(patientId);
		appointment.setProviderID(providerId);
	}

	void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public void setUseCase(Bookable useCase) {
		this.useCase = useCase;
	}


	
	
}