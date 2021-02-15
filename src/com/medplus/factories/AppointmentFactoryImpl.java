package com.medplus.factories;

import com.medplus.entities.appointment.AppointmentImpl;
import com.medplus.entities.domain.Appointment;
import com.medplus.useCases.AppointmentFactory;

public class AppointmentFactoryImpl implements AppointmentFactory {

	@Override
	public Appointment make() {
		return new AppointmentImpl();
	}

}
