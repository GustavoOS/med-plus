package com.medplus.entities.provider.filters;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.coordinate.CoordinateDS;
import com.medplus.entities.domain.Coordinate;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.factories.TestUtils;
import com.medplus.useCases.search.ProviderFilterParameterImpl;

class FilterImplTest {

	Coordinate user;
	FilterImpl filter;
	ProviderFilterParameter param;

	ArrayList<HealthProvider> raw, result;

	@BeforeEach
	void setUp(){
		user = (new CoordinateDS()).with(-23.2198557,-45.8857719);
		filter = new FilterImpl();
		filter.setPicker(TestUtils.mountPickerChain());

		raw = TestUtils.mountProviderList();
		param = new ProviderFilterParameterImpl();
	}

	@Test
	void filterNearSurgeons() {
		param
				.withDistance(10)
				.withReference(user)
				.withSpecialization("surgeon");
		result = filter.filter(raw, param);

		assertEquals(2, result.size());
		assertEquals("Joe", result.get(0).getName());
		assertEquals("Silva", result.get(1).getName());
	}

	@Test
	void filterGynecologyst()
	{
		param.withSpecialization("gynecologyst");
		result = filter.filter(raw, param);
		assertEquals(1, result.size());
		assertEquals("Benedito", result.get(0).getName());
	}

	@Test
	void filterNear()
	{
		param
			.withDistance(10)
			.withReference(user);

		result = filter.filter(raw, param);
		assertEquals(3, result.size());
		assertEquals("surgeon", result.get(0).getSpecialization());
		assertEquals("surgeon", result.get(1).getSpecialization());
		assertEquals("gynecologyst", result.get(2).getSpecialization());
	}

	@Test
	void noFilterNoChange()
	{
		result = filter.filter(raw, null);
		assertEquals(4, result.size());
		for(int i = 0; i < 4; i++) {
			assertSame(result.get(i), raw.get(i));
		}
	}

}
