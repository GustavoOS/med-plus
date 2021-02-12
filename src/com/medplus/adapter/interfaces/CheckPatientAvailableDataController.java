package com.medplus.adapter.interfaces;

import com.medplus.useCases.CheckableAvailablePatientData;

public class CheckPatientAvailableDataController {
	private CheckableAvailablePatientData useCase;

	public void check(String patientID)
	{
		useCase.check(patientID);
	}


	public void setUseCase(CheckableAvailablePatientData useCase) {
		this.useCase = useCase;
	}

	
}
