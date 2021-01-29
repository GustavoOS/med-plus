package com.medplus.useCases;
import java.util.ArrayList;

import com.medplus.entities.Appointment;
import com.medplus.entities.DaySchedule;
import com.medplus.entities.Filter;
import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;

public class BookAppointmentUseCase implements Bookable {
	private ScheduleGateway scheduleGW;
	private ProviderGateway providerGW;
	private Filter filter;
	private SchedulePresenter presenter;
	private DaySchedule daySchedule;

	@Override
	public void book(Appointment appointment) {
		if(!appointmentIsValid(appointment))
		{
			presenter.fail();
			return;
		}

		daySchedule.setDay(scheduleGW.getProviderSchedule(appointment.getProviderID(), appointment.getDateTime().toLocalDate()));
		daySchedule.scheduleAppointment(appointment);
		scheduleGW.setSchedule( appointment.getProviderID(),
								appointment.getDateTime().toLocalDate(),
								daySchedule.getDayAsList());
		presenter.succeed();
	}

	private Boolean appointmentIsValid(Appointment appointment)
	{
		if(appointment.getDateTime() == null)
			return false;
		FilterParameter param = new FilterParameter();
		param.id = appointment.getProviderID();
		ArrayList<HealthProvider> providers = filter.filter(providerGW.list(), param);
		return providers.size() != 0 && providers.get(0).getId().equals(appointment.getProviderID());
	}

	// Setters

	public void setScheduleGW(ScheduleGateway scheduleGW) {
		this.scheduleGW = scheduleGW;
	}

	public void setProviderGW(ProviderGateway providerGW) {
		this.providerGW = providerGW;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setPresenter(SchedulePresenter presenter) {
		this.presenter = presenter;
	}

	public void setDaySchedule(DaySchedule daySchedule) {
		this.daySchedule = daySchedule;
	}

}
