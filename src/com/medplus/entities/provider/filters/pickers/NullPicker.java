package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.entities.provider.filter.ProviderPicker;

public class NullPicker implements ProviderPicker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param) {
		return true;
	}

}
