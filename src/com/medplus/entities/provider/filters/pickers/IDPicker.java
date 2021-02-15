package com.medplus.entities.provider.filters.pickers;

import com.medplus.entities.ProviderFilterParameter;
import com.medplus.entities.ProviderPicker;
import com.medplus.entities.domain.HealthProvider;

public class IDPicker implements ProviderPicker, PickerChain {

	private ProviderPicker picker;
	@Override
	public void setNextPicker(ProviderPicker picker) {
		this.picker = picker;
	}

	@Override
	public Boolean next(HealthProvider provider, ProviderFilterParameter param) {
		return picker.shouldSelect(provider, param);
	}

	@Override
	public Boolean shouldSelect(HealthProvider provider, ProviderFilterParameter param) {
		if(param == null || param.id == null)
			return this.next(provider, param);
		return param.id.equals(provider.getId()) && this.next(provider, param);
	}

}
