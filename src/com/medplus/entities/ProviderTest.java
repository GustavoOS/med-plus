package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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
	void onCloneIdShouldBeCopied()
	{
		hp = new Provider();
		hp.setLocal(new CoordinateDS(-23.1735709, -45.8423158));
		hp.setSpecialization("surgeon");
		hp.setName("João");
		hp.setId("74e50960-e065-4870-ab5a-c244aa757ca8");
		hp.setSocialMediaURL("https://www.instagram.com/neymarjr/");

		assertEquals("74e50960-e065-4870-ab5a-c244aa757ca8", hp.clone().getId());
		assertNotSame(hp, hp.clone());
	}

	@Test
	void testAppointmentGetterAndSetter()
	{
		HashMap<LocalDate, ArrayList<Appointment>> appointments = new HashMap<LocalDate, ArrayList<Appointment>>();
		Appointment a = TestUtils.createAppointment();
		ArrayList<Appointment> as = new ArrayList<Appointment>();
		as.add(a);
		appointments.put(LocalDate.now(), as);

		hp = new Provider(
				"João",
				"https://www.instagram.com/neymarjr/",
				"surgeon",
				new CoordinateDS(-23.1735709, -45.8423158));
		hp.setAppointments(appointments);
		assertSame(appointments,hp.getAppointments());
		assertEquals(1, hp.getAppointments().size());
	}
}
