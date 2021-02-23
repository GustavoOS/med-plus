package com.medplus.entities.exam.changer;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.impl.ExamFactory;

public interface ExamAdder {
	void addExam(Patient p, String fileID, String title);
	void setExamFactory(ExamFactory factory);
}
