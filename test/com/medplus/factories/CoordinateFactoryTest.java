package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Coordinate;
import com.medplus.useCases.CoordinateFactory;

class CoordinateFactoryTest {

	CoordinateFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new CoordinateFactoryImpl();
	}

	@Test
	void test() {
		assertTrue(factory.make() instanceof Coordinate);
	}

	@Test
	void shouldNotBeNull()
	{
		assertNotNull(factory.make());
	}
}
