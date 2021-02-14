package com.medplus.entities.exam.adder;

import java.time.LocalDateTime;

import com.medplus.entities.ExamAdder;
import com.medplus.entities.ExamFactory;
import com.medplus.entities.Patient;

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
