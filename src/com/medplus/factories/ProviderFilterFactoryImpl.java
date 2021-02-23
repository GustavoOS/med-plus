package com.medplus.factories;

import com.medplus.entities.provider.filter.ProviderFilter;
import com.medplus.entities.provider.filter.impl.FilterImpl;

public class ProviderFilterFactoryImpl implements ProviderFilterFactory {

	@Override
	public ProviderFilter Make() {
		return new FilterImpl();
	}

}
