package com.medplus.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.factories.AppointmentFactoryImpl;

class AppointmentFilterAllTest {
	ArrayList<Appointment> raw, result;
	AppointmentFilter filter;
	Appointment param;

	String provider1 = "bee2e051-5dbb-4bfe-962f-c5d4bc4bcab6";
	String patient1 = "582e4705-af3d-456d-a6be-ae23c34fc2f9";
	String provider2 = "f8b0a3c1-16ce-4836-90b4-eb1fe49762fc";
	String patient2 = "346baeed-e66b-4acc-ad73-5951d0569cc2";
	LocalDateTime baseDate = LocalDateTime.of(2020, 01, 29, 16, 0);

	@BeforeEach
	void setUp() throws Exception {


		
		raw = new ArrayList<Appointment>();
		addAppointmentToRaw(provider1, patient1, baseDate);
		addAppointmentToRaw(provider1, patient1, baseDate.plusHours(1));
		addAppointmentToRaw(provider1, patient1, baseDate.plusDays(1));
		addAppointmentToRaw(provider2, patient1, baseDate.plusDays(1).plusHours(1));
		addAppointmentToRaw(provider1, patient2, baseDate.minusHours(1));

		
		filter = new AppointmentFilterAll();
		param = (new AppointmentFactoryImpl()).make();
	}

	@Test
	void noFilter() {
		assertEquals(5, raw.size());
		result = filter.filter(raw, null);
		assertNotSame(raw, result);
		assertEquals(5, result.size());
		for(int i = 0; i < 5; i++)
			compareRawAndResultElementsByIndex(i, i);
	}


	@Test
	void filterByProviderId()
	{
		param.setProviderID(provider1);
		assertEquals(5, raw.size());
		result = filter.filter(raw, param);
		assertEquals(4, result.size());
		for (Appointment appointment : result)
			assertEquals(provider1, appointment.getProviderID());
		compareRawAndResultElementsByIndex(4, 3);
		compareRawAndResultElementsByIndex(2, 2);
		
	}

	@Test
	void filterByClient1()
	{
		param.setPatientID(patient1);
		result = filter.filter(raw, param);
		assertEquals(4, result.size());
		compareRawAndResultElementsByIndex(3, 3);
	}

	@Test
	void filterByClient2()
	{
		param.setPatientID(patient2);
		result = filter.filter(raw, param);
		assertEquals(1, result.size());
		compareRawAndResultElementsByIndex(4, 0);
	}

	@Test
	void filterByDate()
	{
		param.setDateTime(baseDate.plusDays(1));
		result = filter.filter(raw, param);
		assertEquals(2, result.size());
		compareRawAndResultElementsByIndex(2, 0);
		compareRawAndResultElementsByIndex(3, 1);
	}

	@Test
	void filterByProviderAndDate()
	{
		param.setProviderID(provider1);
		param.setDateTime(baseDate);
		result = filter.filter(raw, param);

		assertEquals(3, result.size());
		compareRawAndResultElementsByIndex(0, 0);
		compareRawAndResultElementsByIndex(1, 1);
		compareRawAndResultElementsByIndex(4, 2);
		
	}

	@Test
	void filterByCustomerAndDate()
	{
		param.setPatientID(patient1);
		param.setDateTime(baseDate);

		result = filter.filter(raw, param);
		assertEquals(2, result.size());
		compareRawAndResultElementsByIndex(0, 0);
		compareRawAndResultElementsByIndex(1, 1);
	}

	@Test
	void filterByCustomerAndProvider()
	{
		param.setPatientID(patient1);
		param.setProviderID(provider1);
		
		result = filter.filter(raw, param);
		assertEquals(3, result.size());
		compareRawAndResultElementsByIndex(0, 0);
		compareRawAndResultElementsByIndex(1, 1);
		compareRawAndResultElementsByIndex(2, 2);
	}

	@Test
	void filterByCustomerProviderAndDate()
	{
		param.setPatientID(patient1);
		param.setProviderID(provider1);
		param.setDateTime(baseDate);

		result = filter.filter(raw, param);
		assertEquals(2, result.size());
		compareRawAndResultElementsByIndex(0, 0);
		compareRawAndResultElementsByIndex(1, 1);
	}

	private void compareRawAndResultElementsByIndex(int i, int j) {
		assertSame(raw.get(i), result.get(j));
	}

	void addAppointmentToRaw(String providerId, String patientId, LocalDateTime dateTime)
	{
		Appointment appointment = (new AppointmentFactoryImpl()).make();
		appointment.setProviderID(providerId);
		appointment.setPatientID(patientId);
		appointment.setDateTime(dateTime);
		raw.add(appointment);
	}

}
