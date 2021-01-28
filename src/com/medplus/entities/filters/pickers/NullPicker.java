package com.medplus.entities.filters.pickers;

import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Picker;

public class NullPicker implements Picker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		return true;
	}

}
