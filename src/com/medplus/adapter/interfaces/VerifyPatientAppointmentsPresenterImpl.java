package com.medplus.adapter.interfaces;

import java.util.ArrayList;

import com.medplus.entities.Appointment;
import com.medplus.useCases.VerifyPatientAppointmentsPresenter;

public class VerifyPatientAppointmentsPresenterImpl implements VerifyPatientAppointmentsPresenter {
	private String status = "";
	private ArrayList<Appointment> result = null;

	@Override
	public void fail() {
		status = "fail";
	}

	@Override
	public void succeed(ArrayList<Appointment> list) {
		status = "success";
		result = list;
	}

	public String getStatus() {
		return status;
	}

	public ArrayList<Appointment> getResult() {
		return result;
	}

	
}
