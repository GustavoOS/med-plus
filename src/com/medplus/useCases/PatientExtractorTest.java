package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.patient.PatientImpl;
import com.medplus.factories.TestUtils;

class PatientExtractorTest {

	Patient patient;
	PatientAvailableDataDS extracted;
	@BeforeEach
	void setUp() throws Exception {
		patient = new PatientImpl();
	}

	@Test
	void testFullPatient() {
		patient.setAppointments(TestUtils.mountAppointmentList("doc", LocalDateTime.now()));
		patient.setBirth(LocalDateTime.now().minusYears(20).toLocalDate());
		patient.setId("pac");
		patient.setIsFemale(true);
		patient.setName("mary");
		patient.setExams(TestUtils.mountExamList());
	
		extracted = PatientExtractor.extract(patient);
		assertEquals(20, extracted.getAge());
		assertEquals("mary", extracted.getName());
		assertTrue(extracted.getIsFemale());
		assertEquals("echocardiogram", extracted.getExams().get(2).getTitle());
	}

	@Test
	void testOnlyName()
	{
		patient.setName("mary");

		extracted = PatientExtractor.extract(patient);
		assertEquals("mary", extracted.getName());
		assertNull(extracted.getIsFemale());
	}

	@Test
	void testStatic()
	{
		assertNotNull(new PatientExtractor());
	}
}
