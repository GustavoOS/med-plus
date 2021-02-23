package com.medplus.entities.provider.filter.impl;
import java.util.ArrayList;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilter;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.entities.provider.filter.ProviderPicker;

public class FilterImpl implements ProviderFilter{
	protected ProviderPicker picker;

	public void setPicker(ProviderPicker picker) {
		this.picker = picker;
	}

	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, ProviderFilterParameter param) {
		ArrayList<HealthProvider> result = new ArrayList<HealthProvider>();
		for (HealthProvider provider : raw)
			if (picker.shouldSelect(provider, param))
				result.add(provider);

		return result;
	}

}
