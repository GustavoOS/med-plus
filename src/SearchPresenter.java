import java.util.ArrayList;

public class SearchPresenter implements SearchReceiver {
	private ArrayList<HealthProvider> providers;

	@Override
	public void receive(ArrayList<HealthProvider> providers) {
		this.providers = providers;
	}

	public ArrayList<HealthProvider> getProviders() {
		return providers;
	}

}
