package com.medplus.entities.appointment;

import java.time.LocalDateTime;

import com.medplus.entities.domain.Appointment;

public class AppointmentImpl implements Appointment {
	private String peerID;
	private LocalDateTime dateTime;

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getPeerID() {
		return peerID;
	}

	public void setPeerID(String peerID) {
		this.peerID = peerID;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public Appointment withDateTime(LocalDateTime dateTime) {
		setDateTime(dateTime);
		return this;
	}

	@Override
	public Appointment withPeerID(String patientID) {
		setPeerID(patientID);
		return this;
	}

}
