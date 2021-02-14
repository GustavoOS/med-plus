package com.medplus.factories;

import java.util.ArrayList;

import com.medplus.entities.Exam;
import com.medplus.entities.ExamFactory;
import com.medplus.entities.exam.impl.ExamImpl;

public class ExamFactoryImpl implements ExamFactory {

	@Override
	public Exam make() {
		return new ExamImpl();
	}

	public ArrayList<Exam> makeList(){
		return new ArrayList<Exam>();
	}
}
