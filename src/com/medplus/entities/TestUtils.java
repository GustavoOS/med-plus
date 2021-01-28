package com.medplus.entities;
import java.util.ArrayList;

public class TestUtils {
	public static ArrayList<HealthProvider> mountProviderList(){
		ArrayList<HealthProvider> providers = new ArrayList<HealthProvider>();
		providers.add(new Provider("João", "blabla.com", "surgeon", new CoordinateDS(-23.2191582,-45.8843743)));
		providers.add(new Provider("Silva", "silva.com", "surgeon", new CoordinateDS(-23.2173173,-45.8918382)));
		providers.add(new Provider("Paz", "paz.com", "surgeon", new CoordinateDS(-22.9546314,-45.8329673)));
		providers.add(new Provider("Benedito", "sera.com", "gynecologyst", new CoordinateDS(-23.2232479,-45.8934114)));
		providers.get(0).setId("da2ed3e9-566b-4521-8002-6e15f6f9958d");
		providers.get(1).setId("c43a58c6-de7b-4a95-8b41-b1b29e786422");
		providers.get(2).setId("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		providers.get(3).setId("81a5cb24-d4ba-4789-b5da-3e76ae7ca551");
		return providers;
	}

	public static Filter mountFilter()
	{
		LocalizationPicker lp = new LocalizationPicker();
		lp.setNextPicker(new NullPicker());
		SpecializationPicker sp = new SpecializationPicker();
		sp.setNextPicker(lp);
		IDPicker idPicker = new IDPicker();
		idPicker.setNextPicker(sp);
		return new Filter(idPicker);
	}
}
