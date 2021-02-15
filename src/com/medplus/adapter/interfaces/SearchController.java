package com.medplus.adapter.interfaces;
import com.medplus.entities.CoordinateDS;
import com.medplus.entities.ProviderFilterParameter;
import com.medplus.useCases.search.SearchUseCase;

public class SearchController {
	private SearchUseCase useCase;
	private ProviderFilterParameter param = new ProviderFilterParameter();

	public SearchController(SearchUseCase uc)
	{
		this.useCase = uc;
	}

	public void setLocation(double latitude, double longitude)
	{
		this.param.reference = (new CoordinateDS()).with(latitude, longitude);
	}

	public void setDistance(double distance) {
		this.param.distance = distance;
	}

	public void setSpecialization(String specialization)
	{
		this.param.specialization = specialization;
	}

	public void search() {
		this.useCase.search(param);
	}
}
