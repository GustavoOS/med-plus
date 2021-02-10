package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientAvailableDataDSTest {

	PatientAvailableDataDS ds;
	@BeforeEach
	void setUp() throws Exception {
		ds = new PatientAvailableDataDS();
	}

	@Test
	void mountComplete()
	{
		ds.withAge(26).withIsFemale(false).withName("Joe");
		assertEquals(26, ds.getAge());
		assertFalse(ds.getIsFemale());
		assertEquals("Joe", ds.getName());
	}

}
