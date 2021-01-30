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

	@BeforeEach
	void setUp(){
		bd = DayScheduleFactory.make();
		appointment = TestUtils.createAppointment();
		appointment.setProviderID("a69529e1-69a4-4cef-9914-660901dbe2e9");
		appointment.setPatientID("cb0e17f2-a988-43af-9ef0-63e6b9fc41f5");
		appointment.setDateTime(LocalDateTime.of(2021, 01, 28, 10, 0));
	}

	@Test
	void checkEveryHourAvailabilityForEmptyBusinessDay() {
		for(int i = 0; i < 9; i++)
			assertFalse(bd.isAvailable(i));
		assertTrue(bd.isAvailable(9));
		assertTrue(bd.isAvailable(10));
		assertTrue(bd.isAvailable(11));
		assertFalse(bd.isAvailable(12));
		for(int i = 13; i < 18; i++)
			assertTrue(bd.isAvailable(i));
		for(int i = 18; i < 25; i++)
			assertFalse(bd.isAvailable(i));

	}

	@Test
	void afterScheduleTheHourShouldBeUnavailableForBooking()
	{
		assertTrue(bd.isAvailable(10));
		bd.scheduleAppointment(appointment);
		assertFalse(bd.isAvailable(10));
		assertNotNull(bd.getDay()[1]);
		assertSame(appointment, bd.getDay()[1]);
		assertTrue(bd.isAvailable(15));
		changeAppointmentDateTimeHour(15);
		bd.scheduleAppointment(appointment);
		assertFalse(bd.isAvailable(15));
		assertNotNull(bd.getDay()[5]);
		assertSame(appointment, bd.getDay()[5]);
	}

	@Test
	void scheduleOnAMorningReservedHourShouldNotOverrideSchedule()
	{
		bd.scheduleAppointment(appointment);
		Appointment newAppointment = TestUtils.createAppointment();
		newAppointment.setProviderID("12d98160-0a3c-4c61-a862-cdc5e03bd8ab");
		newAppointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
		newAppointment.setDateTime(appointment.getDateTime().withHour(10));
		bd.scheduleAppointment(newAppointment);
		assertSame(appointment, bd.getDay()[1]);
		assertEquals("a69529e1-69a4-4cef-9914-660901dbe2e9", bd.getDay()[1].getProviderID());
		assertEquals("cb0e17f2-a988-43af-9ef0-63e6b9fc41f5", bd.getDay()[1].getPatientID());
	}

	@Test
	void scheduleOnAfternoonReservedHourShouldNotOverrideSchedule()
	{
		changeAppointmentDateTimeHour(14);
		bd.scheduleAppointment(appointment);
		assertFalse(bd.isAvailable(14));
		Appointment newAppointment = TestUtils.createAppointment();
		newAppointment.setProviderID("12d98160-0a3c-4c61-a862-cdc5e03bd8ab");
		newAppointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
		newAppointment.setDateTime(appointment.getDateTime().withHour(14));
		bd.scheduleAppointment(newAppointment);
		assertSame(appointment, bd.getDay()[4]);
	}

	@Test
	void scheduleAtNoonShouldFail()
	{
		changeAppointmentDateTimeHour(12);
		bd.scheduleAppointment(appointment);
		assertEmptyDay();
	}

	@Test
	void scheduleBeforeOrAfterBHourShouldNotBookAppointment()
	{
		changeAppointmentDateTimeHour(7);
		bd.scheduleAppointment(appointment);
		changeAppointmentDateTimeHour(12);
		bd.scheduleAppointment(appointment);
		changeAppointmentDateTimeHour(23);
		bd.scheduleAppointment(appointment);
		assertEmptyDay();
	}

	@Test
	void testDaySetter()
	{
		ArrayList<Appointment> apps = new ArrayList<Appointment>();
		apps.add(appointment);
		bd.setDay(apps);
		assertFalse(bd.isAvailable(9));
		assertNull(bd.getDay()[1]);
		assertTrue(bd.isAvailable(10));
		assertSame(appointment, bd.getDay()[0]);
		assertTrue(bd.isAvailable(17));
		changeAppointmentDateTimeHour(17);
		bd.scheduleAppointment(appointment);
		assertFalse(bd.isAvailable(17));
	}

	@Test
	void testDaySetterWithNullAppointment()
	{
		bd.setDay(null);
		assertEmptyDay();
	}

	@Test
	void testDayGetter()
	{
		ArrayList<Appointment> appointments;
		bd.scheduleAppointment(appointment);
		changeAppointmentDateTimeHour(17);
		bd.scheduleAppointment(appointment);
		changeAppointmentDateTimeHour(11);
		bd.scheduleAppointment(appointment);
		appointments = bd.getDayAsList();
		assertNull(appointments.get(0));
		assertSame(appointment, appointments.get(1));
		assertSame(appointment, appointments.get(2));
		assertNull(appointments.get(6));
		assertSame(appointment, appointments.get(7));
		assertEquals(8, appointments.size());
	}

	private void assertEmptyDay() {
		for(int i = 0; i < 8; i++)
			assertNull(bd.getDay()[i]);
	}

	private void changeAppointmentDateTimeHour(int hour) {
		appointment.setDateTime(appointment.getDateTime().withHour(hour));
	}
}
