package com.medplus.factories;

import com.medplus.entities.DaySchedule;
import com.medplus.entities.DayScheduleImpl;

public class DayScheduleFactory {
	public static DaySchedule make()
	{
		return new DayScheduleImpl();
	}

}
