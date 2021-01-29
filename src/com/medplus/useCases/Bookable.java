package com.medplus.useCases;

import com.medplus.entities.Appointment;

public interface Bookable {
	void book(Appointment appointment);
}
