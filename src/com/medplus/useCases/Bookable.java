package com.medplus.useCases;

import com.medplus.entities.AppointmentDS;

public interface Bookable {
	void book(AppointmentDS appointment);
}
