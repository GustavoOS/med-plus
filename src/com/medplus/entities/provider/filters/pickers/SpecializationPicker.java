package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.ProviderPicker;
import com.medplus.entities.PickerChain;

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
		if(param == null || param.specialization == null)
			return next(provider, param);
		return (param.specialization.equals(provider.getSpecialization())) 
				&& next(provider, param);
	}

}