package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.ProviderPicker;
import com.medplus.entities.domain.HealthProvider;

public interface PickerChain {
	void setNextPicker(ProviderPicker picker);
	Boolean next(HealthProvider provider, ProviderFilterParameter param);
}
