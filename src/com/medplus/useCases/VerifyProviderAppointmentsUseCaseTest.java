package com.medplus.useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.VerifyPatientAppointmentsPresenterImpl;
import com.medplus.entities.HealthProvider;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.ProviderGW;

class VerifyProviderAppointmentsUseCaseTest {

	VerifyAppointmentsUseCase useCase;
	ProviderGW gw;
	VerifyPatientAppointmentsPresenterImpl presenter;
	String providerID;
	ArrayList<HealthProvider> providers;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new VerifyAppointmentsUseCase();
		gw = new ProviderGW();
		providers = TestUtils.mountProviderList();
		gw.setProviders(providers);
		presenter = new VerifyPatientAppointmentsPresenterImpl();
		providerID = "da2ed3e9-566b-4521-8002-6e15f6f9958d";
		useCase.setGw((UserGateway) gw);
		useCase.setPresenter(presenter);

		providers.get(0).setAppointments(
				TestUtils.mountAppointmentList(
						providerID,
						"4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
						LocalDateTime.now().withHour(14)));
	}

	@Test
	void check() {
		useCase.verify(providerID);
		assertEquals("success", presenter.getStatus());
		assertEquals(3, presenter.getResult().size());
	}

	@Test
	void verifyNull()
	{
		useCase.verify(null);
		assertFailure();
	}

	@Test
	void verifyUnregistered()
	{
		useCase.verify("avião");
		assertFailure();
	}

	private void assertFailure() {
		assertEquals("fail", presenter.getStatus());
		assertNull(presenter.getResult());
	}

}
