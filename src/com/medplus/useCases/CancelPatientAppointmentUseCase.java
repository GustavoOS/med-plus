package com.medplus.useCases;

import java.time.LocalDateTime;

import com.medplus.entities.Canceler;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Patient;

public class CancelPatientAppointmentUseCase implements Cancelable {
	UserGateway patientGW;
	ProviderGateway providerGW;
	VerifyAppointmentsPresenter presenter;
	Canceler canceler;

	Patient patient;
	HealthProvider provider;

	@Override
	public void cancel(String id, LocalDateTime dateTime) {
		patient = (Patient) patientGW.getUser(id);
		if(patient == null || dateTime == null || !canceler.cancel(
				patient.getAppointments(), dateTime))
		{
			presenter.fail();
			return;
		}
		presenter.succeed(patient.getAppointments(), null);
		cancelProviderAppointment(dateTime);
	}


	private void cancelProviderAppointment(LocalDateTime dateTime) {
		patientGW.put(patient);
		provider = (HealthProvider) providerGW.getUser(canceler.getCanceledAppointmentUnknownID());
		canceler.cancel(provider.getAppointments(), dateTime);
		providerGW.put(provider);
	}


	// Setters

	public void setProviderGW(ProviderGateway providerGW) {
		this.providerGW = providerGW;
	}

	public void setPatientGW(UserGateway patientGW) {
		this.patientGW = patientGW;
	}

	public void setPresenter(VerifyAppointmentsPresenter presenter) {
		this.presenter = presenter;
	}

	public void setCancel(Canceler canceler) {
		this.canceler = canceler;
	}

}
