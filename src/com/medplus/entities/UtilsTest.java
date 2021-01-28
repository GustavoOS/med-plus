package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void testDistanceCalculation() {
		CoordinateDS a = new CoordinateDS(53.32055555555556, -1.7297222222222221);
		CoordinateDS b = new CoordinateDS();
		b.latitude = 53.31861111111111;
		b.longitude = -1.6997222222222223;
		
		assertEquals(2.0043, Utils.calculateDistance(a, b), 0.001);
	}

	@Test
	void copiedListShouldHaveDifferentReferences()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Utils.copyProviderList(raw);
		assertEquals(4, result.size());
		for(int i = 0; i < 4; i++)
		{
			assertNotSame(result.get(i), raw.get(i));
			assertEquals(raw.get(i).getName(), result.get(i).getName());
		}
	}

	@Test
	void changeInCopiedListShouldNotChangeTheOriginal()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Utils.copyProviderList(raw);
		result.get(0).setName("Corleone");
		assertEquals("João", raw.get(0).getName());
		assertEquals("Corleone", result.get(0).getName());
	}

	@Test
	void calculateAge() {
		assertEquals(20, Utils.calculateAge(LocalDate.now().minusYears(20)));
	}

	@Test
	void nullBirthday()
	{
		assertEquals(0, Utils.calculateAge(null));
	}
}
