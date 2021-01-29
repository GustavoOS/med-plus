package com.medplus.entities;

import java.time.LocalDateTime;

public class AppointmentImpl implements Appointment {
	private String patientID, providerId;
	private LocalDateTime dateTime;

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getProviderID() {
		return providerId;
	}

	public void setProviderID(String doctorID) {
		this.providerId = doctorID;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
}
