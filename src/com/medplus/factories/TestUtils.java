package com.medplus.factories;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.medplus.entities.Appointment;
import com.medplus.entities.CoordinateDS;
import com.medplus.entities.HealthProvider;
import com.medplus.entities.Patient;
import com.medplus.entities.ProviderPicker;
import com.medplus.entities.PickerChain;
import com.medplus.entities.Provider;

public class TestUtils {
	public static ArrayList<HealthProvider> mountProviderList(){
		ArrayList<HealthProvider> providers = new ArrayList<HealthProvider>();
		providers.add(new Provider("Jo�o", "blabla.com", "surgeon", new CoordinateDS(-23.2191582,-45.8843743)));
		providers.add(new Provider("Silva", "silva.com", "surgeon", new CoordinateDS(-23.2173173,-45.8918382)));
		providers.add(new Provider("Paz", "paz.com", "surgeon", new CoordinateDS(-22.9546314,-45.8329673)));
		providers.add(new Provider("Benedito", "sera.com", "gynecologyst", new CoordinateDS(-23.2232479,-45.8934114)));
		providers.get(0).setId("da2ed3e9-566b-4521-8002-6e15f6f9958d");
		providers.get(1).setId("c43a58c6-de7b-4a95-8b41-b1b29e786422");
		providers.get(2).setId("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		providers.get(3).setId("81a5cb24-d4ba-4789-b5da-3e76ae7ca551");
		providers.get(0).setAppointments(new ArrayList<Appointment>());
		providers.get(1).setAppointments(new ArrayList<Appointment>());
		providers.get(2).setAppointments(new ArrayList<Appointment>());
		providers.get(3).setAppointments(new ArrayList<Appointment>());
		return providers;
	}

	public static ProviderPicker mountPickerChain()
	{
		PickerFactory factory = new PickerFactoryImpl();
		return
			withNext(factory.Make("specialization"),
					withNext(factory.Make("local"),
							factory.Make("accept")
							)
					);
				
	}

	public static ProviderPicker withNext(ProviderPicker picker, ProviderPicker next)
	{
		((PickerChain) picker).setNextPicker(next);
		return picker;
	}

	public static Appointment createAppointment() {
		return (new AppointmentFactoryImpl()).make();
	}

	public static ArrayList<Appointment> mountAppointmentList(String provider, String patient, LocalDateTime baseDate)
	{
		ArrayList<Appointment> list = new ArrayList<Appointment>();

		addAppointmentToRaw(list, provider, patient, baseDate);
		addAppointmentToRaw(list, provider, patient, baseDate.plusHours(1));
		addAppointmentToRaw(list, provider, patient, baseDate.plusDays(1));

		return list;
	}

	public static ArrayList<Patient> mountPatientList()
	{
		ArrayList<Patient> patients = new ArrayList<Patient>();
		Patient p = (new PatientFactoryImpl()).make();
		p.setAppointments(mountAppointmentList(
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
				LocalDateTime.now().withHour(14)));
		p.setBirth(LocalDateTime.now().minusYears(20).toLocalDate());
		p.setId("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		p.setIsFemale(true);
		p.setName("Maria");
		patients.add(p);
		p = (new PatientFactoryImpl()).make();
		p.setAppointments(mountAppointmentList(
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"0c9dbc30-2874-4983-a0b7-6885c409ddbc",
				LocalDateTime.now().withHour(10).plusDays(1)));
		p.setBirth(LocalDateTime.now().minusYears(25).toLocalDate());
		p.setId("0c9dbc30-2874-4983-a0b7-6885c409ddbc");
		p.setIsFemale(false);
		p.setName("John");
		patients.add(p);
		return patients;
	}

	private static void addAppointmentToRaw(ArrayList<Appointment> raw, 
			String providerId,
			String patientId,
			LocalDateTime dateTime)
	{
		Appointment appointment = (new AppointmentFactoryImpl()).make();
		appointment.setProviderID(providerId);
		appointment.setPatientID(patientId);
		appointment.setDateTime(dateTime);
		raw.add(appointment);
	}
}
