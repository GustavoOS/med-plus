package com.medplus.entities.exam.changer.impl;

import java.time.LocalDateTime;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.changer.ExamAdder;
import com.medplus.entities.exam.impl.ExamFactory;

public class ExamAdderImpl implements ExamAdder {
	private ExamFactory factory;

	@Override
	public void addExam(Patient patient, String fileID, String title) {
		if(patient.getExams() == null)
			patient.setExams(factory.makeList());
		patient.getExams().add(
					factory.make()
						.withId(fileID)
						.withInsertionDateTime(LocalDateTime.now())
						.withTitle(title)
				);
	}

	public void setExamFactory(ExamFactory factory) {
		this.factory = factory;
	}

	

}
