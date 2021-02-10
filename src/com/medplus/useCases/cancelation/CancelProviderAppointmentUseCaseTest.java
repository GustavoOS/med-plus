package com.medplus.useCases.cancelation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.CancelAppointmentPresenterImpl;
import com.medplus.entities.Appointment;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Patient;
import com.medplus.entities.ProviderAppointmentCanceler;
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

		presenter = new CancelAppointmentPresenterImpl();
		useCase.setCanceler(new ProviderAppointmentCanceler());
		useCase.setPresenter(presenter);
	}

	private void injectGWs() {
		ArrayList<Patient> patients = TestUtils.mountPatientList();
		ArrayList<HealthProvider> providers = TestUtils.mountProviderList();
		providerID = providers.get(0).getId();
		setAppointments(patients, providers);

		populateGWsAndInjectToUseCase(patients, providers);
	}

	private void populateGWsAndInjectToUseCase(ArrayList<Patient> patients, ArrayList<HealthProvider> providers) {
		PatientGW patientGW = new PatientGW();
		ProviderGW providerGW = new ProviderGW();
		patientGW.setPatients(patients);
		providerGW.setProviders(providers);

		useCase.setTargetGW(patientGW);
		useCase.setRootGW(providerGW);
	}

	private void setAppointments(ArrayList<Patient> patients, ArrayList<HealthProvider> providers) {
		ArrayList<Appointment> appointments = 
				TestUtils.mountAppointmentList(
						providerID,
						patients.get(0).getId(), date);
		patients.get(0).setAppointments(appointments);
		providers.get(0).setAppointments(appointments);
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
		useCase.cancel(providerID, date);
		assertEquals("success", presenter.getStatus());
		ArrayList<Appointment> result = presenter.getList();
		assertEquals(2, result.size());
		assertEquals(providerID, result.get(0).getProviderID());
		assertEquals(providerID, result.get(1).getProviderID());
		
	}
	private void assertFailure()
	{
		assertEquals("fail", presenter.getStatus());
	}

}
