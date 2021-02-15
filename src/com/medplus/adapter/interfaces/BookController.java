package com.medplus.adapter.interfaces;
import java.time.LocalDateTime;

import com.medplus.useCases.Bookable;

public class BookController {
	private Bookable useCase;

	public void setAppointment(String patientID, String providerID, LocalDateTime dateTime)
	{
		useCase.book(providerID, patientID, dateTime);
	}

	public void setUseCase(Bookable useCase) {
		this.useCase = useCase;
	}
}
