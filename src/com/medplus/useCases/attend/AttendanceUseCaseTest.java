package com.medplus.useCases.attend;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.AttendancePresenterImpl;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.CheckAvailablePatientDataUseCase;

class AttendanceUseCaseTest {

	AttendanceUseCase useCase;
	CheckAvailablePatientDataUseCase checker;
	ProviderGW providerGW;
	PatientGW patientGW;
	AttendancePresenterImpl presenter;
	ArrayList<HealthProvider> providers;
	LocalDateTime baseTime;
	String provider;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new AttendanceUseCase();
		checker = new CheckAvailablePatientDataUseCase();
		provider = "da2ed3e9-566b-4521-8002-6e15f6f9958d";
		baseTime = LocalDateTime.now()
				.withHour(14)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);

		presenter = new AttendancePresenterImpl();
		useCase.setPresenter(presenter);
		setGateways();
	}

	private void setGateways() {
		providerGW = new ProviderGW();
		providers = TestUtils.mountProviderList();
		setDoctorAppointments("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		providerGW.setProviders(providers);
		patientGW = new PatientGW();
		patientGW.setPatients(mountPatientList());
		checker.setPatientGateway(patientGW);
		useCase.setChecker(checker);
		useCase.setProviderGateway(providerGW);
	}

	private ArrayList<Patient> mountPatientList() {
		ArrayList<Patient> patients = TestUtils.mountPatientList();
		patients.get(0).setExams(TestUtils.mountExamList());
		return patients;
	}

	private void setDoctorAppointments(String patient) {
		providers.get(0).setAppointments(
				TestUtils.mountAppointmentList(
						patient,
						baseTime));
	}

	@Test
	void testValid() {
		useCase.attend(provider, baseTime);
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertEquals("Maria", presenter.getData().getName());
		assertTrue(presenter.getData().getIsFemale());
		assertEquals(20, presenter.getData().getAge());
		assertEquals("echocardiogram",
				presenter.getData().getExams().get(2).getTitle());
	}

	@Test
	void testInvalidDateTime()
	{
		useCase.attend(provider, baseTime.minusDays(1));
		assertNotNull(presenter.getStatus());
		assertFailure();
	}

	@Test
	void testNullDoctor()
	{
		useCase.attend(null, baseTime);
		assertFailure();
	}

	@Test
	void testNullTime()
	{
		useCase.attend(provider, null);
		assertFailure();
	}

	@Test
	void doctorWithNoAppointmentsShouldFail()
	{
		useCase.attend("7b11fdbb-0894-4e4b-afaf-880738c84f4c", baseTime);
		assertFailure();
	}

	@Test
	void invalidDoctorShoulFail()
	{
		useCase.attend("aviao", baseTime);
		assertFailure();
	}

	@Test
	void appointmentWithoutPatient()
	{
		setDoctorAppointments(null);
		useCase.attend(provider, baseTime);
		assertFailure();
	}

	@Test
	void appointmentsWithInvalidPatient()
	{
		setDoctorAppointments("aviao");
		useCase.attend(provider, baseTime);
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
	}

}
