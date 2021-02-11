package com.medplus.adapter.interfaces;

import com.medplus.useCases.AddExamUseCase;

public class AddExamController {
	AddExamUseCase useCase;

	public void add(String patientID, String fileID, String title)
	{
		useCase.add(patientID, title, fileID);
	}

	public void setUseCase(AddExamUseCase useCase) {
		this.useCase = useCase;
	}
}
