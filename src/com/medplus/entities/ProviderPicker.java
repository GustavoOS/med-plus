package com.medplus.entities;

import com.medplus.entities.domain.HealthProvider;

public interface ProviderPicker {
	Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param);
}
