package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.medplus.factories.TestUtils;

class ProviderTest {
	HealthProvider hp;

	@Test
	void testGettersAndSetters() {
		hp = new Provider();
		hp.setLocal(new CoordinateDS(-23.1735709, -45.8423158));
		hp.setSpecialization("surgeon");
		hp.setName("João");
		hp.setId("74e50960-e065-4870-ab5a-c244aa757ca8");
		hp.setSocialMediaURL("https://www.instagram.com/neymarjr/");
		assertEquals("João", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
		assertEquals("74e50960-e065-4870-ab5a-c244aa757ca8", hp.getId());
	}

	@Test
	void testParameterizedConstructor() {
		hp = new Provider(
				"João",
				"https://www.instagram.com/neymarjr/",
				"surgeon",
				new CoordinateDS(-23.1735709, -45.8423158));
		assertEquals("João", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
	}

	@Test
	void testAppointmentGetterAndSetter()
	{
		ArrayList<Appointment> appointments =
				TestUtils.mountAppointmentList(
						"009255fb-1547-4494-a07b-ac816011d584",
						LocalDateTime.of(2021, 1, 30, 10, 0));

		hp = new Provider(
				"João",
				"https://www.instagram.com/neymarjr/",
				"surgeon",
				new CoordinateDS(-23.1735709, -45.8423158));
		hp.setId("f7369fbc-691d-4df8-b55f-2da43be30cc7");
		hp.setAppointments(appointments);
		assertSame(appointments,hp.getAppointments());
		assertEquals(3, hp.getAppointments().size());
	}
}
