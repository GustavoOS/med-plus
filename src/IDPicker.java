
public class IDPicker implements Picker, PickerChain {

	private Picker picker;
	@Override
	public void setNextPicker(Picker picker) {
		this.picker = picker;
	}

	@Override
	public Boolean next(HealthProvider provider, FilterParameter param) {
		return picker.shouldSelect(provider, param);
	}

	@Override
	public Boolean shouldSelect(HealthProvider provider, FilterParameter param) {
		if(param == null || param.id == null)
			return this.next(provider, param);
		return param.id.equals(provider.getId()) && this.next(provider, param);
	}

}
