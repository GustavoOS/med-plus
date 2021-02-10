package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.useCases.PatientAvailableDataDS;

class AttendancePresenterImplTest {

	AttendancePresenterImpl presenter;
	PatientAvailableDataDS data;

	@BeforeEach
	void setUp() throws Exception {
		presenter = new AttendancePresenterImpl();
		data = new PatientAvailableDataDS();
	}

	@Test
	void testSuccess() {
		presenter.succeed(data);
		assertEquals("success", presenter.getStatus());
		assertSame(data, presenter.getData());
	}

	@Test
	void testFailure()
	{
		presenter.fail();
		assertNull(presenter.getData());
		assertEquals("fail", presenter.getStatus());
	}

}
