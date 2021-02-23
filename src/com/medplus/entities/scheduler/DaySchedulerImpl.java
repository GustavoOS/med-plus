package com.medplus.entities.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.medplus.entities.Utils;
import com.medplus.entities.domain.Appointment;

public class DaySchedulerImpl implements DayScheduler {

	@Override
	public Boolean isAvailable(LocalDateTime dateTime, ArrayList<Appointment> appointments) {
		return !isInvalid(dateTime, appointments);
	}

	private boolean isInvalid(LocalDateTime dateTime, ArrayList<Appointment> appointments) {
		return  appointments == null ||
				dateTime == null ||
				hasConflictingAppointment(dateTime, appointments) ||
				hourIsOutOfBusinessTime(dateTime.getHour())
				;
	}

	private Boolean hourIsOutOfBusinessTime(int hour) {
		return hour > 17 || hour < 9 || hour == 12;
	}

	private Boolean hasConflictingAppointment(LocalDateTime dateTime, ArrayList<Appointment> appointments)
	{
		Appointment conflict = Utils.findFirstAppointmentWithDateTime(appointments, dateTime);
		return conflict != null;
	}


}
