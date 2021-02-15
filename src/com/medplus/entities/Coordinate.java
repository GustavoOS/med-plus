package com.medplus.entities;

public interface Coordinate {
	public void set(double lat, double lon);
	public Coordinate with(double latitude, double longitude);

	public double lat();
	public double lon();
}
