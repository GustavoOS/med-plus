package com.medplus.useCases;
import java.time.LocalDate;
import java.util.ArrayList;

import com.medplus.entities.AppointmentDS;

public interface ScheduleGateway {
	public ArrayList<AppointmentDS> getProviderSchedule(String providerId, LocalDate date);
	public void setSchedule(String providerId, LocalDate date, ArrayList<AppointmentDS> appointments);
}
