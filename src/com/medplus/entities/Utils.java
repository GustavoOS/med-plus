package com.medplus.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Utils {
	public static double calculateDistance(CoordinateDS pointA, CoordinateDS pointB) {
		CoordinateDS aRad, bRad, delta;
		aRad = new CoordinateDS(Math.toRadians(pointA.latitude), Math.toRadians(pointA.longitude));
		bRad = new CoordinateDS(Math.toRadians(pointB.latitude), Math.toRadians(pointB.longitude));

		// Haversine formula  
		delta = new CoordinateDS(bRad.latitude - aRad.latitude, bRad.longitude - aRad.longitude);
		double a = Math.pow(Math.sin(delta.latitude / 2), 2)
				+ Math.cos(aRad.latitude)
				* Math.cos(bRad.latitude)
				* Math.pow(Math.sin(delta.longitude / 2), 2);

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
		return (appointments == null || dateTime == null)? null : appointments.stream()
				  .filter(ap -> getComparableDateTime(ap.getDateTime()).equals(getComparableDateTime(dateTime)))
				  .findAny()
				  .orElse(null);
	}

	private static LocalDateTime getComparableDateTime(LocalDateTime a)
	{
		return a.withNano(0).withSecond(0).withMinute(0);
	}
}
