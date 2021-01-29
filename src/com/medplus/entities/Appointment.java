package com.medplus.entities;

import java.time.LocalDateTime;

public interface Appointment{
	//Getters
	public String getPatientID();
	public String getProviderID();
	public LocalDateTime getDateTime();

	//Setters
	public void setDateTime(LocalDateTime dateTime);
	public void setPatientID(String patientID);
	public void setProviderID(String doctorID);
}
