package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.VerifyPatientAppointmentsPresenterImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;

class VerifyPatientAppointmentsUseCaseTest {

	VerifyPatientAppointmentsUseCase useCase;
	PatientGW gw;
	VerifyPatientAppointmentsPresenterImpl presenter;
	String patient;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new VerifyPatientAppointmentsUseCase();
		gw = new PatientGW();
		gw.setPatients(TestUtils.mountPatientList());
		presenter = new VerifyPatientAppointmentsPresenterImpl();
		patient = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		useCase.setGw(gw);
		useCase.setPresenter(presenter);
	}

	@Test
	void check() {
		useCase.verify(patient);
		assertEquals("success", presenter.getStatus());
		assertEquals(3, presenter.getResult().size());
	}

	@Test
	void verifyNull()
	{
		useCase.verify(null);
		assertFailure();
	}

	@Test
	void verifyUnregistered()
	{
		useCase.verify("avião");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getResult());
	}

}
