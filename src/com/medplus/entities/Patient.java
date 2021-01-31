package com.medplus.entities;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Patient{
	public LocalDate getBirth();
	public void setBirth(LocalDate birth);
	public Boolean getIsFemale();
	public void setIsFemale(Boolean isFemale);
	public String getId();
	public void setId(String id);
	public String getName();
	public void setName(String name);
	public ArrayList<Appointment> getAppointments();
	public void setAppointments(ArrayList<Appointment> appointments);
}
