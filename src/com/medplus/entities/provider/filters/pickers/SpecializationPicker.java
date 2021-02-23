package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.entities.provider.filter.ProviderPicker;

public class SpecializationPicker implements ProviderPicker, PickerChain {
	private ProviderPicker nextPicker;

	@Override
	public void setNextPicker(ProviderPicker picker) {
		nextPicker = picker;
	}

	@Override
	public Boolean next(HealthProvider provider, ProviderFilterParameter param) {
		return nextPicker.shouldSelect(provider, param);
	}

	@Override
	public Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param) {
		if(param == null || param.getSpecialization() == null)
			return next(provider, param);
		return (param.getSpecialization().equals(provider.getSpecialization())) 
				&& next(provider, param);
	}

}
