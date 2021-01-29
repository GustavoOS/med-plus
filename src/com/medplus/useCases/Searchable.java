package com.medplus.useCases;

import com.medplus.entities.ProviderFilterParameter;

public interface Searchable {
	void search(ProviderFilterParameter params);
}
