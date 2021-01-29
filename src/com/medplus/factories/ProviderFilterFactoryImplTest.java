package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.provider.filters.FilterAll;
import com.medplus.entities.provider.filters.FilterFirst;

class ProviderFilterFactoryImplTest {

	private ProviderFilterFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new ProviderFilterFactoryImpl();		
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
