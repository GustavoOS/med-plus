package com.medplus.entities.exam.adder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.changer.ExamAdderImpl;
import com.medplus.entities.exam.changer.impl.ExamFactoryImpl;
import com.medplus.entities.patient.PatientImpl;

class ExamAdderImplTest {

	ExamAdderImpl adder;
	Patient patient;
	Exam exam;

	@BeforeEach
	void setUp() throws Exception {
		adder = new ExamAdderImpl();
		patient = new PatientImpl();
		adder.setExamFactory(new ExamFactoryImpl());
	}

	@Test
	void testAdd() {
		adder.addExam(
				patient,
				"350648de-074e-42b1-bb39-782afc39c597",
				"exam");
		assertNotNull(patient.getExams());
		assertEquals(1, patient.getExams().size());
		exam = patient.getExams().get(0);
		assertEquals(
				"350648de-074e-42b1-bb39-782afc39c597",
				exam.getId());
		assertEquals("exam", exam.getTitle());
	}
}
