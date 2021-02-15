package com.medplus.useCases.verifier;

import java.util.ArrayList;
import java.util.HashMap;

import com.medplus.entities.Appointment;
import com.medplus.entities.User;
import com.medplus.useCases.UserGateway;
import com.medplus.useCases.VerifiableBook;
import com.medplus.useCases.VerifyAppointmentsPresenter;

public class VerifyAppointmentsUseCase implements VerifiableBook {
	private UserGateway userGw;
	private UserGateway peerGW;
	private VerifyAppointmentsPresenter presenter;

	@Override
	public void verify(String id) {
		User user = userGw.getUser(id);
		if(user == null)
		{
			presenter.fail();
			return;
		}
		presenter.succeed(user.getAppointments(), buildNameList(user));
	}

	private HashMap<String, String> buildNameList(User user) {
		ArrayList<Appointment> list = user.getAppointments();
		HashMap<String, String> names = new HashMap<String, String>();
		for (Appointment appointment : list)
			addNameToNameList(names, appointment.getPeerID());
		return names;
	}

	private void addNameToNameList(HashMap<String, String> names, String id) {
		names.put(id, peerGW.getUser(id).getName());
	}

	public void setGw(UserGateway gw)
	{
		this.userGw = gw;
	}

	public void setPresenter(VerifyAppointmentsPresenter presenter)
	{
		this.presenter = presenter;
	}

	public void setPeerGW(UserGateway peerGW) {
		this.peerGW = peerGW;
	}

}
