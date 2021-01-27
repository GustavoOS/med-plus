import java.time.LocalDate;
import java.util.ArrayList;

public interface ScheduleGateway {
	public ArrayList<AppointmentDS> getProviderSchedule(String providerId, LocalDate date);
	public void setSchedule(String providerId, LocalDate date, ArrayList<AppointmentDS> appointments);
}
