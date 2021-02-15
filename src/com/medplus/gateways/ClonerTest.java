package com.medplus.gateways;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medplus.entities.domain.Appointment;
import com.medplus.entities.domain.Exam;
import com.medplus.entities.domain.HealthProvider;
import com.medplus.entities.domain.Patient;
import com.medplus.entities.exam.impl.ExamImpl;
import com.medplus.factories.PatientFactoryImpl;
import com.medplus.factories.TestUtils;

class ClonerTest {

	ArrayList<Appointment> rawAppointmentList;

	@BeforeEach
	void setup()
	{
		rawAppointmentList =
				TestUtils.mountAppointmentList(
						"f60579ed-6de3-49ee-9229-0bb027d5b74b",
						LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)));
	}

	@Test
	void testAppointmentClone() {
		verifySingleAppointmentClone(Cloner.cloneAppointment(rawAppointmentList.get(0)), 0);
	}

	@Test
	void copiedListShouldHaveDifferentReferences()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Cloner.cloneProviderList(raw);
		assertEquals(4, result.size());
		for(int i = 0; i < 4; i++)
		{
			assertNotSame(result.get(i), raw.get(i));
			assertEquals(raw.get(i).getName(), result.get(i).getName());
		}
	}

	@Test
	void changeInCopiedListShouldNotChangeTheOriginal()
	{
		ArrayList<HealthProvider> raw, result;
		raw = TestUtils.mountProviderList();
		result = Cloner.cloneProviderList(raw);
		result.get(0).setName("Corleone");
		assertEquals("Joe", raw.get(0).getName());
		assertEquals("Corleone", result.get(0).getName());
	}

	@Test
	void testNullClones()
	{
		assertNotNull(new Cloner());
		assertNull(Cloner.cloneAppointment(null));
		assertNull(Cloner.cloneAppointmentList(null));
		assertNull(Cloner.cloneProvider(null));
		assertNull(Cloner.cloneProviderList(null));
	}

	@Test
	void cloneAppointmentList() {
		ArrayList<Appointment> appointments = Cloner.cloneAppointmentList(rawAppointmentList);
		assertNotNull(appointments);
		assertEquals(rawAppointmentList.size(), appointments.size());
		for(int i = 0; i < appointments.size(); i++)
			verifySingleAppointmentClone(appointments.get(i), i);
		assertNotSame(appointments, rawAppointmentList);
	}

	@Test
	void clonePatient()
	{
		Patient p = (new PatientFactoryImpl()).make();
		p.setAppointments(rawAppointmentList);
		p.setBirth(LocalDate.now().minusYears(20));
		p.setId("4f24bdb4-4f0c-4d85-b8b4-44f757ba1bb1");
		p.setIsFemale(true);
		p.setName("Maria");
		p.setExams(TestUtils.mountExamList());
		Patient copy = Cloner.clonePatient(p);
		assertEquals(p.getBirth(), copy.getBirth());
		assertEquals("Maria", copy.getName());
		assertTrue(p.getIsFemale());
		assertEquals(p.getId(), copy.getId());
		assertNotSame(p, copy);
		assertEquals(3, copy.getExams().size());
	}


	private void verifySingleAppointmentClone(Appointment appointment, int index) {
		assertNotNull(appointment);
		assertEquals(rawAppointmentList.get(index).getDateTime(), appointment.getDateTime());
		assertNotSame(rawAppointmentList.get(index), appointment);
	}

	@Test
	void cloneExam()
	{
		LocalDateTime dateTime = LocalDateTime.now();
		Exam exam = (new ExamImpl())
						.withId("0ef0c14e-5745-4402-8629-7b4f45c433fb")
						.withInsertionDateTime(dateTime)
						.withTitle("electrocardiogram");
		Exam copy = Cloner.cloneExam(exam);
		assertNotNull(copy);
		assertNotSame(exam, copy);
		assertExamClone(
				"electrocardiogram",
				"0ef0c14e-5745-4402-8629-7b4f45c433fb",
				dateTime, copy);
	}

	@Test
	void cloneNullExamShallReturnNull()
	{
		assertNull(Cloner.cloneExam(null));
	}

	@Test
	void cloneExamList()
	{
		ArrayList<Exam> exams = TestUtils.mountExamList();
		LocalDateTime dateTime = exams.get(0).getInsertionDateTime();
		ArrayList<Exam> copy = Cloner.cloneExamList(exams);
		assertNotNull(copy);
		assertNotSame(copy, exams);
		assertEquals(3, copy.size());
		assertNotSame(copy.get(0), exams.get(0));
		assertNotSame(copy.get(1), exams.get(1));
		assertNotSame(copy.get(2), exams.get(2));
		assertExamClone(
				"electrocardiogram",
				"0ef0c14e-5745-4402-8629-7b4f45c433fb",
				dateTime, copy.get(0));
		assertExamClone(
				"urine test",
				"5367dea0-416a-44bf-a71a-df0cf72902c2",
				dateTime.plusDays(1), copy.get(1));
		assertExamClone(
				"echocardiogram",
				"67e9e81e-c631-479f-aa14-60792d6a1bcf",
				dateTime.plusDays(3), copy.get(2));
	}

	@Test
	void cloneNullExamList()
	{
		assertNull(Cloner.cloneExamList(null));
	}

	private void assertExamClone(String title, String id, LocalDateTime dateTime, Exam copy) {
		assertEquals(title, copy.getTitle());
		assertEquals(id, copy.getId());
		assertEquals(dateTime, copy.getInsertionDateTime());
	}
}
