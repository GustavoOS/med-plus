package com.medplus.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Canceler {
	protected Appointment canceled;

	public Boolean cancel(ArrayList<Appointment> appointments, LocalDateTime dateTime)
	{
		if(appointments == null || dateTime == null)
			return false;
		canceled = Utils.findFirstAppointmentWithDateTime(appointments, dateTime);
		appointments.remove(canceled);
		return canceled != null;
	}

	public abstract String getCanceledAppointmentUnknownID();
}
