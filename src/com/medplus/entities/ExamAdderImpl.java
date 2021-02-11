package com.medplus.entities;

import java.time.LocalDateTime;

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
