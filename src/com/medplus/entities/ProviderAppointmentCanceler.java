package com.medplus.entities;

public class ProviderAppointmentCanceler extends Canceler {

	@Override
	public String getCanceledAppointmentTargetUserID() {
		return canceled == null ? null : canceled.getPatientID();
	}

}
