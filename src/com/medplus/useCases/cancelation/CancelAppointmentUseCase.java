package com.medplus.useCases.cancelation;

import java.time.LocalDateTime;

import com.medplus.entities.Appointment;
import com.medplus.entities.User;
import com.medplus.entities.Utils;
import com.medplus.useCases.CancelAppointmentPresenter;
import com.medplus.useCases.Cancelable;
import com.medplus.useCases.UserGateway;

public class CancelAppointmentUseCase implements Cancelable {
	UserGateway userGW, peerGW;
	CancelAppointmentPresenter presenter;

	User user, peer;

	Appointment userAppointment, peerAppointment;

	@Override
	public void cancel(String id, LocalDateTime dateTime) {
		user = userGW.getUser(id);
		if(cantCancel(dateTime))
		{
			presenter.fail();
			return;
		}

		removeAppointments();
		updateGWs();
		presenter.succeed(userGW.getUser(id).getAppointments());
	}

	private void updateGWs() {
		userGW.put(user);
		peerGW.put(peer);
	}

	private boolean cantCancel(LocalDateTime dateTime) {
		return  user == null ||
				dateTime == null ||
				userAppointmentDoesntExist(dateTime) ||
				peerCantBeFound() ||
				cantFindPeerAppointment(dateTime);
	}

	private Boolean cantFindPeerAppointment(LocalDateTime dateTime) {
		peerAppointment = Utils.findFirstAppointmentWithDateTime(peer.getAppointments(), dateTime);
		return peerAppointment == null;
	}

	private Boolean peerCantBeFound() {
		peer = peerGW.getUser(userAppointment.getPeerID());
		return peer == null;
	}

	private Boolean userAppointmentDoesntExist(LocalDateTime dateTime) {
		userAppointment = Utils.findFirstAppointmentWithDateTime(user.getAppointments(), dateTime);
		return userAppointment == null;
	}


	private void removeAppointments() {
		removeAppointment(userAppointment, user);
		removeAppointment(peerAppointment, peer);
	}

	private void removeAppointment(Appointment appointment, User _user)
	{
		_user.getAppointments().remove(appointment);
	}


	// Auto generated Setters
	public void setUserGW(UserGateway userGW) {
		this.userGW = userGW;
	}

	public void setPeerGW(UserGateway peerGW) {
		this.peerGW = peerGW;
	}

	public void setPresenter(CancelAppointmentPresenter presenter) {
		this.presenter = presenter;
	}
}
