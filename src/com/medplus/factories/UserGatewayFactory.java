package com.medplus.factories;

import com.medplus.useCases.UserGateway;

public interface UserGatewayFactory {
	public UserGateway make(String option); 
}
