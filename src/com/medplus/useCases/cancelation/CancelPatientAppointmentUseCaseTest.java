package com.medplus.useCases.cancelation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.CancelAppointmentPresenterImpl;
import com.medplus.entities.Appointment;
import com.medplus.entities.PatientAppointmentCanceler;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class CancelPatientAppointmentUseCaseTest {

	CancelAppointmentPresenterImpl presenter;
	LocalDateTime date;
	String patientID;

	public CancelAppointmentUseCase useCase;
	@BeforeEach
	void setUp(){
		PatientGW patientGW = new PatientGW();
		ProviderGW providerGW = new ProviderGW();
		patientGW.setPatients(TestUtils.mountPatientList());
		providerGW.setProviders(TestUtils.mountProviderList());

		presenter = new CancelAppointmentPresenterImpl();
		useCase = new CancelAppointmentUseCase();
		useCase.setCanceler(new PatientAppointmentCanceler());
		useCase.setRootGW(patientGW);
		useCase.setPresenter(presenter);
		useCase.setTargetGW(providerGW);

		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		date = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0);
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
		useCase.cancel(patientID, null);
		assertFailure();
	}

	@Test
	void cancelFreeScheduleShouldFail()
	{
		useCase.cancel(patientID,
				LocalDateTime.of(2022, 02, 04, 10, 0));
		assertFailure();
	}

	@Test
	void cancelProperWay()
	{
		useCase.cancel(patientID, date);
		assertEquals("success", presenter.getStatus());
		ArrayList<Appointment> result = presenter.getList();
		assertEquals(2, result.size());
		assertEquals(patientID, result.get(0).getPatientID());
		
	}
	private void assertFailure()
	{
		assertEquals("fail", presenter.getStatus());
	}
}