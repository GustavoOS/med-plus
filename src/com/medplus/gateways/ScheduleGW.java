package com.medplus.gateways;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.medplus.entities.AppointmentDS;
import com.medplus.useCases.ScheduleGateway;

public class ScheduleGW implements ScheduleGateway {

	private HashMap<String, ArrayList<AppointmentDS>> database =
			new HashMap<String,ArrayList<AppointmentDS>>();

	public ArrayList<AppointmentDS> getProviderSchedule(String providerId, LocalDate date){
		return database.get(generateKey(providerId, date));
	}

	public void setSchedule( String providerId,
								LocalDate date,
								ArrayList<AppointmentDS> appointments)
	{
		database.put(generateKey(providerId, date), appointments);
	}

	private String generateKey(String provider, LocalDate date)
	{
		return provider + date.toString();
	}

}
