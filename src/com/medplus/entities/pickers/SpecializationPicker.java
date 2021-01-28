package com.medplus.entities.pickers;

import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Picker;
import com.medplus.entities.PickerChain;

public class SpecializationPicker implements Picker, PickerChain {
	private Picker nextPicker;

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
		if(param == null || param.specialization == null)
			return next(provider, param);
		return (param.specialization.equals(provider.getSpecialization())) 
				&& next(provider, param);
	}

}
