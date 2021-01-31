package com.medplus.useCases;

import com.medplus.entities.Patient;

public interface PatientGateway {
	public Patient getPatient(String id);
	public void put(Patient patient);
}
