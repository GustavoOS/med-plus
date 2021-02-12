package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.TestUtils;

class PatientAvailableDataDSTest {

	PatientAvailableDataDS ds;
	@BeforeEach
	void setUp() throws Exception {
		ds = new PatientAvailableDataDS();
	}

	@Test
	void mountComplete()
	{
		ds
			.withAge(26)
			.withIsFemale(false)
			.withName("Joe")
			.withExams(TestUtils.mountExamList());
		assertEquals(26, ds.getAge());
		assertFalse(ds.getIsFemale());
		assertEquals("Joe", ds.getName());
		assertEquals(3, ds.getExams().size());
	}

}
