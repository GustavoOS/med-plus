package com.medplus.entities.pickers;

import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Picker;

public class RefuserPicker implements Picker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		return false;
	}

}
