package com.medplus.useCases.exam;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.changer.ExamRemover;
import com.medplus.useCases.UserGateway;

public class RemoveExamUseCase {
	private UserGateway patientGateway;
	private ExamRemover remover;
	private ManageExamPresenter presenter;

	public void remove(String patientID, String examID)
	{
		Patient patient = (Patient) patientGateway.getUser(patientID);
		if(patient == null || cantRemove(examID, patient))
		{
			presenter.fail();
			return;
		}
		patientGateway.put(patient);
		presenter.succeed(remover.getLastRemoved());
	}

	private Boolean cantRemove(String examID, Patient patient) {
		remover.remove(patient, examID);
		return remover.getLastRemoved() == null;
	}

	public void setPatientGateway(UserGateway patientGateway) {
		this.patientGateway = patientGateway;
	}

	public void setPresenter(ManageExamPresenter presenter) {
		this.presenter = presenter;
	}

	public void setRemover(ExamRemover remover) {
		this.remover = remover;
	}

}
