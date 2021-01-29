package com.medplus.factories;

import com.medplus.entities.ProviderPicker;

public interface PickerFactory {
	ProviderPicker Make(String type);
}
