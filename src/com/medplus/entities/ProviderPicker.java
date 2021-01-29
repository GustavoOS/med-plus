package com.medplus.entities;

public interface ProviderPicker {
	Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param);
}
