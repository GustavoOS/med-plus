package com.medplus.entities;

import java.util.ArrayList;

public interface ExamFactory {
	public Exam make();
	public ArrayList<Exam> makeList();
}
