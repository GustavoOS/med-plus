package com.medplus.factories;

import com.medplus.entities.Picker;
import com.medplus.entities.filters.pickers.NullPicker;
import com.medplus.entities.filters.pickers.IDPicker;
import com.medplus.entities.filters.pickers.LocalizationPicker;
import com.medplus.entities.filters.pickers.RefuserPicker;
import com.medplus.entities.filters.pickers.SpecializationPicker;

public class PickerFactoryImpl implements PickerFactory {

	@Override
	public Picker Make(String type) {
		if(type == null)
			return new NullPicker();
		if(type.equals("id"))
			return new IDPicker();
		if(type.equals("local"))
			return new LocalizationPicker();
		if(type.equals("specialization"))
			return new SpecializationPicker();
		if(type.equals("refuser"))
			return new RefuserPicker();
		return new NullPicker();
	}

}
