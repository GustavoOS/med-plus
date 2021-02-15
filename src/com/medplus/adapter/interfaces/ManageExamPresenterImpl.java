package com.medplus.adapter.interfaces;

import com.medplus.entities.domain.Exam;
import com.medplus.useCases.ManageExamPresenter;

public class ManageExamPresenterImpl implements ManageExamPresenter {

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
