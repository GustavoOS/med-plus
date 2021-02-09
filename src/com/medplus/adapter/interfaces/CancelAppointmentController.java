package com.medplus.adapter.interfaces;

import java.time.LocalDateTime;

import com.medplus.useCases.Cancelable;

public class CancelAppointmentController {
	private Cancelable useCase;

	void cancel(String id, LocalDateTime dateTime)
	{
		useCase.cancel(id, dateTime);
	}

	public void setUseCase(Cancelable useCase) {
		this.useCase = useCase;
	}
	
}
