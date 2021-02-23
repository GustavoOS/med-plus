package com.medplus.factories;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.medplus.entities.coordinate.CoordinateDS;
import com.medplus.entities.domain.Appointment;
import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.impl.ExamImpl;
import com.medplus.entities.provider.filter.ProviderPicker;
import com.medplus.entities.provider.filters.pickers.PickerChain;
import com.medplus.entities.provider.impl.Provider;

public class TestUtils {
	public static ArrayList<HealthProvider> mountProviderList(LocalDateTime appointmentDateTime){
		ArrayList<HealthProvider> providers = new ArrayList<HealthProvider>();
		providers.add(new Provider("Joe", "blabla.com", "surgeon", (new CoordinateDS()).with(-23.2191582,-45.8843743)));
		providers.add(new Provider("Silva", "silva.com", "surgeon",(new CoordinateDS()).with(-23.2173173,-45.8918382)));
		providers.add(new Provider("Paz", "paz.com", "surgeon",(new CoordinateDS()).with(-22.9546314,-45.8329673)));
		providers.add(new Provider("Benedito", "sera.com", "gynecologyst", (new CoordinateDS()).with(-23.2232479,-45.8934114)));
		providers.get(0).setId("da2ed3e9-566b-4521-8002-6e15f6f9958d");
		providers.get(1).setId("c43a58c6-de7b-4a95-8b41-b1b29e786422");
		providers.get(2).setId("7b11fdbb-0894-4e4b-afaf-880738c84f4c");
		providers.get(3).setId("81a5cb24-d4ba-4789-b5da-3e76ae7ca551");
		ArrayList<Appointment> appointments = mountAppointmentList("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1", appointmentDateTime.withHour(14));
		appointments.addAll(mountAppointmentList("0c9dbc30-2874-4983-a0b7-6885c409ddbc", appointmentDateTime.withHour(10).plusDays(1)));
		providers.get(0).setAppointments(appointments);
		providers.get(1).setAppointments(new ArrayList<Appointment>());
		providers.get(2).setAppointments(new ArrayList<Appointment>());
		providers.get(3).setAppointments(new ArrayList<Appointment>());
		return providers;
	}

	public static ArrayList<HealthProvider> mountProviderList()
	{
		return mountProviderList(LocalDateTime.now());
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

	public static ArrayList<Appointment> mountAppointmentList(String peer, LocalDateTime baseDate)
	{
		ArrayList<Appointment> list = new ArrayList<Appointment>();

		addAppointmentToRaw(list, peer, baseDate);
		addAppointmentToRaw(list, peer, baseDate.plusHours(1));
		addAppointmentToRaw(list, peer, baseDate.plusDays(1));

		return list;
	}

	public static ArrayList<Patient> mountPatientList()
	{
		return mountPatientList(LocalDateTime.now());
	}

	public static ArrayList<Patient> mountPatientList(LocalDateTime baseDate)
	{
		ArrayList<Patient> patients = new ArrayList<Patient>();
		baseDate = baseDate.withMinute(0).withSecond(0).withNano(0);
		Patient p;
		p = createPatient(
				"4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1",
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"Maria",
				true,
				20,
				baseDate.withHour(14));
		patients.add(p);
		p = createPatient(
				"0c9dbc30-2874-4983-a0b7-6885c409ddbc",
				"da2ed3e9-566b-4521-8002-6e15f6f9958d",
				"John",
				false,
				25,
				baseDate.withHour(10).plusDays(1));
		patients.add(p);
		return patients;
	}

	private static Patient createPatient(
			String patientID,
			String providerId,
			String name,
			Boolean isFemale,
			int age,
			LocalDateTime dateTime) {
		Patient p = (new PatientFactoryImpl()).make();
		p.setAppointments(mountAppointmentList(
				providerId,
				dateTime));
		p.setBirth(dateTime.minusYears(age).toLocalDate());
		p.setId(patientID);
		p.setIsFemale(isFemale);
		p.setName(name);
		return p;
	}

	private static void addAppointmentToRaw(ArrayList<Appointment> raw, 
			String peerID,
			LocalDateTime dateTime)
	{
		Appointment appointment = (new AppointmentFactoryImpl()).make();
		appointment.setPeerID(peerID);
		appointment.setDateTime(dateTime);
		raw.add(appointment);
	}

	public static ArrayList<Exam> mountExamList()
	{
		LocalDateTime dateTime = LocalDateTime.now();
		ArrayList<Exam> exams = new ArrayList<Exam>();
		exams.add((new ExamImpl())
						.withId("0ef0c14e-5745-4402-8629-7b4f45c433fb")
						.withInsertionDateTime(dateTime)
						.withTitle("electrocardiogram"));

		exams.add((new ExamImpl())
						.withId("5367dea0-416a-44bf-a71a-df0cf72902c2")
						.withInsertionDateTime(dateTime.plusDays(1))
						.withTitle("urine test"));

		exams.add((new ExamImpl())
						.withId("67e9e81e-c631-479f-aa14-60792d6a1bcf")
						.withInsertionDateTime(dateTime.plusDays(3))
						.withTitle("echocardiogram"));
		return exams;
	}
}
