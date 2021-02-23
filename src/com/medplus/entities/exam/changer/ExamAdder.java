package com.medplus.entities.exam.changer;

import com.medplus.entities.domain.Patient;

public interface ExamAdder {
	void addExam(Patient p, String fileID, String title);
	void setExamFactory(ExamFactory factory);
}
