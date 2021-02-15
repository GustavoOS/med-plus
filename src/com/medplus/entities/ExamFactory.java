package com.medplus.entities;

import java.util.ArrayList;

import com.medplus.entities.domain.Exam;

public interface ExamFactory {
	public Exam make();
	public ArrayList<Exam> makeList();
}
