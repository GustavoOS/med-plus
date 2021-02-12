package com.medplus.adapter.interfaces;

import com.medplus.useCases.RemoveExamUseCase;

public class RemoveExamController {
	private RemoveExamUseCase useCase;

	public void remove(String patient, String examID)
	{
		useCase.remove(patient, examID);
	}

	public void setUseCase(RemoveExamUseCase useCase) {
		this.useCase = useCase;
	}
}
