package com.medplus.entities.provider.filters.pickers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.factories.TestUtils;
import com.medplus.useCases.search.ProviderFilterParameterImpl;

class SpecializationPickerTest {

	SpecializationPicker sp;
	ProviderFilterParameter param;
	ArrayList<HealthProvider>providers;
	RefuserPicker refuser = new RefuserPicker();


	@BeforeEach
	void setUp(){
		sp = new SpecializationPicker();
		sp.setNextPicker(new NullPicker());
		param = new ProviderFilterParameterImpl();
		providers = TestUtils.mountProviderList();
	}

	@Test
	void testSuccessPicker() {
		param.withSpecialization("surgeon");
		assertTrue(sp.shouldSelect(providers.get(0), param));
	}

	@Test
	void testFailPicker()
	{
		param.withSpecialization("gynecologyst");
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
		param.withSpecialization("surgeon");
		assertFalse(sp.shouldSelect(providers.get(0), param));
	}

}
