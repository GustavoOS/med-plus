package com.medplus.entities;

public class NullPicker implements Picker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		return true;
	}

}
