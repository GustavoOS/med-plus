package com.medplus.entities.provider.filter;

import com.medplus.entities.domain.HealthProvider;

public interface ProviderPicker {
	Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param);
}
