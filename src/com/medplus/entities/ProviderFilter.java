package com.medplus.entities;

import java.util.ArrayList;

public interface ProviderFilter {
	public void setPicker(ProviderPicker picker);
	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, ProviderFilterParameter param);
}
