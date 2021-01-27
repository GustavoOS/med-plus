import java.time.LocalDateTime;

public interface Bookable {
	void book(AppointmentDS appointment, LocalDateTime date);
}
