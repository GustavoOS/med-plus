package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
	void calculateAge() {
		assertEquals(20, Utils.calculateAge(LocalDate.now().minusYears(20)));
	}

	@Test
	void nullBirthday()
	{
		assertEquals(0, Utils.calculateAge(null));
	}

	@Test
	void testConstructorToFillCoverage()
	{
		assertNotNull(new Utils());
	}
}
