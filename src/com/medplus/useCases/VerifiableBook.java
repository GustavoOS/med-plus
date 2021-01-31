package com.medplus.useCases;


public interface VerifiableBook {
	public void verify(String id);
	public void setPresenter(VerifyPatientAppointmentsPresenter presenter);
}
