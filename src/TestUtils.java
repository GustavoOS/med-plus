import java.util.ArrayList;

public class TestUtils {
	public static ArrayList<HealthProvider> mountProviderList(){
		ArrayList<HealthProvider> providers = new ArrayList<HealthProvider>();
		providers.add(new Provider("João", "blabla.com", "surgeon", new CoordinateDS(-23.2191582,-45.8843743)));
		providers.add(new Provider("Silva", "silva.com", "surgeon", new CoordinateDS(-23.2173173,-45.8918382)));
		providers.add(new Provider("Paz", "paz.com", "surgeon", new CoordinateDS(-22.9546314,-45.8329673)));
		providers.add(new Provider("Benedito", "sera.com", "gynecologyst", new CoordinateDS(-23.2232479,-45.8934114)));
		return providers;
	}

	public static Filter mountFilter()
	{
		LocalizationPicker lp = new LocalizationPicker();
		lp.setNextPicker(new NullPicker());
		SpecializationPicker sp = new SpecializationPicker();
		sp.setNextPicker(lp);
		return new Filter(sp);
	}
}
