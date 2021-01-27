import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProviderTest {

	@Test
	void testGettersAndSetters() {
		HealthProvider hp = new Provider();
		hp.setLocal(new CoordinateDS(-23.1735709, -45.8423158));
		hp.setSpecialization("surgeon");
		hp.setName("João");
		hp.setId("74e50960-e065-4870-ab5a-c244aa757ca8");
		hp.setSocialMediaURL("https://www.instagram.com/neymarjr/");
		assertEquals("João", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
		assertEquals("74e50960-e065-4870-ab5a-c244aa757ca8", hp.getId());
	}

	@Test
	void testParameterizedConstructor() {
		HealthProvider hp = new Provider(
				"João",
				"https://www.instagram.com/neymarjr/",
				"surgeon",
				new CoordinateDS(-23.1735709, -45.8423158));
		assertEquals("João", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
	}

	@Test
	void onCloneIdShouldBeCopied()
	{
		HealthProvider hp = new Provider();
		hp.setLocal(new CoordinateDS(-23.1735709, -45.8423158));
		hp.setSpecialization("surgeon");
		hp.setName("João");
		hp.setId("74e50960-e065-4870-ab5a-c244aa757ca8");
		hp.setSocialMediaURL("https://www.instagram.com/neymarjr/");

		assertEquals("74e50960-e065-4870-ab5a-c244aa757ca8", hp.clone().getId());
		assertNotSame(hp, hp.clone());
	}
	

}
