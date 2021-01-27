import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookAppointmentUseCase implements Bookable {
	private ScheduleGateway scheduleGW;
	private ProviderGateway providerGW;
	private Filter filter;
	private SchedulePresenter presenter;

	@Override
	public void book(AppointmentDS appointment, LocalDateTime date) {
		if(!providerIsValid(appointment))
		{
			presenter.fail();
			return;
		}
		BusinessDay day = new BusinessDay();
		day.setDay(scheduleGW.getProviderSchedule(appointment.getProviderID(), date.toLocalDate()));
		day.scheduleAppointment(date.getHour(), appointment);
		scheduleGW.setSchedule(appointment.getProviderID(), date.toLocalDate(), day.getDayAsList());
		presenter.succeed();
	}

	private Boolean providerIsValid(AppointmentDS appointment)
	{
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
}
