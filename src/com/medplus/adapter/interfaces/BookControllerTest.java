package com.medplus.adapter.interfaces;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.AppointmentFactoryImpl;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.ProviderGW;
import com.medplus.gateways.ScheduleGW;
import com.medplus.useCases.BookAppointmentUseCase;

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
		pGw = new ProviderGW();
		sGw = new ScheduleGW();
		presenter = new SchedulePresenterImpl();
		
		pGw.setProviders(TestUtils.mountProviderList());

		useCase.setFilter(TestUtils.mountIDFilter());
		useCase.setPresenter(presenter);
		useCase.setProviderGW(pGw);
		useCase.setScheduleGW(sGw);
		useCase.setDaySchedule(DayScheduleFactory.make());

		controller.setAppointmentFactory(new AppointmentFactoryImpl());
		controller.setUseCase(useCase);
	}

	@Test
	void testSuccess() {
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f",
									"7b11fdbb-0894-4e4b-afaf-880738c84f4c",
									LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void testNullProvider() {
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f",
									null,
									LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void testUnknownProvider()
	{
		controller.setAppointment(  "da0ea328-fe04-49a7-9907-d9948bb8be0f",
									"hello",
									LocalDateTime.of(2021, 03, 31, 10, 0));
		controller.schedule();
		assertEquals("fail", presenter.getStatus());
	}
}
