package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.ProviderPicker;
import com.medplus.entities.Utils;
import com.medplus.entities.domain.HealthProvider;

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
		if (param == null || param.reference == null || param.distance < 0)
			return next(provider, param);
		return (Utils.calculateDistance(param.reference, provider.getLocal()) < param.distance)
				&& next(provider, param);
	}

}
