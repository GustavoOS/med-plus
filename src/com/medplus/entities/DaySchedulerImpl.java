package com.medplus.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DaySchedulerImpl implements DayScheduler {

	@Override
	public Boolean isAvailable(Appointment appointment, ArrayList<Appointment> appointments) {
		return !isInvalid(appointment, appointments);
	}

	private boolean isInvalid(Appointment appointment, ArrayList<Appointment> appointments) {
		return  appointments == null ||
				appointment == null ||
				appointment.getDateTime() == null ||
				hasConflictingAppointments(appointment, appointments) ||
				hourIsOutOfBusinessTime(appointment.getDateTime().getHour())
				;
	}

	private Boolean hourIsOutOfBusinessTime(int hour) {
		return hour > 17 || hour < 9 || hour == 12;
	}

	private Boolean hasConflictingAppointments(Appointment appointment, ArrayList<Appointment> appointments) {
		ArrayList<Appointment> filtered = (ArrayList<Appointment>) appointments.stream()
				  .filter(ap -> ap.getDateTime().equals(appointment.getDateTime()))
				  .collect(Collectors.toList());
		return filtered.size() > 0;
	}
}
