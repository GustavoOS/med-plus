package com.medplus.entities;

import java.util.ArrayList;

public interface HealthProvider {
	public String getSocialMediaURL();
	public String getName();
	public String getSpecialization();
	public CoordinateDS getLocal();
	public String getId();
	public ArrayList<Appointment> getAppointments();

	public void setLocal(CoordinateDS local);
	public void setSocialMediaURL(String url);
	public void setName(String name);
	public void setSpecialization(String specialization);
	public void setId(String id);
	public void setAppointments(ArrayList<Appointment> appointments) ;
}
