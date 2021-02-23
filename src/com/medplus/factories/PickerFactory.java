package com.medplus.factories;

import com.medplus.entities.provider.filter.ProviderPicker;

public interface PickerFactory {
	ProviderPicker Make(String type);
}
