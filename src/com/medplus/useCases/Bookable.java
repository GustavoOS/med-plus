package com.medplus.useCases;
import java.time.LocalDateTime;

import com.medplus.entities.AppointmentDS;

public interface Bookable {
	void book(AppointmentDS appointment, LocalDateTime date);
}
