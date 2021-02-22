package com.medplus.entities.exam.impl;

import java.util.ArrayList;

import com.medplus.entities.ExamFactory;
import com.medplus.entities.domain.Exam;

public class ExamFactoryImpl implements ExamFactory {

	@Override
	public Exam make() {
		return new ExamImpl();
	}

	public ArrayList<Exam> makeList(){
		return new ArrayList<Exam>();
	}
}
