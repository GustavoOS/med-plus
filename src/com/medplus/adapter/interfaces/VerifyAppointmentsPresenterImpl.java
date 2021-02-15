package com.medplus.adapter.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.medplus.entities.domain.Appointment;
import com.medplus.useCases.VerifyAppointmentsPresenter;

public class VerifyAppointmentsPresenterImpl implements VerifyAppointmentsPresenter {
	private String status = "";
	private ArrayList<Appointment> result = null;
	private HashMap<String, String> names;

	@Override
	public void fail() {
		status = "fail";
	}

	@Override
	public void succeed(ArrayList<Appointment> list, HashMap<String, String> nameList) {
		status = "success";
		result = list;
		names = nameList;
	}

	public String getStatus() {
		return status;
	}

	public ArrayList<Appointment> getResult() {
		return result;
	}

	public HashMap<String, String> getNames() {
		return names;
	}
	
}
