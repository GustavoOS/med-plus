package com.medplus.useCases;

import java.time.LocalDateTime;

public interface Attendable {
	void attend(String providerID, LocalDateTime dateTime);
	void setProviderGateway(UserGateway gw);
	void setPatientGateway(UserGateway gw);
	public void setPresenter(AttendancePresenter presenter);
}
