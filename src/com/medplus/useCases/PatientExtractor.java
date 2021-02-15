package com.medplus.useCases;

import com.medplus.entities.Utils;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.domain.User;

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
