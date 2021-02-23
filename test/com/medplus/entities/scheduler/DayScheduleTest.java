package com.medplus.entities.scheduler;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Appointment;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;

class DayScheduleTest {

	DayScheduler bd;
	ArrayList<Appointment> list;
	LocalDateTime dateTime;

	@BeforeEach
	void setUp(){
		bd = DayScheduleFactory.make();
		dateTime = LocalDateTime.now().withHour(14);
		list = TestUtils.mountProviderList(dateTime)
				.get(0)
				.getAppointments();
	}

	@Test
	void checkEveryHourAvailabilityForEmptyBusinessDay() {
		changeAppointmentDay(25);
		for(int i = 0; i < 9; i++)
			assertUnavailableHour(i);
		assertAvailableHour(9);
		assertAvailableHour(10);
		assertAvailableHour(11);
		assertUnavailableHour(12);
		for(int i = 13; i < 18; i++)
			assertAvailableHour(i);
		for(int i = 18; i < 24; i++)
			assertUnavailableHour(i);
	}


	@Test
	void testUnavailabilityOfConflictingHour()
	{
		assertAvailableHour(13);
		assertUnavailableHour(14);
		assertUnavailableHour(15);
		assertAvailableHour(16);
	}

	@Test
	void testNullArguments()
	{
		assertFalse(bd.isAvailable(null, list));
		assertFalse(bd.isAvailable(dateTime, null));
	}

	@Test
	void testAppointmentsOfDoctor()
	{
		list = TestUtils.mountProviderList().get(0).getAppointments();
		assertNotNull(list);
		assertTrue(bd.isAvailable(dateTime.withHour(9), list));
	}

	@Test
	void testConflictingDoctorAppointment()
	{
		list = TestUtils.mountPatientList().get(0).getAppointments();
		assertFalse(bd.isAvailable(dateTime.withHour(14).plusDays(1), list));
	}

	private void changeAppointmentDateTimeHour(int hour) {
		dateTime = dateTime.withHour(hour);
	}

	private void assertUnavailableHour(int hour) {
		changeAppointmentDateTimeHour(hour);
		assertFalse(bd.isAvailable(dateTime, list));
	}

	private void assertAvailableHour(int hour)
	{
		changeAppointmentDateTimeHour(hour);
		assertTrue(bd.isAvailable(dateTime, list));
	}

	private void changeAppointmentDay(int days) {
		dateTime = dateTime.plusDays(days);
	}
}
