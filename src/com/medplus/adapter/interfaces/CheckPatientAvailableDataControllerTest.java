package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.useCases.AttendancePresenter;
import com.medplus.useCases.CheckAvailablePatientDataUseCase;

class CheckPatientAvailableDataControllerTest {
	CheckPatientAvailableDataController controller;

	CheckAvailablePatientDataUseCase useCase;
	AttendancePresenter presenter;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new CheckAvailablePatientDataUseCase();
		presenter = new AttendancePresenterImpl();
		useCase.setPresenter(presenter);
		setGateway();
		controller = new CheckPatientAvailableDataController();
		controller.setUseCase(useCase);
	}

	@Test
	void testFullPatient() {
		controller.check("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
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
		controller.check(null);
		assertFailure();
	}

	@Test
	void testInvalidPatientShouldFail()
	{
		controller.check("fghaesbhyuji");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getData());
	}
	private void setGateway() {
		PatientGW gw = new PatientGW();
		ArrayList<Patient> patients = TestUtils.mountPatientList();
		patients.get(0).setExams(TestUtils.mountExamList());
		gw.setPatients(patients);
		useCase.setPatientGateway(gw);
	}
}
