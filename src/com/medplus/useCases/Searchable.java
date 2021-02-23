package com.medplus.useCases;

import com.medplus.entities.provider.filter.ProviderFilterParameter;

public interface Searchable {
	void search(ProviderFilterParameter params);
}
