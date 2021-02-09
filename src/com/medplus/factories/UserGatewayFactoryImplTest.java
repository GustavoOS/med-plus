package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class UserGatewayFactoryImplTest {
	UserGatewayFactory factory;

	String provider;
	String patient;
	@BeforeEach
	void setUp() throws Exception {
		provider = "provider";
		patient = "patient";
		factory = new UserGatewayFactoryImpl();
	}

	@Test
	void makeProviderShouldNotBeNull()
	{
		assertNotNull(factory.make(provider));
	}

	@Test
	void makePatientShouldNotBeNull()
	{
		assertNotNull(factory.make(patient));
	}

	@Test
	void randomMakeShouldBeNull()
	{
		assertNull(factory.make("blabla"));
	}

	@Test
	void makeProviderShouldMakeProviderGWInstance()
	{
		assertTrue(factory.make(provider) instanceof ProviderGW);
	}

	@Test
	void makePatientShouldMakePatientGWInstance()
	{
		assertTrue(factory.make(patient) instanceof PatientGW);
	}
}
