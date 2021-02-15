package com.medplus.useCases;

import java.time.LocalDateTime;

public interface Cancelable {
	void cancel(String id, LocalDateTime dateTime);

	public void setUserGW(UserGateway originGW);
	public void setPeerGW(UserGateway targetGW);
	public void setPresenter(CancelAppointmentPresenter presenter);
}
