import java.util.ArrayList;

public class Filter {
	protected Picker picker;

	public Filter(Picker picker) {
		this.picker = picker;
	}

	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, FilterParameter param) {
		ArrayList<HealthProvider> result = new ArrayList<HealthProvider>();
		for (HealthProvider provider : raw)
			if (picker.shouldSelect(provider, param))
				result.add(provider);

		return result;
	}
}
