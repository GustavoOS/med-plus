package com.medplus.useCases.search;

import com.medplus.entities.ProviderFilter;
import com.medplus.entities.ProviderFilterParameter;
import com.medplus.useCases.ProviderGateway;
import com.medplus.useCases.SearchReceiver;
import com.medplus.useCases.Searchable;

public class SearchUseCase implements Searchable {
	private ProviderGateway gw;
	private ProviderFilter filter;
	private SearchReceiver receiver;

	public SearchUseCase(ProviderGateway gw, ProviderFilter filter, SearchReceiver receiver)
	{
		this.gw = gw;
		this.filter = filter;
		this.receiver = receiver;
	}

	@Override
	public void search(ProviderFilterParameter params)
	{
		receiver.receive(filter.filter(gw.list(), params));
	}

}
