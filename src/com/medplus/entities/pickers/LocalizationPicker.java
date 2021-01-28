package com.medplus.entities.pickers;

import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Picker;
import com.medplus.entities.PickerChain;
import com.medplus.entities.Utils;

public class LocalizationPicker implements Picker, PickerChain {

	Picker nextPicker;

	@Override
	public void setNextPicker(Picker picker) {
		nextPicker = picker;
	}

	@Override
	public Boolean next(HealthProvider provider, FilterParameter param) {
		return nextPicker.shouldSelect(provider, param);
	}

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		if (param == null || param.reference == null || param.distance < 0)
			return next(provider, param);
		return (Utils.calculateDistance(param.reference, provider.getLocal()) < param.distance)
				&& next(provider, param);
	}

}
