package com.medplus.useCases;
import java.util.ArrayList;

import com.medplus.entities.HealthProvider;

public interface ProviderGateway extends UserGateway {
	public ArrayList<HealthProvider> list();
}
