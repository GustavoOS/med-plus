package com.medplus.entities;

import java.util.ArrayList;

public interface Filter {
	public void setPicker(Picker picker);
	public ArrayList<HealthProvider> filter(ArrayList<HealthProvider> raw, FilterParameter param);
}
