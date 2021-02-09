package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.Appointment;
import com.medplus.factories.TestUtils;

class CancelAppointmentPresenterImplTest {

	CancelAppointmentPresenterImpl presenter;
	ArrayList<Appointment> appointments;

	@BeforeEach
	void setUp() throws Exception {
		presenter = new CancelAppointmentPresenterImpl();
		appointments = TestUtils.mountAppointmentList(
				"973ee2f7-ec81-4855-975e-7b05b5970f6f",
				"e8c6b544-0339-4c6f-a024-1a721de23fba",
				LocalDateTime.of(2021, 2, 8, 22, 34));
	}

	@Test
	void testFailure() {
		presenter.fail();
		assertNotNull(presenter.getStatus());
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void assertSuccess()
	{
		presenter.succeed(appointments);
		assertEquals(3, presenter.getList().size());
		assertSame(presenter.getList(), appointments);
	}

}
