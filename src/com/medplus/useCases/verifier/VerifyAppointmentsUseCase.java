package com.medplus.useCases.verifier;

import java.util.ArrayList;
import java.util.HashMap;

import com.medplus.entities.Appointment;
import com.medplus.entities.User;
import com.medplus.useCases.Id2NameTranslater;
import com.medplus.useCases.UserGateway;
import com.medplus.useCases.VerifiableBook;
import com.medplus.useCases.VerifyAppointmentsPresenter;

public class VerifyAppointmentsUseCase implements VerifiableBook {
	private UserGateway gw;
	private VerifyAppointmentsPresenter presenter;
	private Id2NameTranslater translater;

	@Override
	public void verify(String id) {
		User user = gw.getUser(id);
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
		for (Appointment appointment : list) {
			addNameToNameList(names, appointment.getPatientID());
			addNameToNameList(names, appointment.getProviderID());
		}
		return names;
	}

	private void addNameToNameList(HashMap<String, String> names, String id) {
		names.put(id, translater.translate(id));
	}

	public void setGw(UserGateway gw)
	{
		this.gw = gw;
	}

	public void setPresenter(VerifyAppointmentsPresenter presenter)
	{
		this.presenter = presenter;
	}

	public void setTranslater(Id2NameTranslater translater) {
		this.translater = translater;
	}
}
