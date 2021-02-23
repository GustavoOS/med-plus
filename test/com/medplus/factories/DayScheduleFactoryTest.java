package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.medplus.entities.scheduler.DayScheduler;

class DayScheduleFactoryTest{

	@Test
	void createdShouldBeInstanceofDaySchedule() {
		assertTrue(DayScheduleFactory.make() instanceof DayScheduler);
	}

	@Test
	void createdShouldNotBeNull()
	{
		assertNotNull(DayScheduleFactory.make());
	}
}
