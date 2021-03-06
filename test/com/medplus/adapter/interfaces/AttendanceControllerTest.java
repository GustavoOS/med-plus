package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.CheckAvailablePatientDataUseCase;
import com.medplus.useCases.attend.AttendanceUseCase;

class AttendanceControllerTest {
	AttendanceUseCase useCase;
	ProviderGW providerGW;
	PatientGW patientGW;
	AttendancePresenterImpl presenter;
	ArrayList<HealthProvider> providers;
	LocalDateTime baseTime;
	String provider;
	AttendanceController controller;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new AttendanceUseCase();
		provider = "da2ed3e9-566b-4521-8002-6e15f6f9958d";
		baseTime = LocalDateTime.now()
				.withHour(14)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);


		presenter = new AttendancePresenterImpl();
		useCase.setPresenter(presenter);
		
		setGateways();
		
		controller = new AttendanceController();
		controller.setUseCase(useCase);
		
	}

	private void setGateways() {
		providerGW = new ProviderGW();
		providers = TestUtils.mountProviderList(LocalDateTime.now());
		setDoctorAppointments("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		providerGW.setProviders(providers);
		setPatientGW();
		useCase.setProviderGateway(providerGW);
	}

	private void setPatientGW() {
		patientGW = new PatientGW();
		patientGW.setPatients(TestUtils.mountPatientList(LocalDateTime.now()));
		CheckAvailablePatientDataUseCase checker = 
				new CheckAvailablePatientDataUseCase();
		checker.setPatientGateway(patientGW);
		useCase.setChecker(checker);
	}

	private void setDoctorAppointments(String patient) {
		providers.get(0).setAppointments(
				TestUtils.mountAppointmentList(
						patient,
						baseTime));
	}

	@Test
	void testValid() {
		controller.attend(provider, baseTime);
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertEquals("Maria", presenter.getData().getName());
		assertTrue(presenter.getData().getIsFemale());
		assertEquals(20, presenter.getData().getAge());
	}
}
