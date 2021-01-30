package com.medplus.entities;

import java.util.ArrayList;

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
		a.setPatientID(original.getPatientID());
		a.setProviderID(original.getProviderID());
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

	public static ArrayList<HealthProvider> cloneProviderList(ArrayList<HealthProvider> original)
	{
		if(original == null)
			return null;
		ArrayList<HealthProvider> result = new ArrayList<HealthProvider>();
		for (HealthProvider healthProvider : original) 
			result.add(cloneProvider(healthProvider));
		return result;		
	}
}
