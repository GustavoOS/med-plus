package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.cancelation.CancelAppointmentUseCase;

class CancelAppointmentControllerTest {

	CancelAppointmentController controller;
	CancelAppointmentUseCase useCase;
	CancelAppointmentPresenterImpl presenter;
	LocalDateTime date;
	PatientGW patientGW;
	ProviderGW providerGW;

	String patientID, providerID;

	@BeforeEach
	void setUp(){
		controller = new CancelAppointmentController();
		useCase = new CancelAppointmentUseCase();
		presenter = new CancelAppointmentPresenterImpl();

		controller.setUseCase(useCase);
		useCase.setPresenter(presenter);
		date = LocalDateTime.of(2021, 8, 2, 14, 0);
		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		providerID = "da2ed3e9-566b-4521-8002-6e15f6f9958d";
	
		setUpUseCase();
	}

	void setUpUseCase(){
		injectGWs();

		presenter = new CancelAppointmentPresenterImpl();
		useCase.setPresenter(presenter);
	}

	private void injectGWs() {
		ArrayList<Patient> patients = TestUtils.mountPatientList(date);
		ArrayList<HealthProvider> providers = TestUtils.mountProviderList(date);

		populateGWs(patients, providers);
	}

	private void populateGWs(ArrayList<Patient> patients, ArrayList<HealthProvider> providers) {
		patientGW = new PatientGW();
		providerGW = new ProviderGW();
		patientGW.setPatients(patients);
		providerGW.setProviders(providers);
	}

	@Test
	void cancelPatientAppointment() {
		useCase.setPeerGW(providerGW);
		useCase.setUserGW(patientGW);

		controller.cancel(patientID, date);
		assertSuccessAndSize(2);
	}

	@Test
	void cancelProviderAppointment()
	{
		useCase.setPeerGW(patientGW);
		useCase.setUserGW(providerGW);

		controller.cancel(providerID, date);
		assertSuccessAndSize(5);
	}

	private void assertSuccessAndSize(int size)
	{
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertEquals(size, presenter.getList().size());
	}
}
