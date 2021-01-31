package com.medplus.useCases;

import com.medplus.entities.Patient;

public class VerifyPatientAppointmentsUseCase implements VerifiableBook {
	private PatientGateway gw;
	private VerifyPatientAppointmentsPresenter presenter;

	@Override
	public void verify(String id) {
		Patient patient = gw.getPatient(id);
		if(patient == null)
		{
			presenter.fail();
			return;
		}
		presenter.succeed(patient.getAppointments());		
	}

	public void setGw(PatientGateway gw) {
		this.gw = gw;
	}

	public void setPresenter(VerifyPatientAppointmentsPresenter presenter) {
		this.presenter = presenter;
	}
	
}
