package com.medplus.entities;

import java.util.ArrayList;

import com.medplus.entities.domain.HealthProvider;

public interface ProviderFilter {
	public void setPicker(ProviderPicker picker);
	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, ProviderFilterParameter param);
}
