package com.medplus.useCases;

import com.medplus.entities.User;

public class VerifyAppointmentsUseCase implements VerifiableBook {
	private UserGateway gw;
	private VerifyAppointmentsPresenter presenter;

	@Override
	public void verify(String id) {
		User user = gw.getUser(id);
		if(user == null)
		{
			presenter.fail();
			return;
		}
		presenter.succeed(user.getAppointments());		
	}

	public void setGw(UserGateway gw) {
		this.gw = gw;
	}

	public void setPresenter(VerifyAppointmentsPresenter presenter) {
		this.presenter = presenter;
	}
	
}
