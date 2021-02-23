package com.medplus.useCases.exam;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.changer.ExamAdder;
import com.medplus.useCases.UserGateway;

public class AddExamUseCase {
	private UserGateway gateway;
	private ManageExamPresenter presenter;
	private ExamAdder examAdder;
	
	public void add(String patientID, String title, String fileID)
	{
		Patient patient = (Patient) gateway.getUser(patientID);
		if(patient == null || fileID == null)
		{
			presenter.fail();
			return;
		}
		examAdder.addExam(patient, fileID, title);
		gateway.put(patient);
		presenter.succeed(patient.getExams().get(patient.getExams().size() - 1));
	}

	public void setGateway(UserGateway gateway)
	{
		this.gateway = gateway;
	}

	public void setPresenter(ManageExamPresenter presenter)
	{
		this.presenter = presenter;
	}


	public void setExamAdder(ExamAdder examAdder) {
		this.examAdder = examAdder;
	}

	
}
