package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.medplus.entities.Patient;


class PatientFactoryImplTest {

	@Test
	void test() {
		assertTrue((new PatientFactoryImpl()).make() instanceof Patient);
	}

}
