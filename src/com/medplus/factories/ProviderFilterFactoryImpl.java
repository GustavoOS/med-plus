package com.medplus.factories;

import com.medplus.entities.ProviderFilter;
import com.medplus.entities.provider.filters.FilterImpl;

public class ProviderFilterFactoryImpl implements ProviderFilterFactory {

	@Override
	public ProviderFilter Make() {
		return new FilterImpl();
	}

}
