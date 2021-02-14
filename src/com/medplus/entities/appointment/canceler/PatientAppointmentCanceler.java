package com.medplus.entities.appointment.canceler;

import com.medplus.entities.Canceler;

public class PatientAppointmentCanceler extends Canceler {

	@Override
	public String getCanceledAppointmentTargetUserID() {
		return canceled == null ? null : canceled.getProviderID();
	}

}