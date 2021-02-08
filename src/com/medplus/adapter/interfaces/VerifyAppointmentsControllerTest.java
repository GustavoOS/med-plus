package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.HealthProvider;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.Id2NameTranslater;
import com.medplus.useCases.TranslateIDToName;
import com.medplus.useCases.VerifyAppointmentsUseCase;

class VerifyAppointmentsControllerTest {

	VerifyAppointmentsUseCase useCase;
	VerifyAppointmentsPresenterImpl presenter;
	VerifyAppointmentsController controller;

	ProviderGW providerGW;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new VerifyAppointmentsUseCase();
		presenter = new VerifyAppointmentsPresenterImpl();

		setTranslaterAndGWs();			
		useCase.setPresenter(presenter);

		controller = new VerifyAppointmentsController();
		controller.setUseCase(useCase);
	}

	private void setTranslaterAndGWs() {
		PatientGW patientGW = new PatientGW();
		providerGW = new ProviderGW();
		ArrayList<HealthProvider> providerList = TestUtils.mountProviderList();
		providerList.get(0).setAppointments(
				TestUtils.mountAppointmentList(
						"da2ed3e9-566b-4521-8002-6e15f6f9958d",
						"4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
						LocalDateTime.of(2021, 2, 8, 16, 0)));
		patientGW.setPatients(TestUtils.mountPatientList());
		providerGW.setProviders(providerList);

		Id2NameTranslater translater = new TranslateIDToName();
		translater.setPatientGW(patientGW);
		translater.setProviderGW(providerGW);

		useCase.setGw(patientGW);
		useCase.setTranslater(translater);
	}

	@Test
	void checkPatient() {
		String patient = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		controller.verify(patient);
		assertEquals("success", presenter.getStatus());
		assertEquals(3, presenter.getResult().size());
	}

	@Test
	void checkProvider()
	{
		useCase.setGw(providerGW);
		controller.verify("da2ed3e9-566b-4521-8002-6e15f6f9958d");
		assertEquals("success", presenter.getStatus());
		assertEquals(3, presenter.getResult().size());
	}

	@Test
	void verifyNull()
	{
		controller.verify(null);
		assertFailure();
	}

	@Test
	void verifyUnregistered()
	{
		controller.verify("avião");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getResult());
	}
}
