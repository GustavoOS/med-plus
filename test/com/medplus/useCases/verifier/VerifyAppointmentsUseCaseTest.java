package com.medplus.useCases.verifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.VerifyAppointmentsPresenterImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class VerifyAppointmentsUseCaseTest {

	VerifyAppointmentsUseCase useCase;
	PatientGW patientGW;
	ProviderGW providerGW;
	VerifyAppointmentsPresenterImpl presenter;
	

	@BeforeEach
	void setUp() throws Exception {
		useCase = new VerifyAppointmentsUseCase();
		patientGW = new PatientGW();
		patientGW.setPatients(TestUtils.mountPatientList());
		providerGW = new ProviderGW();
		providerGW.setProviders(TestUtils.mountProviderList());
		presenter = new VerifyAppointmentsPresenterImpl();

		
		useCase.setPresenter(presenter);
		useCase.setGw(patientGW);
		useCase.setPeerGW(providerGW);
	}

	@Test
	void checkPatientAppointment() {
		String patient = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		
		useCase.verify(patient);
		assertEquals("success", presenter.getStatus());
		assertEquals(3, presenter.getResult().size());
		assertEquals(1, presenter.getNames().keySet().size());
	}

	@Test
	void checkProviderAppointment()
	{
		String provider = "da2ed3e9-566b-4521-8002-6e15f6f9958d";
		providerCancel();
		useCase.verify(provider);
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void verifyNullPatient()
	{
		useCase.verify(null);
		assertFailure();
	}

	@Test
	void verifyNullProvider()
	{
		providerCancel();
		useCase.verify(null);
		assertFailure();
	}

	@Test
	void verifyUnregisteredPatient()
	{
		useCase.verify("avião");
		assertFailure();
	}

	@Test
	void verifyUnregisteredProvider()
	{
		providerCancel();
		useCase.verify("avião");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getResult());
	}


	private void providerCancel() {
		useCase.setGw(providerGW);
		useCase.setPeerGW(patientGW);
	}

}
