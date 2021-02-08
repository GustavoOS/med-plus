package com.medplus.useCases;

public interface Id2NameTranslater {
	public String translate(String id);
	public void setProviderGW(UserGateway providerGW);
	public void setPatientGW(UserGateway patientGW);
}
