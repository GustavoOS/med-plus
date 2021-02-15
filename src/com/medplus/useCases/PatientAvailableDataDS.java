package com.medplus.useCases;

import java.util.ArrayList;

import com.medplus.entities.domain.Exam;

public class PatientAvailableDataDS {
	private String name;
	private int age;
	private Boolean isFemale;
	private ArrayList<Exam> exams;

	//Getters
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Boolean getIsFemale() {
		return isFemale;
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}


	//Withs
	 public PatientAvailableDataDS withName(String name) {
		this.name = name;
		return this;
	}

	public PatientAvailableDataDS withAge(int age) {
		this.age = age;
		return this;
	}

	public PatientAvailableDataDS withIsFemale(Boolean isFemale) {
		this.isFemale = isFemale;
		return this;
	}

	public PatientAvailableDataDS withExams(ArrayList<Exam> exams) {
		this.exams = exams;
		return this;
	}
	
}
