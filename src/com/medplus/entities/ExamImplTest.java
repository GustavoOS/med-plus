package com.medplus.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExamImplTest {

	Exam exam;
	LocalDateTime dateTime;

	@BeforeEach
	void setUp() throws Exception {
		exam = new ExamImpl();
		dateTime = LocalDateTime.now();
	}

	@Test
	void test() {
		exam
			.withId("42b76ac2-9e63-4afc-80ea-94137bbeb4cc")
			.withTitle("electrocardiogram")
			.withInsertionDateTime(dateTime);
		assertEquals("electrocardiogram", exam.getTitle());
		assertEquals("42b76ac2-9e63-4afc-80ea-94137bbeb4cc", exam.getId());
		assertEquals(dateTime, exam.getInsertionDateTime());
	}

}
