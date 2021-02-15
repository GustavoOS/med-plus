package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.AttendancePresenterImpl;
import com.medplus.entities.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;

class CheckAvailablePatientDataUseCaseTest {
	CheckAvailablePatientDataUseCase useCase;
	AttendancePresenter presenter;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new CheckAvailablePatientDataUseCase();
		presenter = new AttendancePresenterImpl();
		useCase.setPresenter(presenter);
		setGateway();
	}

	@Test
	void testFullPatient() {
		useCase.check("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		assertEquals("success", presenter.getStatus());
		assertNotNull(presenter.getData());
		assertEquals("Maria", presenter.getData().getName());
		assertTrue(presenter.getData().getIsFemale());
		assertEquals(20, presenter.getData().getAge());
		assertEquals(3, presenter.getData().getExams().size());
		assertEquals("5367dea0-416a-44bf-a71a-df0cf72902c2",
				presenter.getData().getExams().get(1).getId());
	}

	@Test
	void testNullPatientShouldFail()
	{
		useCase.check(null);
		assertFailure();
	}

	@Test
	void testInvalidPatientShouldFail()
	{
		useCase.check("fghaesbhyuji");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getData());
	}
	private void setGateway() {
		PatientGW gw = new PatientGW();
		ArrayList<Patient> patients = TestUtils.mountPatientList(LocalDateTime.now());
		patients.get(0).setExams(TestUtils.mountExamList());
		gw.setPatients(patients);
		useCase.setPatientGateway(gw);
	}

}
