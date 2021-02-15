package com.medplus.useCases;

public interface VerifiableBook {
	public void verify(String id);
	public void setPresenter(VerifyAppointmentsPresenter presenter);
	public void setPeerGW(UserGateway peerGW);
}
