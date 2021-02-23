package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.Utils;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.provider.filter.ProviderFilterParameter;
import com.medplus.entities.provider.filter.ProviderPicker;

public class LocalizationPicker implements ProviderPicker, PickerChain {

	ProviderPicker nextPicker;

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
		if (param == null || param.getReference() == null || param.getDistance() < 0)
			return next(provider, param);
		double distance = Utils.calculateDistance(param.getReference(), provider.getLocal());
		return (distance < param.getDistance()) && next(provider, param);
	}

}
