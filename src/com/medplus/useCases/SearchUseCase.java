package com.medplus.useCases;

import com.medplus.entities.Filter;
import com.medplus.entities.FilterParameter;

public class SearchUseCase implements Searchable {
	private ProviderGateway gw;
	private Filter filter;
	private SearchReceiver receiver;

	public SearchUseCase(ProviderGateway gw, Filter filter, SearchReceiver receiver)
	{
		this.gw = gw;
		this.filter = filter;
		this.receiver = receiver;
	}

	@Override
	public void search(FilterParameter params)
	{
		receiver.receive(filter.filter(gw.list(), params));
	}

}
