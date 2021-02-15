package com.medplus.gateways;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Patient;
import com.medplus.entities.patient.PatientImpl;
import com.medplus.factories.TestUtils;

class PatientGWTest {

	PatientGW gw;
	ArrayList<Patient> result;

	@BeforeEach
	void setUp(){
		gw = new PatientGW();
		gw.setPatients(TestUtils.mountPatientList());
	}

	@Test
	void putANewPatient()
	{
		Patient p = new PatientImpl();
		p.setId("b023fc40-70e0-4363-bc20-6e86fe34b0d1");
		p.setName("Tarzão");
		gw.put(p);
		result = gw.getPatients();
		assertEquals(3, result.size());
		assertEquals("Tarzão", result.get(2).getName());
	}

	@Test
	void updateAPatient()
	{
		Patient p = Cloner.clonePatient(gw.getPatients().get(1));
		String id = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		p.setName("Rita");
		assertEquals(id, p.getId());
		assertEquals("Maria", gw.getPatients().get(0).getName());
		gw.put(p);
		assertEquals("Rita", gw.getUser(id).getName());
	}

	@Test
	void testProviderRecovery()
	{
		String id = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		assertTrue(gw.getUser(id) instanceof Patient);
		Patient patient = (Patient) gw.getUser(id);
		assertNotNull(patient);
		assertEquals("John", patient.getName());
		assertNotSame(patient, gw.getPatients().get(1));
		assertEquals(id, gw.getPatients().get(1).getId());
	}

	@Test
	void testFilter()
	{
		String id = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		assertEquals(1, gw.getPatients().indexOf(gw.filterPatient(id)));
	}
}
