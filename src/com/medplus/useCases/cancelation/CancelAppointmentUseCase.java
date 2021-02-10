package com.medplus.useCases.cancelation;

import java.time.LocalDateTime;

import com.medplus.entities.Canceler;
import com.medplus.entities.User;
import com.medplus.useCases.CancelAppointmentPresenter;
import com.medplus.useCases.Cancelable;
import com.medplus.useCases.UserGateway;

public class CancelAppointmentUseCase implements Cancelable {
	UserGateway rootGW, targetGW;
	CancelAppointmentPresenter presenter;
	Canceler canceler;

	User rootUser, targetUser;

	@Override
	public void cancel(String id, LocalDateTime dateTime) {
		rootUser = rootGW.getUser(id);
		if(cantCancel(dateTime))
		{
			presenter.fail();
			return;
		}
		presenter.succeed(rootUser.getAppointments());
		cancelTargetAppointment(dateTime);
	}

	private boolean cantCancel(LocalDateTime dateTime) {
		return rootUser == null || dateTime == null || 
				!canceler.cancel(
						rootUser.getAppointments(),
						dateTime);
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

	public void setPresenter(CancelAppointmentPresenter presenter) {
		this.presenter = presenter;
	}

	public void setCanceler(Canceler canceler) {
		this.canceler = canceler;
	}
}
