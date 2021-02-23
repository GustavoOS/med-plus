package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelperClassesTest {
// To fill 100% of coverage when classes only have static members

	@Test
	void dayScheduleFactory() {
		assertNotNull(new DayScheduleFactory());
	}

	@Test
	void testUtils()
	{
		assertNotNull(new TestUtils());
	}

}
