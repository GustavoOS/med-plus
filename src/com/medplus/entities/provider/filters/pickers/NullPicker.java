package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.ProviderPicker;

public class NullPicker implements ProviderPicker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param) {
		return true;
	}

}
