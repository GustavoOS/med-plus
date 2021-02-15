package com.medplus.entities;


public interface HealthProvider extends User {
	public String getSocialMediaURL();
	public String getSpecialization();
	public Coordinate getLocal();

	public void setLocal(Coordinate local);
	public void setSocialMediaURL(String url);
	public void setSpecialization(String specialization);
}
