
public class RefuserPicker implements Picker {

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		return false;
	}

}
