package com.medplus.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.TestUtils;

class ClonerTest {

	ArrayList<Appointment> rawAppointmentList;

	@BeforeEach
	void setup()
	{
		rawAppointmentList =
				TestUtils.mountAppointmentList(
						"062227ac-50fe-4967-9fb1-13d8968d7ab0",
						"f60579ed-6de3-49ee-9229-0bb027d5b74b",
						LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
	}

	@Test
	void testAppointmentClone() {
		verifySingleAppointmentClone(Cloner.cloneAppointment(rawAppointmentList.get(0)), 0);
	}

	@Test
	void copiedListShouldHaveDifferentReferences()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Cloner.cloneProviderList(raw);
		assertEquals(4, result.size());
		for(int i = 0; i < 4; i++)
		{
			assertNotSame(result.get(i), raw.get(i));
			assertEquals(raw.get(i).getName(), result.get(i).getName());
		}
	}

	@Test
	void changeInCopiedListShouldNotChangeTheOriginal()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Cloner.cloneProviderList(raw);
		result.get(0).setName("Corleone");
		assertEquals("João", raw.get(0).getName());
		assertEquals("Corleone", result.get(0).getName());
	}

	@Test
	void testNullClones()
	{
		assertNotNull(new Cloner());
		assertNull(Cloner.cloneAppointment(null));
		assertNull(Cloner.cloneAppointmentList(null));
		assertNull(Cloner.cloneProvider(null));
		assertNull(Cloner.cloneProviderList(null));
	}

	@Test
	void cloneAppointmentList() {
		ArrayList<Appointment> appointments = Cloner.cloneAppointmentList(rawAppointmentList);
		assertNotNull(appointments);
		assertEquals(rawAppointmentList.size(), appointments.size());
		for(int i = 0; i < appointments.size(); i++)
			verifySingleAppointmentClone(appointments.get(i), i);
		assertNotSame(appointments, rawAppointmentList);
	}




	private void verifySingleAppointmentClone(Appointment appointment, int index) {
		assertNotNull(appointment);
		assertEquals(rawAppointmentList.get(index).getDateTime(), appointment.getDateTime());
		assertNotSame(rawAppointmentList.get(index), appointment);
	}
}
