package com.medplus.entities.exam.changer;

import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.Patient;

public interface ExamRemover {
	public void remove(Patient patient, String id);
	public Exam getLastRemoved();
}
