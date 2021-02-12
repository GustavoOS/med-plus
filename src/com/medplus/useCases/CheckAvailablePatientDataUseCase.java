package com.medplus.useCases;

import com.medplus.entities.User;

public class CheckAvailablePatientDataUseCase implements CheckableAvailablePatientData {
	private UserGateway patientGateway;
	private User patient;
	private AttendancePresenter presenter;

	public void check(String patientID) {
		if(patientID == null || patientIsNull(patientID))
			presenter.fail();
		else
			presenter.succeed(PatientExtractor.extract(patient));
	}

	public void setPatientGateway(UserGateway gw) {
		this.patientGateway = gw;
	}

	public void setPresenter(AttendancePresenter presenter) {
		this.presenter = presenter;
	}

	private Boolean patientIsNull(String patientID) {
		patient = patientGateway.getUser(patientID);
		return patient == null;
	}
}
