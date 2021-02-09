package com.medplus.adapter.interfaces;

import java.util.ArrayList;

import com.medplus.entities.Appointment;
import com.medplus.useCases.CancelAppointmentPresenter;

public class CancelAppointmentPresenterImpl implements CancelAppointmentPresenter {
	private String status;
	private ArrayList<Appointment> list;

	@Override
	public void fail() {
		status = "fail";
	}

	@Override
	public void succeed(ArrayList<Appointment> list) {
		status = "success";
		this.list = list;
	}

	public String getStatus() {
		return status;
	}

	public ArrayList<Appointment> getList() {
		return list;
	}

}
