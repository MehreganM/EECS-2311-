package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import eecs2311.Patient;
import eecs2311.Physician;
import eecs2311.PhysicianAdminstrator;

/**
 * This class is designed to test the Physician class. 
 * @author Amira Mohamed 
 */

class PhysicianTest {

	static Physician physician;
	static Physician physician2;
	static Patient patient;
	static Patient patient1, patient2, patient3, patient4, patient5, patient6, 
	patient7,patient8, patient9, patient10; 
	static ArrayList<Patient> patients, patients2;
	PhysicianAdminstrator admin;
	static int employee;

	/*
	 * This is the set up to create object instances for testing. 
	 * Setting up objects of physician as physician and physician2
	 * Setting up objects of Patient and patient lists as patient, patient1, patient2,
	 * patient3, patient4, patient5, patient6, patient7, patient8, patient9, patient10, 
	 * and patients as the arrayList.
	 */
	
	@BeforeAll
	static void setUp() {
		physician = new Physician("Andrew", "Smith", 32, "Male", "55 Bloor St");
		physician.setSpecialty("Dermatology");
		employee = physician.getEmployeeID();
		
		physician2 = new Physician("Andrew", "Smith", 30, "Male", "55 Bloor St");
		physician.setSpecialty("Dermatology");

		
		patients = new ArrayList<>();
		patient = new Patient("Joe"," Andrew", 35, "Male","125 Pharmacy Ave");

		patients2 = new ArrayList<>();
		patient1 = new Patient("Joe"," Smith",25, "Male","125 Young Street");
		patient2 = new Patient("Noah"," Jack",56, "Male","59 Bellamy Road");
		patient3 = new Patient("Liam"," Smith",39, "Male","136 Young Street");
		patient4 = new Patient("Lucas"," John",44, "Male","155 Bloor Street");
		patient5 = new Patient("Oliver"," Henry",46, "Male","125 Young Street");
		patient6 = new Patient("Emily"," Smith",25, "Female","125 Bloor Street");
		patient7 = new Patient("Camila"," Lucas",25, "Female","45 Eglinton Ave");
		patient8 = new Patient("Eva"," Liam",31, "Female","125 Victoria Street");
		patient9 = new Patient("Clara"," Henry",22, "Female","56 Lawerance Ave");
		patient10 = new Patient("Ella"," Smith",23, "Female","125 Young Street");
		
	}

	/*
	 * This is testing the getSpecialty method on the physician. We are comparing the 
	 * expected specialty with the output of the getSpecialty method. They should be equal.
	 */
	
	@Test
	void getSpecialtytest() {
		String expectedSpecialty = "Dermatology";
		assertEquals(expectedSpecialty, physician.getSpecialty());
	}
	
	/*
	 * This is testing the setSpecialty method on the physician. We are setting the specialty 
	 * of the physician and then comparing it with the output of the getSpecialty method.
	 * They should be equal.
	 */
	
	@Test
	void setSpecialtytest() {
		String expectedSpecialty = "Cardiology";
		physician.setSpecialty("Cardiology");
		assertEquals(expectedSpecialty, physician.getSpecialty());
	}
	
	/*
	 * This is testing the setSpecialty method on the physician for an invalid input. We are 
	 * trying to set the specialty to a specialty that are not from the list of the possible 
	 * specialties. The list of specialty is: Radiology, Dermatology, Neurology, Cardiology,
	 * Hematology, and Immunology. The setSpecialty method should throw an IllegalArgumentException
	 * for the invalid specialty.
	 */
	
	@Test
	void setSpecialtytestException() {
		assertThrows(IllegalArgumentException.class, ()-> physician.setSpecialty("Surgery"));
	}
	
	/*
	 * Testing the addPatient method and the patients arrayList by adding one patient to the
	 * patients list and check the size of the arrayList. I used assertTrue to verify that
	 * the patient was added to the physician's patient list and the assertEquals to verify 
	 * that the number of patients in the array is one. 
	 */
	
	@Test
	void patientsSizeTest() {
		physician.addPatient(patient);
		patients.add(patient);
		int expected = 1;
		assertTrue(physician.addPatient(patient));
		assertEquals(expected, patients.size());
	}
	
	/*
	 * Testing the extractPatientDetail method by adding two patients to the expected list and
	 * adding two patients to the physician's patient list and compare it with the output of the
	 * extractPatientDetail method. I used assertIterableEquals to compare the two lists.
	 */
	@Test
	void extractPatientDetailTest() {
		List<Patient> expected = new ArrayList<>();
		expected.add(patient);
		expected.add(patient1);
		physician.addPatient(patient);
		physician.addPatient(patient1);
		assertIterableEquals(expected, physician.extractPatientDetail());
	}
	
	/*
	 * Testing the setAdmin method by comparing the expected string and the actual output from
	 * the getAdmin method. They should be the same.
	 */
	
	@Test
	void setAdminTest() {
		physician.setAdmin(admin);
		PhysicianAdminstrator expected = admin;
		assertEquals(expected, physician.getAdmin());
	}

	/*
	 * Testing the equals method to check whether physician and physician 2 are the same. 
	 * It should return false since they do not have the same age. 
	 */
	
	@Test
	void equalsMethodTest() {
		assertFalse(physician.equals(physician2));
	}
	
	/*
	 * Testing the addPatient method by adding one patient to the physician's patient list.
	 * The addPatient should return true as there is no patients in the list and therefore
	 * the patient can be added. 
	 */
	@Test
	void addPatientTest() {
		assertTrue(physician.addPatient(patient), "Patient is successfully added");
	}
	
	/*
	 * Testing if we have the maximum number of patients for the physician.
	 * Physician has only one patient so the hasMaximumPatients method should
	 * return false. 
	 */
	@Test
	void hasMaxPatientsTest() {
		assertFalse(physician.hasMaximumPatients());
	}
	
	/*
	 * Adding the maximum number of patients to the physician 2 list to test if the 
	 * method will add more patients after the maximum number or not. It should not 
	 * allow more patients to be added. Therefore, the addPatient method will 
	 * return false. 
	 */
	
	@Test
	void addingPatientsTest() {
		physician2.addPatient(patient1);
		physician2.addPatient(patient2);
		physician2.addPatient(patient3);
		physician2.addPatient(patient4);
		physician2.addPatient(patient5);
		physician2.addPatient(patient6);
		physician2.addPatient(patient7);
		physician2.addPatient(patient8);
		physician2.addPatient(patient9);
		physician2.addPatient(patient10);
	
		assertFalse(physician2.addPatient(patient));
	}
	
	/*
	 * Testing if we have the maximum number of patients for the physician 2.
	 * Physician2 has the maximum number of patients and therefore the hasMaximumPatients
	 * method should return true. 
	 */
	
	@Test
	void hasMaxPatientsTest2() {
		assertTrue(physician2.hasMaximumPatients());
	}
	
	/*
	 * Testing the toString method to print the physician details.
	 */
	@Test 
	void toStringTest() {
		String expected = "Physician [firstName=" + "Andrew" + ", lastName=" + "Smith" + ", employeeID=" + employee + ", age=" + 32 + ", gender=" + "Male" + ","
		+ " address=" + "55 Bloor St" + ", specialty=" + "Dermatology" + "]";
		assertEquals(expected, physician.toString());
	}
}
