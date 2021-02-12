package com.medplus.entities;

public interface ExamRemover {
	public void remove(Patient patient, String id);
	public Exam getLastRemoved();
}
