package com.medplus.gateways;
import java.util.ArrayList;

import com.medplus.entities.Cloner;
import com.medplus.entities.HealthProvider;
import com.medplus.useCases.ProviderGateway;

public class ProviderGW implements ProviderGateway {
	private ArrayList<HealthProvider> providers;

	public void setProviders(ArrayList<HealthProvider> providers) {
		this.providers = providers;
	}

	@Override
	public ArrayList<HealthProvider> list() {
		return Cloner.cloneProviderList(providers);
	}

	@Override
	public void put(HealthProvider provider) {
		int index = providers.indexOf(getProvider(provider.getId()));
		if(index < 0)
			providers.add(provider);
		else
			providers.set(index, provider);		
	}

	@Override
	public HealthProvider getProvider(String id) {
		return providers.stream()
				  .filter(provider -> id.equals(provider.getId()))
				  .findAny()
				  .orElse(null);
	}
}
