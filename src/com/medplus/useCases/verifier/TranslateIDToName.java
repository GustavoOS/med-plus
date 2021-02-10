package com.medplus.useCases.verifier;

import com.medplus.entities.User;
import com.medplus.useCases.Id2NameTranslater;
import com.medplus.useCases.UserGateway;

public class TranslateIDToName implements Id2NameTranslater {
	private UserGateway providerGW;
	private UserGateway patientGW;

	@Override
	public String translate(String id) {
		String name = convertWithGW(id, patientGW);
		return name == null ? convertWithGW(id, providerGW) : name;
	}
	private String convertWithGW(String id, UserGateway gw) {
		User u = gw.getUser(id);
		if(u != null)
			return u.getName();
		return null;
	}
	public void setProviderGW(UserGateway providerGW) {
		this.providerGW = providerGW;
	}
	public void setPatientGW(UserGateway patientGW) {
		this.patientGW = patientGW;
	}


}
