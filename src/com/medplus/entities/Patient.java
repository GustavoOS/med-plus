package com.medplus.entities;
import java.time.LocalDate;

public interface Patient extends User{
	public LocalDate getBirth();
	public void setBirth(LocalDate birth);
	public Boolean getIsFemale();
	public void setIsFemale(Boolean isFemale);
}
