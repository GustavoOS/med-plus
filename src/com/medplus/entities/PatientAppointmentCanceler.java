package com.medplus.entities;

public class PatientAppointmentCanceler extends Canceler {

	@Override
	public String getCanceledAppointmentTargetUserID() {
		return canceled == null ? null : canceled.getProviderID();
	}

}
