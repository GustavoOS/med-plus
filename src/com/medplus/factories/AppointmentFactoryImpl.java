package com.medplus.factories;

import com.medplus.entities.Appointment;
import com.medplus.entities.AppointmentImpl;

public class AppointmentFactoryImpl implements AppointmentFactory {

	@Override
	public Appointment make() {
		return new AppointmentImpl();
	}

}
