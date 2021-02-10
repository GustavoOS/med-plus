package com.medplus.useCases.verifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class TranslateIDToNameTest {
	TranslateIDToName translater;
	@BeforeEach
	void setUp() throws Exception {
		PatientGW patientGW = new PatientGW();
		patientGW.setPatients(TestUtils.mountPatientList());
		ProviderGW providerGW = new ProviderGW();
		providerGW.setProviders(TestUtils.mountProviderList());
		translater = new TranslateIDToName();
		translater.setPatientGW(patientGW);
		translater.setProviderGW(providerGW);
	}

	@Test
	void testPatientConversion() {
		assertEquals("Maria", translater.translate("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1"));
	}

	@Test
	void testProviderConversion()
	{
		assertEquals("Joe", translater.translate("da2ed3e9-566b-4521-8002-6e15f6f9958d"));
	}

	@Test
	void nullTranslationShouldReturnNull()
	{
		assertNull(translater.translate(null));
	}

	@Test
	void unregisteredNameShouldReturnNull()
	{
		assertNull(translater.translate("aviao"));
	}
}
