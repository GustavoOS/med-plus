package com.medplus.useCases.attend;

import java.time.LocalDateTime;

import com.medplus.entities.Appointment;
import com.medplus.entities.User;
import com.medplus.entities.Utils;
import com.medplus.useCases.Attendable;
import com.medplus.useCases.AttendancePresenter;
import com.medplus.useCases.CheckableAvailablePatientData;
import com.medplus.useCases.UserGateway;

public class AttendanceUseCase implements Attendable {

	private UserGateway providerGateway;
	private User provider;
	private Appointment appointment;
	private AttendancePresenter presenter;
	private CheckableAvailablePatientData checker;

	@Override
	public void attend(String providerID, LocalDateTime dateTime) {
		if(attendanceIsInvalid(providerID, dateTime))
			presenter.fail();
		else
			checker.check(appointment.getPeerID());
	}

	public void setProviderGateway(UserGateway userGateway) {
		this.providerGateway = userGateway;
	}

	public void setPresenter(AttendancePresenter presenter) {
		this.presenter = presenter;
	}

	private boolean attendanceIsInvalid(String providerID, LocalDateTime dateTime) {
		return providerIsNull(providerID) || appointmentIsNull(dateTime);
	}

	private Boolean providerIsNull(String providerID) {
		provider = providerGateway.getUser(providerID);
		return provider == null;
	}

	private Boolean appointmentIsNull(LocalDateTime dateTime) {
		appointment = Utils.findFirstAppointmentWithDateTime(provider.getAppointments(), dateTime);
		return appointment == null;
	}

	public void setChecker(CheckableAvailablePatientData checker) {
		this.checker = checker;
		this.checker.setPresenter(presenter);
	}


	
	
}
