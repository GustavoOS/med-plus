package com.medplus.entities.pickers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.CoordinateDS;
import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.factories.TestUtils;

class LocalizationPickerTest {

	LocalizationPicker picker;
	FilterParameter param;
	ArrayList<HealthProvider>providers;
	RefuserPicker refuser = new RefuserPicker();
	CoordinateDS user;


	@BeforeEach
	void setUp(){
		picker = new LocalizationPicker();
		picker.setNextPicker(new NullPicker());
		param = new FilterParameter();
		providers = TestUtils.mountProviderList();
		user = new CoordinateDS(-23.1649181,-45.7951985);
	}

	@Test
	void successfullFilter() {
		param.distance = 20;
		param.reference = user;
		assertTrue(picker.shouldSelect(providers.get(0), param));
	}

	@Test
	void failDistance()
	{
		param.distance = 2;
		param.reference = user;
		assertFalse(picker.shouldSelect(providers.get(0), param));
	}

	@Test
	void failNextShoudFail()
	{
		param.distance = 20;
		param.reference = user;
		picker.setNextPicker(refuser);
		assertFalse(picker.shouldSelect(providers.get(0), param));
	}

	@Test
	void missingReferenceShouldNotApplyFilter() {
		param.distance = 20;
		assertTrue(picker.shouldSelect(providers.get(0), param));
	}

	@Test
	void missingDistanceShouldNotApplyFilter()
	{
		param.reference = user;
		assertTrue(picker.shouldSelect(providers.get(0), param));
	}

	@Test
	void nullParameterShouldNotApplyFilter()
	{
		assertTrue(picker.shouldSelect(providers.get(0), null));
	}
}
