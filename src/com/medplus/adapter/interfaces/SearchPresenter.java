package com.medplus.adapter.interfaces;
import java.util.ArrayList;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.useCases.SearchReceiver;

public class SearchPresenter implements SearchReceiver {
	private ArrayList<HealthProvider> providers;

	@Override
	public void receive(ArrayList<HealthProvider> providers) {
		this.providers = providers;
	}

	public ArrayList<HealthProvider> getProviders() {
		return providers;
	}

}
