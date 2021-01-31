package com.medplus.adapter.interfaces;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.AppointmentFactoryImpl;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.BookAppointmentUseCase;

class BookControllerTest {

	BookController controller;
	BookAppointmentUseCase useCase;
	SchedulePresenterImpl presenter;
	private ProviderGW providerGw;
	private PatientGW patientGW;

	@BeforeEach
	void setUp() throws Exception {
		controller = new BookController();
		useCase = new BookAppointmentUseCase();
		providerGw = new ProviderGW();
		patientGW = new PatientGW();
		presenter = new SchedulePresenterImpl();
		
		providerGw.setProviders(TestUtils.mountProviderList());
		patientGW.setPatients(TestUtils.mountPatientList());

		useCase.setPresenter(presenter);
		useCase.setProviderGW(providerGw);
		useCase.setPatientGW(patientGW);
		useCase.setDaySchedule(DayScheduleFactory.make());

		controller.setAppointmentFactory(new AppointmentFactoryImpl());
		controller.setUseCase(useCase);
	}

	@Test
	void testSuccess() {
		controller.setAppointment(  "da2ed3e9-566b-4521-8002-6e15f6f9958d",
									"0c9dbc30-2874-4983-a0b7-6885c409ddbc",
									LocalDateTime.of(2021, 01, 21, 10, 0));
		assertNotNull(controller.getAppointment());
		assertEquals("da2ed3e9-566b-4521-8002-6e15f6f9958d", controller.getAppointment().getProviderID());
		assertEquals("0c9dbc30-2874-4983-a0b7-6885c409ddbc", controller.getAppointment().getPatientID());
		assertEquals(LocalDateTime.of(2021, 01, 21, 10, 0),controller.getAppointment().getDateTime());
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
