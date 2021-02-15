package com.medplus.useCases.cancelation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.CancelAppointmentPresenterImpl;
import com.medplus.entities.Appointment;
import com.medplus.entities.Patient;
import com.medplus.entities.appointment.AppointmentImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;

class CancelPatientAppointmentUseCaseTest {

	CancelAppointmentPresenterImpl presenter;
	LocalDateTime date;
	String patientID;

	PatientGW patientGW;

	public CancelAppointmentUseCase useCase;
	@BeforeEach
	void setUp(){
		patientGW = new PatientGW();
		ProviderGW providerGW = new ProviderGW();
		LocalDateTime now = LocalDateTime.now();
		patientGW.setPatients(TestUtils.mountPatientList(now));
		providerGW.setProviders(TestUtils.mountProviderList(now));

		presenter = new CancelAppointmentPresenterImpl();
		useCase = new CancelAppointmentUseCase();
		useCase.setUserGW(patientGW);
		useCase.setPresenter(presenter);
		useCase.setPeerGW(providerGW);

		patientID = "4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1";
		date = now.withHour(14).withMinute(0).withSecond(0).withNano(0);
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
		assertNotEquals(date, result.get(0).getDateTime());
		assertNotEquals(date, result.get(1).getDateTime());
	}

	@Test
	void cantFindPeer()
	{
		addAppointmentToPatientAppointmentList("dfgyui");
		useCase.cancel(patientID, date.plusDays(20));
		assertEquals("fail", presenter.getStatus());
	}

	@Test
	void peerHasNoAppointment()
	{
		addAppointmentToPatientAppointmentList("81a5cb24-d4ba-4789-b5da-3e76ae7ca551");
		useCase.cancel(patientID, date.plusDays(20));
		assertEquals("fail", presenter.getStatus());
	}

	private void assertFailure()
	{
		assertEquals("fail", presenter.getStatus());
	}


	private void addAppointmentToPatientAppointmentList(String provider) {
		Appointment appointment =
				(new AppointmentImpl())
					.withDateTime(date.plusDays(20))
					.withPeerID(provider);
		Patient p = (Patient) patientGW.getUser(patientID);
		p.getAppointments().add(appointment);
		patientGW.put(p);
	}
}
