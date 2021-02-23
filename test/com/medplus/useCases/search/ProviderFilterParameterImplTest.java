package com.medplus.useCases.search;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.coordinate.CoordinateDS;
import com.medplus.entities.domain.Coordinate;
import com.medplus.entities.provider.filter.ProviderFilterParameter;

class ProviderFilterParameterImplTest {
	ProviderFilterParameterImpl param;
	@BeforeEach
	void setUp() throws Exception {
		param = new ProviderFilterParameterImpl();
	}

	@Test
	void distanceShouldBeNegative() {
		assertEquals(-1, param.getDistance());
	}

	@Test
	void testWiths()
	{
		ProviderFilterParameter result;
		Coordinate reference = (new CoordinateDS()).with(213,360);
		result = param
					.withDistance(23)
					.withId("lobo mau")
					.withReference(reference)
					.withSpecialization("maudade");
		assertSame(result, param);
		assertEquals(23, result.getDistance());
		assertEquals("lobo mau", result.getId());
		assertEquals(reference, result.getReference());
		assertEquals("maudade", result.getSpecialization());
	}
}
