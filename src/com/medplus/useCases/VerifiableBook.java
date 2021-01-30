package com.medplus.useCases;
import java.time.LocalDateTime;

import com.medplus.entities.Appointment;


public interface VerifiableBook {
	public Appointment verify(String id, LocalDateTime time);
}
