package com.medplus.useCases;

public interface CheckableAvailablePatientData {
	public void check(String patientID);
	public void setPatientGateway(UserGateway gw);
	public void setPresenter(AttendancePresenter presenter);
}
