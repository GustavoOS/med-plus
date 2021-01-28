package com.medplus.gateways;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.AppointmentDS;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.TestUtils;

class ScheduleGWTest {

	ScheduleGW gw;
	ArrayList<HealthProvider> providers;
	AppointmentDS appointment;

	@BeforeEach
	void setUp() throws Exception {
		gw = new ScheduleGW();
		providers = TestUtils.mountProviderList();
		appointment = new AppointmentDS();
	}

	@Test
	void oneInsertionOneRecovery() {
		appointment.setProviderID(providers.get(0).getId());
		ArrayList<AppointmentDS> list = new ArrayList<AppointmentDS>();
		list.add(appointment);
		gw.setSchedule(providers.get(0).getId(), LocalDate.now(), list);
		assertNotNull(gw.getProviderSchedule(providers.get(0).getId(), LocalDate.now()));
		assertEquals(providers.get(0).getId(), gw.getProviderSchedule(providers.get(0).getId(), LocalDate.now()).get(0).getProviderID());
	}

	

}
