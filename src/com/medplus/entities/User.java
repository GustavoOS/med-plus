package com.medplus.entities;

import java.util.ArrayList;

public interface User {
	public String getName();
	public void setName(String name);
	public ArrayList<Appointment> getAppointments();
	public void setAppointments(ArrayList<Appointment> appointments);
	public String getId();
	public void setId(String id);
}
