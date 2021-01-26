
public interface PickerChain {
	void setNextPicker(Picker picker);
	Boolean next(HealthProvider provider, FilterParameter param);
}