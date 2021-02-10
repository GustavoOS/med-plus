package com.medplus.useCases;

import java.time.LocalDateTime;

import com.medplus.entities.Canceler;

public interface Cancelable {
	void cancel(String id, LocalDateTime dateTime);

	public void setRootGW(UserGateway originGW);
	public void setTargetGW(UserGateway targetGW);
	public void setPresenter(CancelAppointmentPresenter presenter);

	public void setCanceler(Canceler canceler);
}
