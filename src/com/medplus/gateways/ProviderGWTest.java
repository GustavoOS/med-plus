package com.medplus.gateways;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.Cloner;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Provider;
import com.medplus.factories.TestUtils;

class ProviderGWTest {
	ProviderGW gw;
	ArrayList<HealthProvider> result, raw;

	@BeforeEach
	void setUp(){
		gw = new ProviderGW();
		raw = TestUtils.mountProviderList();
		gw.setProviders(raw);
	}

	@Test
	void listShouldReturnACopyOfAllValuesStoredCheckByName() {
		result = gw.list();
		assertEquals(4, result.size());
		assertEquals("Joe", result.get(0).getName());
		assertEquals("Silva", result.get(1).getName());
		assertEquals("Paz", result.get(2).getName());
		assertEquals("Benedito", result.get(3).getName());
	}

	@Test
	void listReferenceShouldDifferFromRaw()
	{
		result = gw.list();
		assertEquals(4, result.size());
		assertEquals(4, raw.size());
		for(int i = 0; i < 4; i++)
		{
			assertNotSame(raw.get(i), result.get(i));
		}
	}

	@Test
	void putANewProvider()
	{
		HealthProvider p = new Provider();
		p.setId("b023fc40-70e0-4363-bc20-6e86fe34b0d1");
		p.setSocialMediaURL("medplus.com");
		gw.put(p);
		result = gw.list();
		assertEquals(5, result.size());
		assertEquals("medplus.com", result.get(4).getSocialMediaURL());
		assertEquals("Joe", result.get(0).getName());
		assertEquals("Silva", result.get(1).getName());
		assertEquals("Paz", result.get(2).getName());
		assertEquals("Benedito", result.get(3).getName());
	}

	@Test
	void updateAProvider()
	{
		HealthProvider p = Cloner.cloneProvider(raw.get(0));
		p.setSocialMediaURL("medplus.com");
		p.setAppointments(
				TestUtils.mountAppointmentList(
						"da2ed3e9-566b-4521-8002-6e15f6f9958d",
						"566fce65-7170-4bc3-bde5-09f807df6798",
						LocalDateTime.now().withHour(14)));

		result = gw.list();
		assertEquals("blabla.com", result.get(0).getSocialMediaURL());
		assertEquals("da2ed3e9-566b-4521-8002-6e15f6f9958d", result.get(0).getId());

		gw.put(p);
		result = gw.list();
		assertEquals(4, result.size());
		assertNotNull(result.get(0).getAppointments());
		assertEquals(3, result.get(0).getAppointments().size());
		assertNotSame(p.getAppointments(), result.get(0).getAppointments());
		assertEquals("da2ed3e9-566b-4521-8002-6e15f6f9958d", result.get(0).getId());
		assertEquals("medplus.com", result.get(0).getSocialMediaURL());
	}

	@Test
	void testProviderRecovery()
	{
		assertTrue(gw.getUser("7b11fdbb-0894-4e4b-afaf-880738c84f4c") instanceof HealthProvider);
		HealthProvider provider = (HealthProvider) gw.getUser("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		assertNotNull(provider);
		assertEquals("Paz", provider.getName());
		assertNotSame(provider, gw.getProviders().get(2));
		assertEquals("7b11fdbb-0894-4e4b-afaf-880738c84f4c", gw.getProviders().get(2).getId());
	}

	@Test
	void putNullShouldNotChange()
	{
		gw.put(null);
		result = gw.list();
		assertEquals(4, result.size());
		assertEquals("Joe", result.get(0).getName());
		assertEquals("Silva", result.get(1).getName());
		assertEquals("Paz", result.get(2).getName());
		assertEquals("Benedito", result.get(3).getName());
	}
}
