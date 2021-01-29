package com.medplus.useCases;
import java.time.LocalDate;
import java.util.ArrayList;

import com.medplus.entities.Appointment;

public interface ScheduleGateway {
	public ArrayList<Appointment> getProviderSchedule(String providerId, LocalDate date);
	public void setSchedule(String providerId, LocalDate date, ArrayList<Appointment> appointments);
}
