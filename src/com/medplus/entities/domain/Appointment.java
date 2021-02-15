package com.medplus.entities.domain;

import java.time.LocalDateTime;

public interface Appointment{
	//Getters
	public String getPeerID();
	public LocalDateTime getDateTime();

	//Setters
	public void setDateTime(LocalDateTime dateTime);
	public void setPeerID(String patientID);

	//Withs
	public Appointment withDateTime(LocalDateTime dateTime);
	public Appointment withPeerID(String patientID);
}
