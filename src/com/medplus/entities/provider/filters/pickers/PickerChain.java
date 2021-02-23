package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.entities.provider.filter.ProviderPicker;

public interface PickerChain {
	void setNextPicker(ProviderPicker picker);
	Boolean next(HealthProvider provider, ProviderFilterParameter param);
}
