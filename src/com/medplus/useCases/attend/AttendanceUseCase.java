package com.medplus.useCases.attend;

import java.time.LocalDateTime;

import com.medplus.entities.Appointment;
import com.medplus.entities.User;
import com.medplus.entities.Utils;
import com.medplus.useCases.Attendable;
import com.medplus.useCases.AttendancePresenter;
import com.medplus.useCases.UserGateway;

public class AttendanceUseCase implements Attendable {

	private UserGateway providerGateway, patientGateway;
	private User provider, patient;
	private Appointment appointment;
	private AttendancePresenter presenter;

	@Override
	public void attend(String providerID, LocalDateTime dateTime) {
		provider = providerGateway.getUser(providerID);
		appointment = Utils.findFirstAppointmentWithDateTime(provider.getAppointments(), dateTime);
		patient = patientGateway.getUser(appointment.getPatientID());
		presenter.succeed(PatientExtractor.extract(patient));
	}

	public void setProviderGateway(UserGateway userGateway) {
		this.providerGateway = userGateway;
	}

	@Override
	public void setPatientGateway(UserGateway gw) {
		this.patientGateway = gw;
	}

	public void setPresenter(AttendancePresenter presenter) {
		this.presenter = presenter;
	}

	
}
