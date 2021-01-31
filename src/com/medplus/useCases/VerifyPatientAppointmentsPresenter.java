package com.medplus.useCases;

import java.util.ArrayList;

import com.medplus.entities.Appointment;

public interface VerifyPatientAppointmentsPresenter extends FailablePresenter{
	public void succeed(ArrayList<Appointment> list);
}
