package com.medplus.entities;

import java.util.ArrayList;

public interface DayScheduler {
	public Boolean isAvailable(Appointment appointment,ArrayList<Appointment> appointments);
}
