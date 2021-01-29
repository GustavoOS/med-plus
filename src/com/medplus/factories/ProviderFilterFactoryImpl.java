package com.medplus.factories;

import com.medplus.entities.ProviderFilter;
import com.medplus.entities.provider.filters.FilterAll;
import com.medplus.entities.provider.filters.FilterFirst;

public class ProviderFilterFactoryImpl implements ProviderFilterFactory {

	@Override
	public ProviderFilter Make(String name) {
		if(name.equals("first"))
			return new FilterFirst();
		return new FilterAll();
	}

}
