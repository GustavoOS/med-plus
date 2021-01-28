package com.medplus.factories;

import com.medplus.entities.Filter;

public interface FilterFactory {
	public Filter Make(String name);
}
