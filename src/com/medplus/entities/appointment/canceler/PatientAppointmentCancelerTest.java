package com.medplus.entities.appointment.canceler;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.Appointment;
import com.medplus.factories.TestUtils;

class PatientAppointmentCancelerTest {
	ArrayList<Appointment> appointments;
	PatientAppointmentCanceler canceler;
	LocalDateTime baseDate;
	
	@BeforeEach
	void setUp() throws Exception {
		baseDate = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).withNano(0);
		appointments = TestUtils.
				mountAppointmentList(
						"b9d4ec7e-7cda-4d52-a986-e9dd2ea4343d",
						"0d8306b3-20a9-47c0-95f4-8a764699ba16",
						baseDate);
		canceler = new PatientAppointmentCanceler();
	}

	@Test
	void cancelFirst() {
		assertEquals(3, appointments.size());
		canceler.cancel(appointments, baseDate);
		assertEquals(2, appointments.size());
		assertEquals("b9d4ec7e-7cda-4d52-a986-e9dd2ea4343d",
				canceler.getCanceledAppointmentTargetUserID());
		assertNotEquals(
				appointments.get(0).getDateTime()
							.withMinute(0).withSecond(0).withNano(0),
				baseDate);
	}

	@Test
	void cancelNullAppointments()
	{
		canceler.cancel(null, baseDate);
		assertCancelFailure();
	}

	@Test
	void cancelNullDate()
	{
		canceler.cancel(appointments, null);
		assertCancelFailure();
	}

	@Test
	void cancelNotFoundDate()
	{
		canceler.cancel(appointments, baseDate.minusYears(20));
		assertCancelFailure();
	}

	private void assertCancelFailure() {
		assertNull(canceler.getCanceledAppointmentTargetUserID());
	}
}
