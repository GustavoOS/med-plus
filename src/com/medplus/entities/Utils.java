package com.medplus.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import com.medplus.entities.coordinate.CoordinateDS;

public class Utils {
	public static double calculateDistance(Coordinate pointA, Coordinate pointB) {
		Coordinate aRad, bRad, delta;
		aRad = (new CoordinateDS()).with(Math.toRadians(pointA.lat()), Math.toRadians(pointA.lon()));
		bRad = (new CoordinateDS()).with(Math.toRadians(pointB.lat()), Math.toRadians(pointB.lon()));

		// Haversine formula  
		delta = (new CoordinateDS()).with(bRad.lat() - aRad.lat(), bRad.lon() - aRad.lon());
		double a = Math.pow(Math.sin(delta.lat() / 2), 2)
				+ Math.cos(aRad.lat())
				* Math.cos(bRad.lat())
				* Math.pow(Math.sin(delta.lon() / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371;

		// calculate the result 
		return (c * r);
	}

	public static int calculateAge(LocalDate birth)
	{
		if(birth == null)
			return 0;
		return Period.between(birth, LocalDate.now()).getYears();
	}

	public static Appointment findFirstAppointmentWithDateTime(ArrayList<Appointment> appointments, LocalDateTime dateTime)
	{
		if(appointments == null || dateTime == null)
			return null;
		for (Appointment ap : appointments) {
			if(getComparableDateTime(ap.getDateTime())
					.equals(getComparableDateTime(dateTime)))
				return ap;
		}
		return null;
	}

	private static LocalDateTime getComparableDateTime(LocalDateTime a)
	{
		return a.withNano(0).withSecond(0).withMinute(0);
	}
}
