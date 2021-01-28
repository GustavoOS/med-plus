package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.filters.FilterAll;
import com.medplus.entities.filters.FilterFirst;

class FilterFactoryImplTest {

	private FilterFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new FilterFactoryImpl();		
	}

	@Test
	void makeFilterFirst() {
		assertTrue(factory.Make("first") instanceof FilterFirst);
	}

	@Test
	void makeFilterAll()
	{
		assertTrue(factory.Make("all") instanceof FilterAll);
	}
}
