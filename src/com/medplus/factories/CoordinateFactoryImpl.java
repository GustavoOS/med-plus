package com.medplus.factories;

import com.medplus.entities.Coordinate;
import com.medplus.entities.coordinate.CoordinateDS;
import com.medplus.useCases.CoordinateFactory;

public class CoordinateFactoryImpl implements CoordinateFactory {

	@Override
	public Coordinate make() {
		return new CoordinateDS();
	}

}
