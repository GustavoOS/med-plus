import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchControllerTest {
	SearchPresenter receiver;
	SearchController controller;


	@BeforeEach
	void setUp() throws Exception {
		Filter filter = TestUtils.mountFilter();
		ProviderGW gw = new ProviderGW();
		gw.setProviders(TestUtils.mountProviderList());
		receiver = new SearchPresenter();
		controller = new SearchController(new SearchUseCase(gw, filter, receiver));
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