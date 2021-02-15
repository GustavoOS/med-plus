package com.medplus.entities.appointment;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.Appointment;

class AppointmentImplTest {
	AppointmentImpl appointment;

	@BeforeEach
	void setUp() throws Exception {
		appointment = new AppointmentImpl();
	}

	@Test
	void testWithPeerID() {
		Appointment result = appointment.withPeerID("a9f0b790-85ea-47d3-ad9e-c03d47b1f167");
		assertEquals("a9f0b790-85ea-47d3-ad9e-c03d47b1f167", result.getPeerID());
		assertSame(result, appointment);
	}

	@Test
	void testWithDate()
	{
		LocalDateTime now = LocalDateTime.now();
		Appointment result = appointment.withDateTime(now);
		assertSame(result, appointment);
		assertEquals(now, result.getDateTime());
	}
}
