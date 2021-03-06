package com.medplus.entities.provider.filters.pickers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.factories.TestUtils;
import com.medplus.useCases.search.ProviderFilterParameterImpl;

class IDPickerTest {

	IDPicker idP;
	ProviderFilterParameter param;
	ArrayList<HealthProvider>providers;
	RefuserPicker refuser = new RefuserPicker();
	String id;

	@BeforeEach
	void setUp(){
		idP = new IDPicker();
		idP.setNextPicker(new NullPicker());
		param = new ProviderFilterParameterImpl();
		providers = TestUtils.mountProviderList();
		id = "7b11fdbb-0894-4e4b-afaf-880738c84f4c";
	}

	@Test
	void testSuccessPicker() {
		param.withId(id);
		assertTrue(idP.shouldSelect(providers.get(2), param));
	}

	@Test
	void testFailPicker()
	{
		param.withId(id);
		assertFalse(idP.shouldSelect(providers.get(0), param));
	}

	@Test
	void testNullParam()
	{
		assertTrue(idP.shouldSelect(providers.get(0), null));
		idP.setNextPicker(refuser);
		assertFalse(idP.shouldSelect(providers.get(0), null));
	}

	@Test
	void testCorrectPickButFailingNextShouldFail()
	{
		idP.setNextPicker(refuser);
		param.withId(id);
		assertFalse(idP.shouldSelect(providers.get(2), param));
	}

}
