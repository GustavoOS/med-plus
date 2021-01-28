package com.medplus.useCases;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.SchedulePresenterImpl;
import com.medplus.entities.AppointmentDS;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.ProviderGW;
import com.medplus.gateways.ScheduleGW;

class BookAppointmentUseCaseTest {

	BookAppointmentUseCase useCase;
	AppointmentDS appointment;
	LocalDateTime dateTime;
	SchedulePresenterImpl presenter;
	ProviderGW pGw;
	ScheduleGateway sGw;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new BookAppointmentUseCase();
		appointment = new AppointmentDS();
		dateTime = LocalDateTime.of(2021, 01, 28, 10, 0);
		pGw = new ProviderGW();
		sGw = new ScheduleGW();
		presenter = new SchedulePresenterImpl();
		
		pGw.setProviders(TestUtils.mountProviderList());
		useCase.setFilter(TestUtils.mountIDFilter());
		useCase.setPresenter(presenter);
		useCase.setProviderGW(pGw);
		useCase.setScheduleGW(sGw);
		
		appointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
	}

	@Test
	void bookAppointment() {
		appointment.setProviderID("7b11fdbb-0894-4e4b-afaf-880738c84f4c");

		useCase.book(appointment, dateTime);
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void bookAppointmentWithNullProviderIDShouldFail()
	{
		useCase.book(appointment, dateTime);
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void bookAppointmentWithNotListedProviderIdShouldFail()
	{
		appointment.setProviderID("hello");
		useCase.book(appointment, dateTime);
		assertEquals("fail", presenter.getStatus());
	}
}
