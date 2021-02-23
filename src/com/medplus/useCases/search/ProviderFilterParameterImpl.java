package com.medplus.useCases.search;

import com.medplus.entities.domain.Coordinate;
import com.medplus.entities.provider.filter.ProviderFilterParameter;

public class ProviderFilterParameterImpl implements ProviderFilterParameter {

	private String specialization;
	private double distance = -1;
	private Coordinate reference;
	private String id;

	// Getters
	public String getSpecialization() {
		return specialization;
	}
	public double getDistance() {
		return distance;
	}
	public Coordinate getReference() {
		return reference;
	}
	public String getId() {
		return id;
	}

	// Withs
	public ProviderFilterParameter withSpecialization(String specialization) {
		this.specialization = specialization;
		return this;
	}

	public ProviderFilterParameter withDistance(double distance) {
		this.distance = distance;
		return this;
	}

	public ProviderFilterParameter withReference(Coordinate reference) {
		this.reference = reference;
		return this;
	}

	public ProviderFilterParameter withId(String id) {
		this.id = id;
		return this;
	}
}
