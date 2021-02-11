package com.medplus.adapter.interfaces;

import com.medplus.entities.Exam;
import com.medplus.useCases.AddExamPresenter;

public class AddExamPresenterImpl implements AddExamPresenter {

	private String status;
	private Exam result;

	@Override
	public void fail() {
		status = "fail";
	}

	@Override
	public void succeed(Exam exam) {
		status = "success";
		result = exam;
	}

	public String getStatus() {
		return status;
	}
	public Exam getResult() {
		return result;
	}


}
