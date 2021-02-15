package com.medplus.factories;

import com.medplus.entities.Appointment;
import com.medplus.entities.AppointmentImpl;
import com.medplus.useCases.AppointmentFactory;

public class AppointmentFactoryImpl implements AppointmentFactory {

	@Override
	public Appointment make() {
		return new AppointmentImpl();
	}

}
