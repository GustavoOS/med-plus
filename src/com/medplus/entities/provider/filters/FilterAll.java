package com.medplus.entities.provider.filters;
import java.util.ArrayList;

import com.medplus.entities.ProviderFilter;
import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.ProviderPicker;

public class FilterAll implements ProviderFilter{
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
