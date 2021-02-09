package com.medplus.useCases;

import java.time.LocalDateTime;

import com.medplus.entities.Canceler;
import com.medplus.entities.User;

public class CancelAppointmentUseCase implements Cancelable {
	UserGateway rootGW, targetGW;
	VerifyAppointmentsPresenter presenter;
	Canceler canceler;

	User rootUser, targetUser;

	@Override
	public void cancel(String id, LocalDateTime dateTime) {
		rootUser = rootGW.getUser(id);
		if(rootUser == null || dateTime == null || !canceler.cancel(
				rootUser.getAppointments(), dateTime))
		{
			presenter.fail();
			return;
		}
		presenter.succeed(rootUser.getAppointments(), null);
		cancelTargetAppointment(dateTime);
	}

	private void cancelTargetAppointment(LocalDateTime dateTime) {
		rootGW.put(rootUser);
		targetUser = targetGW.getUser(canceler.getCanceledAppointmentTargetUserID());
		canceler.cancel(targetUser.getAppointments(), dateTime);
		targetGW.put(targetUser);
	}

	// Auto generated Setters

	public void setRootGW(UserGateway originGW) {
		this.rootGW = originGW;
	}

	public void setTargetGW(UserGateway targetGW) {
		this.targetGW = targetGW;
	}

	public void setPresenter(VerifyAppointmentsPresenter presenter) {
		this.presenter = presenter;
	}

	public void setCanceler(Canceler canceler) {
		this.canceler = canceler;
	}
}
