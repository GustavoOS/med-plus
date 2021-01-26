import java.util.ArrayList;

public class ProviderGW implements ProviderGateway {
	private ArrayList<HealthProvider> providers;

	public void setProviders(ArrayList<HealthProvider> providers) {
		this.providers = providers;
	}

	@Override
	public ArrayList<HealthProvider> list() {
		return Utils.copyProviderList(providers);
	}

}
