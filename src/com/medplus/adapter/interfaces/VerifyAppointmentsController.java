package com.medplus.adapter.interfaces;

import com.medplus.useCases.VerifiableBook;

public class VerifyAppointmentsController {
	private VerifiableBook useCase;

	public void setUseCase(VerifiableBook useCase) {
		this.useCase = useCase;
	}

	public void verify(String id)
	{
		useCase.verify(id);
	}


}
