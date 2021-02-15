package com.medplus.entities.domain;
import java.time.LocalDate;
import java.util.ArrayList;

public interface Patient extends User{
	public LocalDate getBirth();
	public void setBirth(LocalDate birth);
	public Boolean getIsFemale();
	public void setIsFemale(Boolean isFemale);
	public void setExams(ArrayList<Exam> exams);
	public ArrayList<Exam> getExams();
}
