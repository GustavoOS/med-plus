package com.medplus.entities;

import java.util.ArrayList;

public interface DaySchedule {

	public Boolean isAvailable(int hour);
	public void scheduleAppointment(Appointment appointment);

	// Getters and Setters
	public Appointment[] getDay();
	public ArrayList<Appointment> getDayAsList();
	public void setDay(ArrayList<Appointment> appointments);
}
