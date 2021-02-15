package com.medplus.entities.exam.remover;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Patient;
import com.medplus.factories.TestUtils;

class ExamRemoverImplTest {
	ExamRemoverImpl remover;
	Patient patient;

	@BeforeEach
	void setUp() throws Exception {
		remover = new ExamRemoverImpl();
		patient = TestUtils.mountPatientList().get(0);
		patient.setExams(TestUtils.mountExamList());
	}

	@Test
	void removeLastExam() {
		removeExam("67e9e81e-c631-479f-aa14-60792d6a1bcf");
	}


	@Test
	void removeFirstExam()
	{
		removeExam("0ef0c14e-5745-4402-8629-7b4f45c433fb");
	}

	@Test
	void removeMiddleExam()
	{
		removeExam("5367dea0-416a-44bf-a71a-df0cf72902c2");
	}

	@Test
	void removeEmptyIDShouldFail()
	{
		remover.remove(patient, null);
		assertEquals(3, patient.getExams().size());
		assertNull(remover.getLastRemoved());
	}

	@Test
	void afterRemovalFoundShouldBeTheSecondRemoved()
	{
		remover.remove(patient, "67e9e81e-c631-479f-aa14-60792d6a1bcf");
		assertEquals("67e9e81e-c631-479f-aa14-60792d6a1bcf",
				remover.getLastRemoved().getId());
		remover.remove(patient, "5367dea0-416a-44bf-a71a-df0cf72902c2");
		assertEquals(1, patient.getExams().size());
		assertEquals("0ef0c14e-5745-4402-8629-7b4f45c433fb",
				patient.getExams().get(0).getId());
		assertNotNull(remover.getLastRemoved());
		assertEquals("5367dea0-416a-44bf-a71a-df0cf72902c2",
				remover.getLastRemoved().getId());
	}

	@Test
	void removeEmptyPatientShouldFail()
	{
		remover.remove(null, "5367dea0-416a-44bf-a71a-df0cf72902c2");
		assertEquals(3, patient.getExams().size());
		assertNull(remover.getLastRemoved());
	}

	private void removeExam(String id) {
		remover.remove(patient, id);
		assertEquals(2, patient.getExams().size());
		assertNotEquals(id,
				patient.getExams().get(0).getId());
		assertNotEquals(id,
				patient.getExams().get(1).getId());
		assertNotNull(remover.getLastRemoved());
		assertEquals(id,
				remover.getLastRemoved().getId());
	}
}
