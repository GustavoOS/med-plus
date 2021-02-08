package com.medplus.gateways;

import java.util.ArrayList;

import com.medplus.entities.Cloner;
import com.medplus.entities.Patient;
import com.medplus.entities.User;
import com.medplus.useCases.UserGateway;

public class PatientGW implements UserGateway {
	private ArrayList<Patient> patients;

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	@Override
	public void put(User patient) {
		Patient p = (Patient) patient;
		int index = patients.indexOf(filterPatient(p.getId()));
		if(index < 0)
			patients.add(p);
		else
			patients.set(index, p);		
	}

	@Override
	public User getUser(String id)
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
