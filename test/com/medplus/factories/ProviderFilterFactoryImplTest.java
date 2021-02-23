package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.provider.filters.FilterImpl;

class ProviderFilterFactoryImplTest {

	private ProviderFilterFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new ProviderFilterFactoryImpl();		
	}

	@Test
	void makeFilterAll()
	{
		assertTrue(factory.Make() instanceof FilterImpl);
	}
}
