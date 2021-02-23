package com.medplus.entities.provider.filter;

import com.medplus.entities.domain.Coordinate;

public interface ProviderFilterParameter {

	public String getSpecialization();
	public ProviderFilterParameter withSpecialization(String specialization);
	public double getDistance();
	public ProviderFilterParameter withDistance(double distance);
	public Coordinate getReference();
	public ProviderFilterParameter withReference(Coordinate reference);
	public String getId();
	public ProviderFilterParameter withId(String id);
	
}
