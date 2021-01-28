package com.medplus.entities;
import java.util.ArrayList;
import java.util.Arrays;

public class BusinessDay {
	private AppointmentDS[] day = new AppointmentDS[8];
	private int morningStartHour = 9;
	private int afternoonStartHour = 13;
	private int morningEndHour = 12;
	private int afternoonEndHour = 18;

	public Boolean isAvailable(int hour)
	{
		return (isValidMorningHour(hour) || isValidAfternoonHour(hour)) &&
				(day[hourToIndex(hour)] == null);
	}

	public void scheduleAppointment(AppointmentDS appointment)
	{
		int hour = appointment.getDateTime().getHour();
		if(isAvailable(hour))
			day[hourToIndex(hour)] = appointment;
	}

	private int hourToIndex(int hour)
	{
		return (hour < morningEndHour) ? hour - morningStartHour :
				hour - afternoonStartHour + (morningEndHour - morningStartHour);
	}

	private Boolean isValidMorningHour(int hour)
	{
		return (hour < morningEndHour) && (hour >= morningStartHour);
	}

	private Boolean isValidAfternoonHour(int hour)
	{
		return (hour > morningEndHour) && (hour < afternoonEndHour);
	}

	public AppointmentDS[] getDay() {
		return day;
	}

	public ArrayList<AppointmentDS> getDayAsList()
	{
		return new ArrayList<AppointmentDS> (Arrays.asList(day));
	}

	public void setDay(ArrayList<AppointmentDS> appointments)
	{
		if(appointments == null)
			appointments = new ArrayList<AppointmentDS>();
		appointments.toArray(day);
	}

}
