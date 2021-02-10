package com.medplus.useCases.attend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceUseCaseTest {

	AttendanceUseCase useCase;

	@BeforeEach
	void setUp() throws Exception {
		useCase = new AttendanceUseCase();
		// TODO populate gateways
		// provider with valid client id and dateTime appointment
		// patient shoudl exist in gateway
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
