package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.ExamAdder;
import com.medplus.entities.exam.adder.ExamAdderImpl;
import com.medplus.factories.ExamFactoryImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.useCases.ManageExamPresenter;
import com.medplus.useCases.AddExamUseCase;

class AddExamControllerTest {

	AddExamController controller;
	ManageExamPresenter presenter;
	AddExamUseCase useCase;
	PatientGW gw;
	ExamAdder adder;

	String patient;
	
	@BeforeEach
	void setUp() throws Exception {
		useCase = new AddExamUseCase();
		presenter = new ManageExamPresenterImpl();
		adder = new ExamAdderImpl();
		adder.setExamFactory(new ExamFactoryImpl());
		useCase.setExamAdder(adder);
		useCase.setPresenter(presenter);
		gw = new PatientGW();
		gw.setPatients(TestUtils.mountPatientList(LocalDateTime.now()));
		useCase.setGateway(gw);
		controller = new AddExamController();
		controller.setUseCase(useCase);
		patient = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
	}

	@Test
	void testValidUseCase() {
		controller.add(patient, "61c013fb-8deb-4d26-acce-82d8a6b9dfd0", "random");
		assertNotNull(presenter.getResult());
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertEquals("61c013fb-8deb-4d26-acce-82d8a6b9dfd0", presenter.getResult().getId());
		assertEquals("random", presenter.getResult().getTitle());
	}

}
