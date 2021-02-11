package com.medplus.useCases;

import com.medplus.entities.Exam;

public interface AddExamPresenter extends FailablePresenter{
	public void succeed(Exam exam);
	public String getStatus();
	public Exam getResult();
}
