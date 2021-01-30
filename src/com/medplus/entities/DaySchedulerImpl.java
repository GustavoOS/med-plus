package com.medplus.entities;

import java.util.ArrayList;

public class DaySchedulerImpl implements DayScheduler {

	private AppointmentFilter filter;
	private Appointment param;
	

	public void setFilter(AppointmentFilter filter) {
		this.filter = filter;
	}

	@Override
	public Boolean isAvailable(Appointment appointment, ArrayList<Appointment> appointments) {
		setParameter(appointment);
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
		ArrayList<Appointment> filtered = filter.filter(appointments, param);
		for (Appointment a : filtered)
			if(a.getDateTime().equals(appointment.getDateTime()))
				return true;
		return false;
	}

	private void setParameter(Appointment app) {
		param = Cloner.cloneAppointment(app);
		if(param != null) {
			param.setPatientID(null);
			param.setProviderID(null);
		}
	}
}
