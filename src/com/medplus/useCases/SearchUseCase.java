package com.medplus.useCases;

import com.medplus.entities.ProviderFilter;
import com.medplus.entities.ProviderFilterParameter;

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
