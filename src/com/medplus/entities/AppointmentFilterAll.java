package com.medplus.entities;

import java.util.ArrayList;

public class AppointmentFilterAll implements AppointmentFilter {

	@Override
	public ArrayList<Appointment> filter(ArrayList<Appointment> raw, Appointment param) {
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		for (Appointment appointment : raw) {
			if(fitsParameter(appointment, param))
				result.add(appointment);
		}
		return result;
	}

	private Boolean fitsParameter(Appointment appointment, Appointment param)
	{
		return (param == null) || (appointment == null) || 
				(
						providerIDsMatch(appointment, param) &&
						patientIDsMatch(appointment, param) &&
						datesMatch(appointment, param)
				);
	}


	private boolean providerIDsMatch(Appointment appointment, Appointment param) {
		return param.getProviderID() == null || param.getProviderID().equals(appointment.getProviderID());
	}

	private boolean patientIDsMatch(Appointment appointment, Appointment param) {
		return param.getPatientID() == null || param.getPatientID().equals(appointment.getPatientID());
	}

	private boolean datesMatch(Appointment appointment, Appointment param) {
		return param.getDateTime() == null ||
				param.getDateTime().toLocalDate().equals(appointment.getDateTime().toLocalDate());
	}


}
