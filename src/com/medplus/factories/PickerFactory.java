package com.medplus.factories;

import com.medplus.entities.Picker;

public interface PickerFactory {
	Picker Make(String type);
}
