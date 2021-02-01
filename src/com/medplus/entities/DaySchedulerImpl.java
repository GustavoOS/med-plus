package com.medplus.entities;

import java.time.LocalDateTime;
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
				hourIsOutOfBusinessTime(getComparableDateTime(appointment).getHour())
				;
	}

	private Boolean hourIsOutOfBusinessTime(int hour) {
		return hour > 17 || hour < 9 || hour == 12;
	}

	private Boolean hasConflictingAppointments(Appointment appointment, ArrayList<Appointment> appointments) {
		ArrayList<Appointment> filtered = (ArrayList<Appointment>) appointments.stream()
				  .filter(ap -> getComparableDateTime(ap).equals(getComparableDateTime(appointment)))
				  .collect(Collectors.toList());
		return filtered.size() > 0;
	}

	private LocalDateTime getComparableDateTime(Appointment a)
	{
		return a.getDateTime().withNano(0).withMinute(0);
	}
}
