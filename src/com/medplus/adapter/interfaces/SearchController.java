package com.medplus.adapter.interfaces;
import com.medplus.entities.ProviderFilterParameter;
import com.medplus.useCases.CoordinateFactory;
import com.medplus.useCases.search.SearchUseCase;

public class SearchController {
	private SearchUseCase useCase;
	private ProviderFilterParameter param = new ProviderFilterParameter();
	private CoordinateFactory factory;


	public void search() {
		this.useCase.search(param);
	}


	public void setLocation(double latitude, double longitude)
	{
		this.param.reference = factory.make().with(latitude, longitude);
	}

	public void setDistance(double distance) {
		this.param.distance = distance;
	}

	public void setSpecialization(String specialization)
	{
		this.param.specialization = specialization;
	}


	public void setUseCase(SearchUseCase useCase) {
		this.useCase = useCase;
	}

	public void setFactory(CoordinateFactory factory)
	{
		this.factory = factory;
	}
}
