package com.medplus.adapter.interfaces;

import java.time.LocalDateTime;

import com.medplus.useCases.Attendable;

public class AttendanceController {
	private Attendable useCase;

	public void attend(String providerId, LocalDateTime dateTime)
	{
		useCase.attend(providerId, dateTime);
	}

	public void setUseCase(Attendable useCase) {
		this.useCase = useCase;
	}
}
