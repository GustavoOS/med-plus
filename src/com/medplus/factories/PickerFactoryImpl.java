package com.medplus.factories;

import com.medplus.entities.provider.filter.ProviderPicker;
import com.medplus.entities.provider.filters.pickers.IDPicker;
import com.medplus.entities.provider.filters.pickers.LocalizationPicker;
import com.medplus.entities.provider.filters.pickers.NullPicker;
import com.medplus.entities.provider.filters.pickers.RefuserPicker;
import com.medplus.entities.provider.filters.pickers.SpecializationPicker;

public class PickerFactoryImpl implements PickerFactory {

	@Override
	public ProviderPicker Make(String type) {
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
