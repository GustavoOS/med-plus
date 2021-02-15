package com.medplus.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.medplus.entities.domain.Appointment;

public interface DayScheduler {
	public Boolean isAvailable(LocalDateTime dateTime,ArrayList<Appointment> appointments);
}
