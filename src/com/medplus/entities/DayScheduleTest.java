package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;

class DayScheduleTest {

	DayScheduler bd;
	Appointment appointment;
	ArrayList<Appointment> list;

	@BeforeEach
	void setUp(){
		bd = DayScheduleFactory.make();
		bd.setFilter(new AppointmentFilterAll());
		appointment = TestUtils.createAppointment();
		appointment.setProviderID("da2ed3e9-566b-4521-8002-6e15f6f9958d");
		appointment.setPatientID("cb0e17f2-a988-43af-9ef0-63e6b9fc41f5");
		appointment.setDateTime(LocalDateTime.of(2021, 01, 28, 10, 0));
		list = TestUtils.mountAppointmentList(
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"f7369fbc-691d-4df8-b55f-2da43be30cc7",
				LocalDateTime.of(2021, 01, 28, 14, 0));
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
		assertFalse(bd.isAvailable(appointment, null));
		appointment.setDateTime(null);
		assertFalse(bd.isAvailable(appointment, list));
	}
	private void changeAppointmentDateTimeHour(int hour) {
		appointment.setDateTime(appointment.getDateTime().withHour(hour));
	}


	private void assertUnavailableHour(int hour) {
		changeAppointmentDateTimeHour(hour);
		assertFalse(bd.isAvailable(appointment, list));
	}

	private void assertAvailableHour(int hour)
	{
		changeAppointmentDateTimeHour(hour);
		assertTrue(bd.isAvailable(appointment, list));
	}

	private void changeAppointmentDay(int day) {
		appointment.setDateTime(appointment.getDateTime().withDayOfMonth(day));
	}
}
