package com.medplus.useCases;

import com.medplus.entities.domain.Exam;

public interface ManageExamPresenter extends FailablePresenter{
	public void succeed(Exam exam);
	public String getStatus();
	public Exam getResult();
}
