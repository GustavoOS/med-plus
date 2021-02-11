package com.medplus.entities;

public interface ExamAdder {
	void addExam(Patient p, String fileID, String title);
	void setExamFactory(ExamFactory factory);
}
