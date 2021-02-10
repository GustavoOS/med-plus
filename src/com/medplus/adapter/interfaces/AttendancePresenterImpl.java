package com.medplus.adapter.interfaces;

import com.medplus.useCases.AttendancePresenter;
import com.medplus.useCases.PatientAvailableDataDS;

public class AttendancePresenterImpl implements AttendancePresenter {
	private String status;
	private PatientAvailableDataDS data;

	@Override
	public void fail() {
		status = "fail";
	}

	@Override
	public void succeed(PatientAvailableDataDS data) {
		status = "success";
		this.data = data;
	}

	public PatientAvailableDataDS getData()
	{
		return data;
	}

	public String getStatus()
	{
		return status;
	}
}
