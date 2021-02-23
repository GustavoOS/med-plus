package com.medplus.useCases.exam;

import com.medplus.entities.domain.Exam;
import com.medplus.useCases.FailablePresenter;

public interface ManageExamPresenter extends FailablePresenter{
	public void succeed(Exam exam);
	public String getStatus();
	public Exam getResult();
}
