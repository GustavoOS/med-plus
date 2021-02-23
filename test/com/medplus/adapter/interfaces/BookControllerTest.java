package com.medplus.adapter.interfaces;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Appointment;
import com.medplus.factories.AppointmentFactoryImpl;
import com.medplus.factories.DayScheduleFactory;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.UserGateway;
import com.medplus.useCases.book.BookAppointmentUseCase;

class BookControllerTest {

	BookController controller;
	BookAppointmentUseCase useCase;
	SchedulePresenterImpl presenter;
	private ProviderGW providerGw;
	private PatientGW patientGW;

	String providerID, patientID;
	LocalDateTime dateTime;

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
		useCase.setAppointmentFactory(new AppointmentFactoryImpl());

		controller.setUseCase(useCase);

		providerID = "c43a58c6-de7b-4a95-8b41-b1b29e786422";
		patientID = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		dateTime = LocalDateTime.of(2021, 01, 21, 10, 0);
	}

	@Test
	void testSuccess() {
		controller.setAppointment(patientID, providerID, dateTime);

		assertEquals("success", presenter.getStatus());
		assertResult(providerGw, providerID, 1, patientID);
		assertResult(patientGW, patientID, 4, providerID);
	}

	private void assertResult(UserGateway gw, String userID, int size, String peerID) {
		ArrayList<Appointment> result;
		result = gw.getUser(userID).getAppointments();
		assertEquals(size, result.size());
		assertEquals(dateTime, result.get(size - 1).getDateTime());
		assertEquals(peerID, result.get(size - 1).getPeerID());
	}

	@Test
	void testNullProvider() {
		controller.setAppointment(patientID, null, dateTime);
		assertEquals("fail", presenter.getStatus());
		
	}

	@Test
	void testUnknownProvider()
	{
		controller.setAppointment(patientID, "hello", dateTime);
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void testNullPatient() {
		controller.setAppointment(null, providerID, dateTime);
		assertEquals("fail", presenter.getStatus());
		
	}

	@Test
	void testUnknownPatient()
	{
		controller.setAppointment("hello", patientID, dateTime);
		assertEquals("fail", presenter.getStatus());
	}
}
