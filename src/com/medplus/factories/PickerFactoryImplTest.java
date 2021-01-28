package com.medplus.factories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.filters.pickers.NullPicker;
import com.medplus.entities.filters.pickers.IDPicker;
import com.medplus.entities.filters.pickers.LocalizationPicker;
import com.medplus.entities.filters.pickers.RefuserPicker;
import com.medplus.entities.filters.pickers.SpecializationPicker;

class PickerFactoryImplTest {
	PickerFactory factory;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = new PickerFactoryImpl();
	}

	@Test
	void makeIDPicker() {
		assertTrue(factory.Make("id") instanceof IDPicker);
	}

	@Test
	void makeSpecializationPicker()
	{
		assertTrue(factory.Make("specialization") instanceof SpecializationPicker);
	}

	@Test
	void makeNullPickerByMisspelingLocalToLocalization()
	{
		assertTrue(factory.Make("localization") instanceof NullPicker);
	}

	@Test
	void makeLocalPicker()
	{
		assertTrue(factory.Make("local") instanceof LocalizationPicker);
	}

	@Test
	void makeRefuser()
	{
		assertTrue(factory.Make("refuser") instanceof RefuserPicker);
	}

	@Test
	void makeNullPicker()
	{
		assertTrue(factory.Make("null") instanceof NullPicker);
	}

	@Test
	void makeWithNull()
	{
		assertTrue(factory.Make(null) instanceof NullPicker);
	}
}
