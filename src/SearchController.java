
public class SearchController {
	private SearchUseCase useCase;
	private FilterParameter param = new FilterParameter();

	public SearchController(SearchUseCase uc)
	{
		this.useCase = uc;
	}

	public void setLocation(double latitude, double longitude)
	{
		this.param.reference = new CoordinateDS(latitude, longitude);
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
