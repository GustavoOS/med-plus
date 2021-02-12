package com.medplus.useCases;

import com.medplus.entities.Patient;
import com.medplus.entities.User;
import com.medplus.entities.Utils;

public class PatientExtractor {
	public static PatientAvailableDataDS extract(User _patient)
	{
		Patient patient = (Patient) _patient;
		return (new PatientAvailableDataDS())
				.withAge(Utils.calculateAge(patient.getBirth()))
				.withName(patient.getName())
				.withIsFemale(patient.getIsFemale())
				.withExams(patient.getExams());
	}
}
