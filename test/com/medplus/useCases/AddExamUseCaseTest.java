package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.ManageExamPresenterImpl;
import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.changer.ExamAdder;
import com.medplus.entities.exam.changer.ExamAdderImpl;
import com.medplus.entities.exam.changer.impl.ExamFactoryImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.useCases.exam.AddExamUseCase;
import com.medplus.useCases.exam.ManageExamPresenter;

class AddExamUseCaseTest {
	AddExamUseCase useCase;
	PatientGW gw;
	Patient patient;
	String patientID, fileID;
	ManageExamPresenter presenter;

	Exam exam;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new AddExamUseCase();
		ExamAdder adder = new ExamAdderImpl();
		adder.setExamFactory(new ExamFactoryImpl());
		useCase.setExamAdder(adder);
		gw = new PatientGW();
		gw.setPatients(TestUtils.mountPatientList(LocalDateTime.now()));
		useCase.setGateway(gw);
		presenter = new ManageExamPresenterImpl();
		useCase.setPresenter(presenter);
		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		fileID = "1cbc4c03-4ec6-4946-b5c2-195bf4fad481";
	}

	@Test
	void testSuccess() {
		useCase.add(patientID, "echo", fileID);
		patient = (Patient) gw.getUser(patientID);
		assertEquals(1, patient.getExams().size());
		assertEquals("echo", patient.getExams().get(0).getTitle());
		assertEquals(fileID, patient.getExams().get(0).getId());
		assertEquals("success", presenter.getStatus());
		assertEquals(patient.getExams().get(0).getInsertionDateTime(),
				presenter.getResult().getInsertionDateTime());
	}

	@Test
	void testInvalidPatient()
	{
		useCase.add("aviao", "echo", fileID);
		assertFailure();
	}

	@Test
	void testNullPatient()
	{
		useCase.add(null, "echo", fileID);
		assertFailure();
	}

	@Test
	void testNullFileIDShouldFail()
	{
		useCase.add(patientID, "echo", null);
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
	}
}
