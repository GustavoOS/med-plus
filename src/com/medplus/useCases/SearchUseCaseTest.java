package com.medplus.useCases;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.adapter.interfaces.SearchPresenter;
import com.medplus.entities.CoordinateDS;
import com.medplus.entities.Filter;
import com.medplus.entities.FilterParameter;
import com.medplus.factories.FilterFactoryImpl;
import com.medplus.factories.TestUtils;
import com.medplus.gateways.ProviderGW;

class SearchUseCaseTest {
	Filter filter;
	ProviderGateway gw;
	SearchPresenter receiver;
	FilterParameter param;
	SearchUseCase useCase;

	CoordinateDS userLocation;

	@BeforeEach
	void setUp(){
		filter = (new FilterFactoryImpl()).Make("all");
		filter.setPicker(TestUtils.mountPickerChain());
		gw = new ProviderGW();
		((ProviderGW)gw).setProviders(TestUtils.mountProviderList());
		receiver = new SearchPresenter();
		useCase = new SearchUseCase(gw, filter, receiver);
		userLocation = new CoordinateDS(-23.2198557,-45.8857719);
		param = new FilterParameter();
	}

	@Test
	void noFilterSearch()
	{
		useCase.search(null);
		assertEquals(4, receiver.getProviders().size());
		assertEquals("João", receiver.getProviders().get(0).getName());
		assertEquals("Silva", receiver.getProviders().get(1).getName());
		assertEquals("Paz", receiver.getProviders().get(2).getName());
		assertEquals("Benedito", receiver.getProviders().get(3).getName());
	}

	@Test
	void filterBySpecialization()
	{
		param.specialization = "gynecologyst";
		useCase.search(param);
		assertEquals(1, receiver.getProviders().size());
		assertEquals("gynecologyst", receiver.getProviders().get(0).getSpecialization());
		assertEquals("Benedito", receiver.getProviders().get(0).getName());
	}

	@Test
	void filterByDistance()
	{
		param.reference = userLocation;
		param.distance = 5;

		useCase.search(param);
		assertEquals(3, receiver.getProviders().size());
		assertEquals("João", receiver.getProviders().get(0).getName());
		assertEquals("Silva", receiver.getProviders().get(1).getName());
		assertEquals("Benedito", receiver.getProviders().get(2).getName());
	}

	@Test
	void filterByDistanceAndSpecialization()
	{
		param.reference = userLocation;
		param.distance = 5;
		param.specialization = "surgeon";

		useCase.search(param);
		assertEquals(2, receiver.getProviders().size());
		assertEquals("João", receiver.getProviders().get(0).getName());
		assertEquals("Silva", receiver.getProviders().get(1).getName());
	}
}
