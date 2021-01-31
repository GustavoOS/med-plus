package com.medplus.gateways;

import java.util.ArrayList;

import com.medplus.entities.Cloner;
import com.medplus.entities.Patient;
import com.medplus.useCases.PatientGateway;

public class PatientGW implements PatientGateway {
	private ArrayList<Patient> patients;

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	@Override
	public void put(Patient patient) {
		int index = patients.indexOf(filterPatient(patient.getId()));
		if(index < 0)
			patients.add(patient);
		else
			patients.set(index, patient);		
	}

	@Override
	public Patient getPatient(String id)
	{
		return Cloner.clonePatient(filterPatient(id));
	}

	Patient filterPatient(String id) {
		return patients.stream()
				  .filter(patient -> patient.getId().equals(id))
				  .findAny()
				  .orElse(null);
	}

}
