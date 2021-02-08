package com.medplus.entities;


public interface HealthProvider extends User {
	public String getSocialMediaURL();
	public String getSpecialization();
	public CoordinateDS getLocal();

	public void setLocal(CoordinateDS local);
	public void setSocialMediaURL(String url);
	public void setSpecialization(String specialization);
}
