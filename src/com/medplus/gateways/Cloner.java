package com.medplus.gateways;

import java.util.ArrayList;

import com.medplus.entities.domain.Appointment;
import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.impl.ExamImpl;
import com.medplus.entities.patient.PatientImpl;
import com.medplus.entities.provider.impl.Provider;
import com.medplus.factories.AppointmentFactoryImpl;

public class Cloner {
	public static HealthProvider cloneProvider(HealthProvider original)
	{
		if(original == null)
			return null;
		HealthProvider p = new Provider();
		p.setLocal(original.getLocal());
		p.setName(original.getName());
		p.setSocialMediaURL(original.getSocialMediaURL());
		p.setSpecialization(original.getSpecialization());
		p.setId(original.getId());
		p.setAppointments(cloneAppointmentList(original.getAppointments()));
		return p;
	}

	public static Appointment cloneAppointment(Appointment original)
	{
		if(original == null)
			return null;
		Appointment a = (new AppointmentFactoryImpl()).make();
		a.setDateTime(original.getDateTime());
		a.setPeerID(original.getPeerID());
		return a;
	}

	static ArrayList<Appointment> cloneAppointmentList(ArrayList<Appointment> original)
	{
		if(original == null)
			return null;
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		for (Appointment appointment : original) {
			list.add(cloneAppointment(appointment));
		}
		return list;
	}

	public static Exam cloneExam(Exam original)
	{
		if(original == null)
			return null;

		return (new ExamImpl())
				.withId(original.getId())
				.withInsertionDateTime(original.getInsertionDateTime())
				.withTitle(original.getTitle());
	}

	public static ArrayList<Exam> cloneExamList(ArrayList<Exam> original)
	{
		if(original == null)
			return null;
		ArrayList<Exam> copy = new ArrayList<Exam>();
		for (Exam exam : original)
			copy.add(cloneExam(exam));
		return copy;
	}

	public static ArrayList<HealthProvider> cloneProviderList(ArrayList<HealthProvider> original)
	{
		if(original == null)
			return null;
		ArrayList<HealthProvider> result = new ArrayList<HealthProvider>();
		for (HealthProvider healthProvider : original) 
			result.add(cloneProvider(healthProvider));
		return result;		
	}

	public static Patient clonePatient(Patient original)
	{
		if(original == null)
			return null;
		Patient p = new PatientImpl();
		p.setAppointments(cloneAppointmentList(original.getAppointments()));
		p.setBirth(original.getBirth());
		p.setId(original.getId());
		p.setIsFemale(original.getIsFemale());
		p.setName(original.getName());
		p.setExams(cloneExamList(original.getExams()));
		return p;
	}
}
