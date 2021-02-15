package com.medplus.useCases;

import java.util.ArrayList;
import java.util.HashMap;

import com.medplus.entities.domain.Appointment;

public interface VerifyAppointmentsPresenter extends FailablePresenter{
	public void succeed(ArrayList<Appointment> list, HashMap<String, String> providers);
}
