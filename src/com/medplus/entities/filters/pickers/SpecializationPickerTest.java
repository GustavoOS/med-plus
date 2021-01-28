package com.medplus.entities.filters.pickers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.factories.TestUtils;

class SpecializationPickerTest {

	SpecializationPicker sp;
	FilterParameter param;
	ArrayList<HealthProvider>providers;
	RefuserPicker refuser = new RefuserPicker();


	@BeforeEach
	void setUp(){
		sp = new SpecializationPicker();
		sp.setNextPicker(new NullPicker());
		param = new FilterParameter();
		providers = TestUtils.mountProviderList();
	}

	@Test
	void testSuccessPicker() {
		param.specialization = "surgeon";
		assertTrue(sp.shouldSelect(providers.get(0), param));
	}

	@Test
	void testFailPicker()
	{
		param.specialization = "gynecologyst";
		assertFalse(sp.shouldSelect(providers.get(0), param));
	}

	@Test
	void testNullParam()
	{
		param = null;
		assertTrue(sp.shouldSelect(providers.get(0), param));
		assertTrue(sp.shouldSelect(providers.get(0), null));
		sp.setNextPicker(refuser);
		assertFalse(sp.shouldSelect(providers.get(0), param));
	}

	@Test
	void testCorrectPickButFailingNextShouldFail()
	{
		sp.setNextPicker(refuser);
		param.specialization = "surgeon";
		assertFalse(sp.shouldSelect(providers.get(0), param));
	}

}
