package com.medplus.entities;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessDayTest {

	BusinessDay bd;
	AppointmentDS appointment;

	@BeforeEach
	void setUp(){
		bd = new BusinessDay();
		appointment = new AppointmentDS();
		appointment.setProviderID("a69529e1-69a4-4cef-9914-660901dbe2e9");
		appointment.setPatientID("cb0e17f2-a988-43af-9ef0-63e6b9fc41f5");
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
		bd.scheduleAppointment(10, appointment);
		assertFalse(bd.isAvailable(10));
		assertNotNull(bd.getDay()[1]);
		assertSame(appointment, bd.getDay()[1]);
		assertTrue(bd.isAvailable(15));
		bd.scheduleAppointment(15, appointment);
		assertFalse(bd.isAvailable(15));
		assertNotNull(bd.getDay()[5]);
		assertSame(appointment, bd.getDay()[5]);
	}

	@Test
	void scheduleOnAMorningReservedHourShouldNotOverrideSchedule()
	{
		bd.scheduleAppointment(10, appointment);
		AppointmentDS newAppointment = new AppointmentDS();
		newAppointment.setProviderID("12d98160-0a3c-4c61-a862-cdc5e03bd8ab");
		newAppointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
		bd.scheduleAppointment(10, newAppointment);
		assertSame(appointment, bd.getDay()[1]);
		assertEquals("a69529e1-69a4-4cef-9914-660901dbe2e9", bd.getDay()[1].getProviderID());
		assertEquals("cb0e17f2-a988-43af-9ef0-63e6b9fc41f5", bd.getDay()[1].getPatientID());
	}

	@Test
	void scheduleOnAfternoonReservedHourShouldNotOverrideSchedule()
	{
		bd.scheduleAppointment(14, appointment);
		assertFalse(bd.isAvailable(14));
		AppointmentDS newAppointment = new AppointmentDS();
		newAppointment.setProviderID("12d98160-0a3c-4c61-a862-cdc5e03bd8ab");
		newAppointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
		bd.scheduleAppointment(14, newAppointment);
		assertSame(appointment, bd.getDay()[4]);
	}

	@Test
	void scheduleAtNoonShouldFail()
	{
		bd.scheduleAppointment(12, appointment);
		assertEmptyDay();
	}

	@Test
	void scheduleBeforeOrAfterBHourShouldNotBookAppointment()
	{
		bd.scheduleAppointment(7, appointment);
		bd.scheduleAppointment(12, appointment);
		bd.scheduleAppointment(23, appointment);
		assertEmptyDay();
	}

	@Test
	void testDaySetter()
	{
		ArrayList<AppointmentDS> apps = new ArrayList<AppointmentDS>();
		apps.add(appointment);
		bd.setDay(apps);
		assertFalse(bd.isAvailable(9));
		assertNull(bd.getDay()[1]);
		assertTrue(bd.isAvailable(10));
		assertSame(appointment, bd.getDay()[0]);
		assertTrue(bd.isAvailable(17));
		bd.scheduleAppointment(17, appointment);
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
		ArrayList<AppointmentDS> appointments;
		bd.scheduleAppointment(10, appointment);
		bd.scheduleAppointment(17, appointment);
		bd.scheduleAppointment(11, appointment);
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
}
