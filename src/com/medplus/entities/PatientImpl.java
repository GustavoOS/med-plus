package com.medplus.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientImpl implements Patient{
	private String name;
	private String id;

	private LocalDate birth;
	private Boolean isFemale;

	private ArrayList<Appointment> appointments;
	private ArrayList<Exam> exams;

	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public Boolean getIsFemale() {
		return isFemale;
	}
	public void setIsFemale(Boolean isFemale) {
		this.isFemale = isFemale;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	public ArrayList<Exam> getExams() {
		return exams;
	}
	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

}