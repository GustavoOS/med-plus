package com.medplus.gateways;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.HealthProvider;
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
		assertEquals("João", result.get(0).getName());
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
}
