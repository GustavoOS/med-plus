package com.medplus.entities.exam.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Exam;
import com.medplus.entities.exam.changer.ExamFactory;
import com.medplus.entities.exam.changer.impl.ExamFactoryImpl;

class ExamFactoryImplTest {

	ExamFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new ExamFactoryImpl();
	}

	@Test
	void test() {
		assertTrue(factory.make() instanceof Exam);
		assertNotNull(factory.make());
	}

	@Test
	void testListCreation()
	{
		assertNotNull(factory.makeList());
		assertTrue(factory.makeList() instanceof ArrayList);
		assertEquals(0, factory.makeList().size());
	}
}
