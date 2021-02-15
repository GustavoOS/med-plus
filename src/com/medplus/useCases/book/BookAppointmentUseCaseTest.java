package com.medplus.useCases.book;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.SchedulePresenterImpl;
import com.medplus.factories.AppointmentFactoryImpl;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.AppointmentFactory;

class BookAppointmentUseCaseTest {

	BookAppointmentUseCase useCase;
	SchedulePresenterImpl presenter;
	ProviderGW providerGw;
	PatientGW patientGW;
	AppointmentFactory factory;

	private LocalDateTime dateTime;
	private String providerID, patientID;

	@BeforeEach
	void setUp() throws Exception {
		prepareAttributes();
		
		providerGw.setProviders(TestUtils.mountProviderList());
		patientGW.setPatients(TestUtils.mountPatientList());

		prepareUseCase();

		patientID = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		providerID = "7b11fdbb-0894-4e4b-afaf-880738c84f4c";
		dateTime = LocalDateTime.now().withHour(10).plusDays(15);
	}

	private void prepareUseCase() {
		useCase.setPresenter(presenter);
		useCase.setProviderGW(providerGw);
		useCase.setPatientGW(patientGW);
		useCase.setDaySchedule(DayScheduleFactory.make());
		useCase.setAppointmentFactory(factory);
	}

	private void prepareAttributes() {
		useCase = new BookAppointmentUseCase();
		providerGw = new ProviderGW();
		patientGW = new PatientGW();
		presenter = new SchedulePresenterImpl();
		factory = new AppointmentFactoryImpl();
	}

	@Test
	void bookAppointment() {
		useCase.book(providerID, patientID, dateTime);
		assertEquals("success", presenter.getStatus());
	}

	@Test
	void bookAppointmentWithNullProviderIDShouldFail()
	{
		useCase.book(null, patientID, dateTime);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNotListedProviderIdShouldFail()
	{
		useCase.book("hello", patientID, dateTime);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNotListedPatientIdShouldFail()
	{
		useCase.book(providerID, "hello", dateTime);
		assertFailure();
	}

	@Test
	void bookAppointmentWithNoTimeShouldFail()
	{
		useCase.book(providerID, patientID, null);
		assertFailure();
	}

	@Test
	void bookConflictingProviderTimeShouldFail()
	{
		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		dateTime = LocalDateTime.now().withHour(14).plusDays(1);
		useCase.book(providerID, patientID, dateTime);
		assertFailure();
	}

	@Test
	void bookConflictingPatientTimeShouldFail()
	{
		dateTime = LocalDateTime.now().withHour(10).plusDays(1);
		useCase.book(providerID, patientID, dateTime);
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
	}

}
