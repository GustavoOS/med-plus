package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.TestUtils;


class UtilsTest {
	private ArrayList<Appointment> appointments;
	private LocalDateTime dateTime;

	@BeforeEach
	void filterSetup()
	{
		dateTime = LocalDateTime.now().withHour(14);
		 appointments = TestUtils.
				mountAppointmentList(
						"0d8306b3-20a9-47c0-95f4-8a764699ba16",
						"0fe73670-7db1-4e46-9b32-8529dfb45752",
						dateTime);
	}

	@Test
	void testDistanceCalculation() {
		CoordinateDS a = new CoordinateDS(53.32055555555556, -1.7297222222222221);
		CoordinateDS b = new CoordinateDS();
		b.latitude = 53.31861111111111;
		b.longitude = -1.6997222222222223;
		
		assertEquals(2.0043, Utils.calculateDistance(a, b), 0.001);
	}

	@Test
	void calculateAge() {
		assertEquals(20, Utils.calculateAge(LocalDate.now().minusYears(20)));
	}

	@Test
	void nullBirthday()
	{
		assertEquals(0, Utils.calculateAge(null));
	}

	@Test
	void testConstructorToFillCoverage()
	{
		assertNotNull(new Utils());
	}

	@Test
	void testFilterWithNullDate()
	{
		assertNull(Utils.findFirstAppointmentWithDateTime(appointments, null));
	}

	@Test
	void testFilterWithNullAppointments()
	{
		assertNull(Utils.findFirstAppointmentWithDateTime(null, dateTime));
	}

	@Test
	void testFilterWithoutTimeInList()
	{
		assertNull(Utils.findFirstAppointmentWithDateTime(appointments, dateTime.plusYears(10)));
	}

	@Test
	void testFilterWithTimeInList()
	{
		Appointment result = Utils.findFirstAppointmentWithDateTime(appointments, dateTime.plusHours(1));
		assertNotNull(result);
		assertEquals("0d8306b3-20a9-47c0-95f4-8a764699ba16", result.getProviderID());
		assertEquals("0fe73670-7db1-4e46-9b32-8529dfb45752", result.getPatientID());
	}

	@Test
	void testIf9amIsInList()
	{
		appointments = TestUtils.mountAppointmentList(
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"f7369fbc-691d-4df8-b55f-2da43be30cc7",
				LocalDateTime.of(2021, 01, 28, 14, 0));
		Appointment result = Utils.findFirstAppointmentWithDateTime(appointments,
				LocalDateTime.of(2021, 01, 28, 9, 0));
		assertNull(result);
	}
}
