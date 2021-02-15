package com.medplus.useCases.cancelation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.CancelAppointmentPresenterImpl;
import com.medplus.entities.domain.Appointment;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class CancelProviderAppointmentUseCaseTest {


	CancelAppointmentPresenterImpl presenter;
	LocalDateTime date;
	String providerID;

	public CancelAppointmentUseCase useCase;
	@BeforeEach
	void setUp(){
		useCase = new CancelAppointmentUseCase();
		date = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0);
		injectGWs();
		providerID = "da2ed3e9-566b-4521-8002-6e15f6f9958d";

		presenter = new CancelAppointmentPresenterImpl();
		useCase.setPresenter(presenter);
	}

	private void injectGWs() {
		LocalDateTime now = LocalDateTime.now();
		ArrayList<Patient> patients = TestUtils.mountPatientList(now);
		ArrayList<HealthProvider> providers = TestUtils.mountProviderList(now);
		ProviderGW providerGW = new ProviderGW();
		providerGW.setProviders(providers);
		PatientGW patientGW = new PatientGW();
		patientGW.setPatients(patients);
		useCase.setUserGW(providerGW);
		useCase.setPeerGW(patientGW);
	}

	@Test
	void nullArgumentsShouldFailCancel() {
		useCase.cancel(null, null);
		assertFailure();
	}

	@Test
	void nullIdShouldFail()
	{
		useCase.cancel(null, date);
		assertFailure();
	}

	@Test
	void rightIDnullDateShouldFail()
	{
		useCase.cancel(providerID, null);
		assertFailure();
	}

	@Test
	void cancelFreeScheduleShouldFail()
	{
		useCase.cancel(providerID,
				LocalDateTime.of(2022, 02, 04, 10, 0));
		assertFailure();
	}

	@Test
	void cancelProperWay()
	{
		String maria, john;
		maria = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		john = "0c9dbc30-2874-4983-a0b7-6885c409ddbc";
		useCase.cancel(providerID, date);
		assertEquals("success", presenter.getStatus());
		ArrayList<Appointment> result = presenter.getList();
		assertEquals(5, result.size());
		assertEquals(maria, result.get(0).getPeerID());
		assertEquals(maria, result.get(1).getPeerID());
		assertEquals(john, result.get(2).getPeerID());
		assertEquals(john, result.get(3).getPeerID());
		assertEquals(john, result.get(4).getPeerID());
		
	}
	private void assertFailure()
	{
		assertEquals("fail", presenter.getStatus());
	}

}
