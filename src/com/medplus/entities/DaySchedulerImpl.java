package com.medplus.entities;

import java.util.ArrayList;

public class DaySchedulerImpl implements DayScheduler {

	@Override
	public Boolean isAvailable(Appointment appointment, ArrayList<Appointment> appointments) {
		return !isInvalid(appointment, appointments);
	}

	private boolean isInvalid(Appointment appointment, ArrayList<Appointment> appointments) {
		return  appointments == null ||
				appointment == null ||
				appointment.getDateTime() == null ||
				hasConflictingAppointment(appointment, appointments) ||
				hourIsOutOfBusinessTime(appointment.getDateTime().getHour())
				;
	}

	private Boolean hourIsOutOfBusinessTime(int hour) {
		return hour > 17 || hour < 9 || hour == 12;
	}

	private Boolean hasConflictingAppointment(Appointment appointment, ArrayList<Appointment> appointments)
	{
		Appointment conflict = Utils.findFirstAppointmentWithDateTime(appointments, appointment.getDateTime());
		return conflict != null;
	}


}
