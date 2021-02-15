package com.medplus.useCases;

import com.medplus.entities.domain.User;

public interface UserGateway {
	public User getUser(String id);
	public void put(User patient);
}
