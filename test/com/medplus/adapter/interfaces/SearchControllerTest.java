package com.medplus.adapter.interfaces;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.provider.filter.ProviderFilter;
import com.medplus.factories.CoordinateFactoryImpl;
import com.medplus.factories.ProviderFilterFactoryImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.search.SearchUseCase;

class SearchControllerTest {
	SearchPresenter receiver;
	SearchController controller;


	@BeforeEach
	void setUp() throws Exception {
		ProviderFilter filter = (new ProviderFilterFactoryImpl()).Make();
		filter.setPicker(TestUtils.mountPickerChain());
		ProviderGW gw = new ProviderGW();
		gw.setProviders(TestUtils.mountProviderList());
		receiver = new SearchPresenter();
		controller = new SearchController();
		controller.setUseCase(new SearchUseCase(gw, filter, receiver));
		controller.setFactory(new CoordinateFactoryImpl());
	}

	@Test
	void noFilterTest() {
		controller.search();
		assertEquals(4, receiver.getProviders().size());
	}

	@Test
	void filterBySpecializationAndLocation()
	{
		controller.setDistance(5);
		controller.setLocation(-23.2244793,-45.8825458);
		controller.setSpecialization("surgeon");
		controller.search();
		assertEquals(2, receiver.getProviders().size());
	}

}
