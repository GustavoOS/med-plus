package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Appointment;
import com.medplus.useCases.AppointmentFactory;

class AppointmentFactoryImplTest {

	AppointmentFactory factory;
	@BeforeEach
	void setUp() throws Exception {
		factory = new AppointmentFactoryImpl();
	}

	@Test
	void testInstance() {
		assertTrue(factory.make() instanceof Appointment);
	}

	@Test
	void testNotNull()
	{
		assertNotNull(factory.make());
	}

}
