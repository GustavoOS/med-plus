package com.medplus.factories;

import com.medplus.entities.ProviderFilter;

public interface ProviderFilterFactory {
	public ProviderFilter Make(String name);
}
