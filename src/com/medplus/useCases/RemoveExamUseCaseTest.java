package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.ManageExamPresenterImpl;
import com.medplus.entities.Patient;
import com.medplus.entities.exam.remover.ExamRemoverImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;

class RemoveExamUseCaseTest {

	RemoveExamUseCase useCase;
	ManageExamPresenter presenter;
	ArrayList<Patient> patients;

	String patientID, examID;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new RemoveExamUseCase();
		setGateway();
		presenter = new ManageExamPresenterImpl();
		useCase.setRemover(new ExamRemoverImpl());
		useCase.setPresenter(presenter);
	}

	private void setGateway() {
		PatientGW gw = new PatientGW();
		patients = TestUtils.mountPatientList(LocalDateTime.now());
		patients.get(0).setExams(TestUtils.mountExamList());
		gw.setPatients(patients);
		useCase.setPatientGateway(gw);
		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		examID = "5367dea0-416a-44bf-a71a-df0cf72902c2";
	}

	@Test
	void testSucess() {
		
		useCase.remove(patientID,
						examID);
		assertEquals("success", presenter.getStatus());
		assertNotNull(presenter.getResult());
		assertEquals(examID,
				presenter.getResult().getId()
				);
		assertEquals("urine test", presenter.getResult().getTitle());
		assertPatientExamListSize(2);
	}

	@Test
	void invalidExamRemovalShouldFail()
	{
		useCase.remove(patientID, "wsedgyuikthisissorandomomg");
		assertFailure();
	}

	@Test
	void nullExamIDShouldFail()
	{
		useCase.remove(patientID, null);
		assertFailure();
	}

	@Test
	void invalidPatientShouldFail()
	{
		useCase.remove("drfgyhuytfrygu", examID);
		assertInvalid();
	}

	@Test
	void nullPatientShouldFail()
	{
		useCase.remove(null, examID);
		assertInvalid();
	}

	private void assertInvalid() {
		assertNull(presenter.getResult());
		assertEquals("fail", presenter.getStatus());
	}

	private void assertFailure() {
		assertNull(presenter.getResult());
		assertPatientExamListSize(3);
		assertEquals("fail", presenter.getStatus());
	}


	private void assertPatientExamListSize(int size) {
		assertEquals(size, patients.get(0).getExams().size());
	}

}
