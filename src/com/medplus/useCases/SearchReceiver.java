package com.medplus.useCases;
import java.util.ArrayList;

import com.medplus.entities.domain.HealthProvider;

public interface SearchReceiver {
	public void receive(ArrayList<HealthProvider> providers);
}
