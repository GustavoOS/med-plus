package com.medplus.factories;

import com.medplus.entities.scheduler.DayScheduler;
import com.medplus.entities.scheduler.DaySchedulerImpl;

public class DayScheduleFactory {
	public static DayScheduler make()
	{
		return new DaySchedulerImpl();
	}

}
