package com.medplus.factories;

import com.medplus.entities.DayScheduler;
import com.medplus.entities.DaySchedulerImpl;

public class DayScheduleFactory {
	public static DayScheduler make()
	{
		return new DaySchedulerImpl();
	}

}
