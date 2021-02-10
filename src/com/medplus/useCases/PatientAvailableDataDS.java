package com.medplus.useCases;

public class PatientAvailableDataDS {
	private String name;
	private int age;
	private Boolean isFemale;

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
}
