package com.medplus.entities;

public interface Picker {
	Boolean shouldSelect(HealthProvider provider, FilterParameter param);
}
