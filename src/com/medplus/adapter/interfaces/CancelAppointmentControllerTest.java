package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.Appointment;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Patient;
import com.medplus.entities.PatientAppointmentCanceler;
import com.medplus.entities.ProviderAppointmentCanceler;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.CancelAppointmentUseCase;

class CancelAppointmentControllerTest {

	CancelAppointmentController controller;
	CancelAppointmentUseCase useCase;
	CancelAppointmentPresenterImpl presenter;
	LocalDateTime date;
	PatientGW patientGW;
	ProviderGW providerGW;

	@BeforeEach
	void setUp(){
		controller = new CancelAppointmentController();
		useCase = new CancelAppointmentUseCase();
		presenter = new CancelAppointmentPresenterImpl();

		controller.setUseCase(useCase);
		useCase.setPresenter(presenter);
		date = LocalDateTime.of(2021, 8, 2, 22, 0);
	
		setUpUseCase();
	}

	void setUpUseCase(){
		injectGWs();

		presenter = new CancelAppointmentPresenterImpl();
		useCase.setPresenter(presenter);
	}

	private void injectGWs() {
		ArrayList<Patient> patients = TestUtils.mountPatientList();
		ArrayList<HealthProvider> providers = TestUtils.mountProviderList();
		setAppointments(patients, providers);

		populateGWs(patients, providers);
	}

	private void setAppointments(ArrayList<Patient> patients, ArrayList<HealthProvider> providers) {
		ArrayList<Appointment> appointments = 
				TestUtils.mountAppointmentList(
						"da2ed3e9-566b-4521-8002-6e15f6f9958d",
						"4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
						date);
		patients.get(0).setAppointments(appointments);
		providers.get(0).setAppointments(appointments);
	}

	private void populateGWs(ArrayList<Patient> patients, ArrayList<HealthProvider> providers) {
		patientGW = new PatientGW();
		providerGW = new ProviderGW();
		patientGW.setPatients(patients);
		providerGW.setProviders(providers);
	}

	@Test
	void cancelPatientAppointment() {
		useCase.setTargetGW(providerGW);
		useCase.setRootGW(patientGW);

		useCase.setCanceler(new PatientAppointmentCanceler());

		controller.cancel("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1", date);
		assertSuccessAndSize(2);
	}

	@Test
	void cancelProviderAppointment()
	{

		useCase.setCanceler(new ProviderAppointmentCanceler());
		useCase.setTargetGW(patientGW);
		useCase.setRootGW(providerGW);

		controller.cancel("da2ed3e9-566b-4521-8002-6e15f6f9958d", date);
		assertSuccessAndSize(2);
	}

	private void assertSuccessAndSize(int size)
	{
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertEquals(size, presenter.getList().size());
	}
}
