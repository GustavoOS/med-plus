import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookControllerTest {

	BookController controller;
	BookAppointmentUseCase useCase;
	SchedulePresenterImpl presenter;
	private ProviderGW pGw;
	private ScheduleGW sGw;

	
	@BeforeEach
	void setUp() throws Exception {
		controller = new BookController();
		useCase = new BookAppointmentUseCase();
		useCase.setFilter(TestUtils.mountFilter());
		pGw = new ProviderGW();
		sGw = new ScheduleGW();
		presenter = new SchedulePresenterImpl();
		
		pGw.setProviders(TestUtils.mountProviderList());

		useCase.setFilter(TestUtils.mountFilter());
		useCase.setPresenter(presenter);
		useCase.setProviderGW(pGw);
		useCase.setScheduleGW(sGw);

		controller.setUseCase(useCase);
	}

	@Test
	void testSuccess() {
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f",
									"7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		controller.setDateTime(LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void testNullProvider() {
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f", null);
		controller.setDateTime(LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void testUnknownProvider()
	{
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f",
				"hello");
		controller.setDateTime(LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("fail", presenter.getStatus());
	}
}
