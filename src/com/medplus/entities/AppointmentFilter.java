package com.medplus.entities;

import java.util.ArrayList;

public interface AppointmentFilter {
	public ArrayList<Appointment> filter(ArrayList<Appointment> raw, Appointment param);
}
