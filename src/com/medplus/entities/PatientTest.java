package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientTest {

	Patient patient;
	@BeforeEach
	void setUp()
	{
		patient = new Patient();
	}

	@Test
	void testSettersAndGetters()
	{
		patient.setId("ae20ca9f-a2de-4417-a980-64408ecf657a");
		patient.setName("Joe");
		patient.setIsFemale(false);
		patient.setBirth(LocalDate.now());
		assertEquals("ae20ca9f-a2de-4417-a980-64408ecf657a", patient.getId());
		assertEquals("Joe", patient.getName());
		assertEquals(LocalDate.now(), patient.getBirth());
		assertFalse(patient.getIsFemale());
	}
}
