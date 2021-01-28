package com.medplus.factories;

import com.medplus.entities.Filter;
import com.medplus.entities.filters.FilterAll;
import com.medplus.entities.filters.FilterFirst;

public class FilterFactoryImpl implements FilterFactory {

	@Override
	public Filter Make(String name) {
		if(name.equals("first"))
			return new FilterFirst();
		return new FilterAll();
	}

}
