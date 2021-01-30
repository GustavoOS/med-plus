package com.medplus.factories;

import com.medplus.entities.DayScheduler;
import com.medplus.entities.DayScheduleImpl;

public class DayScheduleFactory {
	public static DayScheduler make()
	{
		return new DayScheduleImpl();
	}

}
