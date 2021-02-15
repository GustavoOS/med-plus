package com.medplus.adapter.interfaces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Exam;
import com.medplus.factories.TestUtils;

class ManageExamPresenterImplTest {
	ManageExamPresenterImpl presenter;
	Exam exam;

	@BeforeEach
	void setUp() throws Exception {
		exam = TestUtils.mountExamList().get(0);
		presenter = new ManageExamPresenterImpl();
	}

	@Test
	void testSuccess() {
		presenter.succeed(exam);
		assertNotNull(presenter.getResult());
		assertNotNull(presenter.getStatus());
		assertEquals("success", presenter.getStatus());
		assertSame(exam, presenter.getResult());
	}

	@Test
	void testFailure()
	{
		presenter.fail();
		assertNull(presenter.getResult());
		assertNotNull(presenter.getStatus());
		assertEquals("fail", presenter.getStatus());
	}

}
