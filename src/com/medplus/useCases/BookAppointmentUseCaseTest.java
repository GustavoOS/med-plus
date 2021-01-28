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
	SchedulePresenterImpl presenter;
	ProviderGW pGw;
	ScheduleGateway sGw;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new BookAppointmentUseCase();
		appointment = new AppointmentDS();
		pGw = new ProviderGW();
		sGw = new ScheduleGW();
		presenter = new SchedulePresenterImpl();
		
		pGw.setProviders(TestUtils.mountProviderList());
		useCase.setFilter(TestUtils.mountIDFilter());
		useCase.setPresenter(presenter);
		useCase.setProviderGW(pGw);
		useCase.setScheduleGW(sGw);
		
		appointment.setPatientID("ee7393de-9386-47d6-ab97-cdbdf4673691");
		appointment.setDateTime(LocalDateTime.of(2021, 01, 28, 10, 0));
	}

	@Test
	void bookAppointment() {
		appointment.setProviderID("7b11fdbb-0894-4e4b-afaf-880738c84f4c");

		useCase.book(appointment);
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void bookAppointmentWithNullProviderIDShouldFail()
	{
		useCase.book(appointment);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNotListedProviderIdShouldFail()
	{
		appointment.setProviderID("hello");
		useCase.book(appointment);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNoTimeShouldFail()
	{
		appointment.setProviderID("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		appointment.setDateTime(null);
		useCase.book(appointment);
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
	}

}
