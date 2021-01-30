package com.medplus.useCases;
import java.util.ArrayList;

import com.medplus.entities.HealthProvider;

public interface ProviderGateway {
	public ArrayList<HealthProvider> list();
	public void put(HealthProvider provider);
	public HealthProvider getProvider(String id);
}
