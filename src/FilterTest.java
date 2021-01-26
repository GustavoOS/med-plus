import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterTest {

	CoordinateDS user;
	Filter filter;
	FilterParameter param;

	ArrayList<HealthProvider> raw, result;

	@BeforeEach
	void setUp(){
		user = new CoordinateDS(-23.2198557,-45.8857719);
		filter = TestUtils.mountFilter();

		raw = TestUtils.mountProviderList();
		param = new FilterParameter();
	}

	@Test
	void filterNearSurgeons() {
		param.distance = 10;
		param.reference = user;
		param.specialization = "surgeon";
		result = filter.filter(raw, param);

		assertEquals(2, result.size());
		assertEquals("João", result.get(0).getName());
		assertEquals("Silva", result.get(1).getName());
	}

	@Test
	void filterGynecologyst()
	{
		param.specialization = "gynecologyst";
		result = filter.filter(raw, param);
		assertEquals(1, result.size());
		assertEquals("Benedito", result.get(0).getName());
	}

	@Test
	void filterNear()
	{
		param.distance = 10;
		param.reference = user;

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
