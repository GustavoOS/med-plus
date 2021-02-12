package com.medplus.useCases;

public interface AttendancePresenter extends FailablePresenter {
	public void succeed(PatientAvailableDataDS data);
	public String getStatus();
	public PatientAvailableDataDS getData();
}
