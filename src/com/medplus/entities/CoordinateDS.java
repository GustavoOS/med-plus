package com.medplus.entities;

public class CoordinateDS implements Coordinate {
	private double latitude = 0;
	private double longitude = 0;

	public void set(double lat, double lon)
	{
		latitude = lat;
		longitude = lon;
	}

	public double lat() {
		return latitude;
	}

	public double lon() {
		return longitude;
	}

	@Override
	public Coordinate with(double latitude, double longitude) {
		set(latitude, longitude);
		return this;
	}

	
}
