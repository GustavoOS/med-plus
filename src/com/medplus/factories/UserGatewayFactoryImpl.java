package com.medplus.factories;

import com.medplus.gateways.PatientGW;
import com.medplus.gateways.ProviderGW;
import com.medplus.useCases.UserGateway;

public class UserGatewayFactoryImpl implements UserGatewayFactory {

	@Override
	public UserGateway make(String option) {
		if("patient".equals(option))
			return new PatientGW();
		if("provider".equals(option))
			return new ProviderGW();
		return null;
	}

}
