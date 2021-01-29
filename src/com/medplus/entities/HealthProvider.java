package com.medplus.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public interface HealthProvider {
	public String getSocialMediaURL();
	public String getName();
	public String getSpecialization();
	public CoordinateDS getLocal();
	public String getId();
	public HashMap<LocalDate, ArrayList<Appointment>> getAppointments();

	public void setLocal(CoordinateDS local);
	public void setSocialMediaURL(String url);
	public void setName(String name);
	public void setSpecialization(String specialization);
	public void setId(String id);
	public void setAppointments(HashMap<LocalDate, ArrayList<Appointment>> appointments) ;

	HealthProvider clone();
}
