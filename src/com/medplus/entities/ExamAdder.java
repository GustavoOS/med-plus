package com.medplus.entities;

import com.medplus.entities.domain.Patient;

public interface ExamAdder {
	void addExam(Patient p, String fileID, String title);
	void setExamFactory(ExamFactory factory);
}
