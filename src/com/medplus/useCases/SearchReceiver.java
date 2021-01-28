package com.medplus.useCases;
import java.util.ArrayList;

import com.medplus.entities.HealthProvider;

public interface SearchReceiver {
	public void receive(ArrayList<HealthProvider> providers);
}
