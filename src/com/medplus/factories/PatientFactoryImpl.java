package com.medplus.factories;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.patient.PatientImpl;

public class PatientFactoryImpl implements PatientFactory {

	@Override
	public Patient make() {
		return new PatientImpl();
	}

}
