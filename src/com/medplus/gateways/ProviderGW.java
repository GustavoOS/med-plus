package com.medplus.gateways;
import java.util.ArrayList;

import com.medplus.entities.Cloner;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.User;
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
	public void put(User provider) {
		if(provider == null)
			return;
		int index = providers.indexOf(filterProvider(provider.getId()));
		HealthProvider p = (HealthProvider) provider;
		if(index < 0)
			providers.add(p);
		else
			providers.set(index, p);		
	}

	@Override
	public User getUser(String id)
	{
		return Cloner.cloneProvider(filterProvider(id));
	}

	public HealthProvider filterProvider(String id) {
		if(id == null)
			return null;
		return providers.stream()
				  .filter(provider -> id.equals(provider.getId()))
				  .findAny()
				  .orElse(null);
	}

	ArrayList<HealthProvider> getProviders() {
		return providers;
	}

	
}
