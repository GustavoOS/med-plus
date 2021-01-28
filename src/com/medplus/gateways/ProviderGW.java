package com.medplus.gateways;
import java.util.ArrayList;

import com.medplus.entities.HealthProvider;
import com.medplus.entities.Utils;
import com.medplus.useCases.ProviderGateway;

public class ProviderGW implements ProviderGateway {
	private ArrayList<HealthProvider> providers;

	public void setProviders(ArrayList<HealthProvider> providers) {
		this.providers = providers;
	}

	@Override
	public ArrayList<HealthProvider> list() {
		return Utils.copyProviderList(providers);
	}

}
