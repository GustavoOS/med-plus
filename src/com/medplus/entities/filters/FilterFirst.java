package com.medplus.entities.filters;

import java.util.ArrayList;

import com.medplus.entities.Filter;
import com.medplus.entities.FilterParameter;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Picker;

public class FilterFirst implements Filter {
	private Picker picker;

	@Override
	public void setPicker(Picker picker) {
		this.picker = picker;
	}

	@Override
	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, FilterParameter param) {
		ArrayList<HealthProvider> result = new ArrayList<HealthProvider>();
		for (HealthProvider provider : raw)
			if (picker.shouldSelect(provider, param))
				{
					result.add(provider);
					return result;
				}

		return result;
	}

}