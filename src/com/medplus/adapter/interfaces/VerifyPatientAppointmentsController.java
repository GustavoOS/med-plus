package com.medplus.adapter.interfaces;

import com.medplus.useCases.VerifiableBook;

public class VerifyPatientAppointmentsController {
	private VerifiableBook useCase;

	public void setUseCase(VerifiableBook useCase) {
		this.useCase = useCase;
	}

	public void verify(String patientID)
	{
		useCase.verify(patientID);
	}


}
