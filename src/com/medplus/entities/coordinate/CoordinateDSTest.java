package com.medplus.entities.coordinate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Coordinate;

class CoordinateDSTest {

	Coordinate coordinate, result;
	@BeforeEach
	void setUp() throws Exception {
		coordinate = new CoordinateDS();
	}

	@Test
	void checkValidation()
	{
		result = coordinate.with(93, 24);
		assertNotNull(result);
		assertTrue(result instanceof CoordinateDS);
	}

	@Test
	void testCordinateDSStorage() {
		result = coordinate.with(25, 34);
		assertSame(result, coordinate);
		assertEquals(25, result.lat());
		assertEquals(34, result.lon());
	}

}
