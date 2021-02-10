package com.medplus.useCases.book;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.SchedulePresenterImpl;
import com.medplus.entities.Appointment;
import com.medplus.factories.AppointmentFactoryImpl;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class BookAppointmentUseCaseTest {

	BookAppointmentUseCase useCase;
	Appointment appointment;
	SchedulePresenterImpl presenter;
	ProviderGW providerGw;
	PatientGW patientGW;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new BookAppointmentUseCase();
		appointment = (new AppointmentFactoryImpl()).make();
		providerGw = new ProviderGW();
		patientGW = new PatientGW();
		presenter = new SchedulePresenterImpl();
		
		providerGw.setProviders(TestUtils.mountProviderList());
		patientGW.setPatients(TestUtils.mountPatientList());

		useCase.setPresenter(presenter);
		useCase.setProviderGW(providerGw);
		useCase.setPatientGW(patientGW);
		useCase.setDaySchedule(DayScheduleFactory.make());

		appointment.setPatientID("0c9dbc30-2874-4983-a0b7-6885c409ddbc");
		appointment.setProviderID("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		appointment.setDateTime(LocalDateTime.now().withHour(10).plusDays(15));
	}

	@Test
	void bookAppointment() {
		useCase.book(appointment);
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void bookAppointmentWithNullProviderIDShouldFail()
	{
		appointment.setProviderID(null);
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
	void bookAppointmentWithNotListedPatientIdShouldFail()
	{
		appointment.setPatientID("hello");
		useCase.book(appointment);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNoTimeShouldFail()
	{
		appointment.setDateTime(null);
		useCase.book(appointment);
		assertFailure();
	}

	@Test
	void bookConflictingProviderTimeShouldFail()
	{
		appointment.setPatientID("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		appointment.setDateTime(LocalDateTime.now().withHour(14).plusDays(1));
		useCase.book(appointment);
		assertFailure();
	}

	@Test
	void bookConflictingPatientTimeShouldFail()
	{
		appointment.setDateTime(LocalDateTime.now().withHour(10).plusDays(1));
		useCase.book(appointment);
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
	}

}
