package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.ExamRemoverImpl;
import com.medplus.entities.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.useCases.RemoveExamUseCase;

class RemoveExamControllerTest {
	RemoveExamController controller;
	RemoveExamUseCase useCase;
	ManageExamPresenterImpl presenter;

	PatientGW gw;
	ArrayList<Patient> patients;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new RemoveExamUseCase();
		setGW();
		presenter = new ManageExamPresenterImpl();
		useCase.setPresenter(presenter);
		useCase.setRemover(new ExamRemoverImpl());
		controller = new RemoveExamController();
		controller.setUseCase(useCase);
	}

	private void setGW() {
		gw = new PatientGW();
		patients = TestUtils.mountPatientList();
		patients.get(0).setExams(TestUtils.mountExamList());
		gw.setPatients(patients);
		useCase.setPatientGateway(gw);
	}

	@Test
	void test() {
		controller.remove("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
				"67e9e81e-c631-479f-aa14-60792d6a1bcf");
		assertEquals(2, patients.get(0).getExams().size());
		assertEquals("success", presenter.getStatus());
	}

}
