package com.medplus.entities;

public interface PickerChain {
	void setNextPicker(ProviderPicker picker);
	Boolean next(HealthProvider provider, ProviderFilterParameter param);
}
