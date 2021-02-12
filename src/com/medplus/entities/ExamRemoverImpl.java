package com.medplus.entities;

import java.util.ArrayList;

public class ExamRemoverImpl implements ExamRemover {
	private ArrayList<Exam> exams;
	private Exam found;

	@Override
	public void remove(Patient patient, String id) {
		if(patient == null)
			return;
		exams = patient.getExams();
		found = findExam(id);
		if(found != null)
			exams.remove(found);
	}

	private Exam findExam(String id)
	{
		return (exams == null || id == null)? null : exams.stream()
				  .filter(e -> id.equals(e.getId()))
				  .findAny()
				  .orElse(null);
	}

	public Exam getLastRemoved()
	{
		return found;
	}
}
