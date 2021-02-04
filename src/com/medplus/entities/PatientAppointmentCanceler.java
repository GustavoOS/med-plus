package com.medplus.entities;

public class PatientAppointmentCanceler extends Canceler {

	@Override
	public String getCanceledAppointmentUnknownID() {
		return canceled == null ? null : canceled.getProviderID();
	}

}
