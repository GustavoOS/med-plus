import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProviderTest {

	@Test
	void testGettersAndSetters() {
		HealthProvider hp = new Provider();
		hp.setLocal(new CoordinateDS(-23.1735709, -45.8423158));
		hp.setSpecialization("surgeon");
		hp.setName("Jo�o");
		hp.setSocialMediaURL("https://www.instagram.com/neymarjr/");
		assertEquals("Jo�o", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
	}

	@Test
	void testParameterizedConstructor() {
		HealthProvider hp = new Provider(
				"Jo�o",
				"https://www.instagram.com/neymarjr/",
				"surgeon",
				new CoordinateDS(-23.1735709, -45.8423158));
		assertEquals("Jo�o", hp.getName());
		assertEquals(-23.173, hp.getLocal().latitude, 0.01);
		assertEquals(-45.842, hp.getLocal().longitude, 0.01);
		assertEquals("https://www.instagram.com/neymarjr/", hp.getSocialMediaURL());
		assertEquals("surgeon", hp.getSpecialization());
	}

}
