package Hospital.src.Hospital.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class NurseTest {
    private Nurse nurse;
    private Patient patient1;
    private Patient patient2;
    private StubDB stub;

    @BeforeEach
    public void setUp() {
        
    	nurse = new Nurse("Mary", "Poppins",121,"Fairy", "Your moms house");
        patient1 = new Patient("John", "Doe", 23, "Male", "12 Bobby Ave.");
        patient2 = new Patient("Jane", "Doe", 22, "Female", "13 Sissy St.");
        stub = new StubDB();
        nurse.setPatientDB(stub);
        stub.addMedicationsForPatient(patient1.getPatientID(), List.of("Medicine A", "Medicine B"));
        // Assuming Patient class has a comparable implemented based on a certain attribute, e.g., name
    }
    @Test
    public void searchFamilyDoctorsByNameFound() {
        List<FamilyDoctor> results = nurse.searchFamilyDoctors("Anna");
        assertFalse(results.isEmpty(), "Should find at least one doctor by name");
        assertTrue(results.stream().anyMatch(doc -> doc.getName().contains("Anna")), "Doctor named Anna should be found");
    }

    @Test
    public void searchFamilyDoctorsByEmailFound() {
        List<FamilyDoctor> results = nurse.searchFamilyDoctors("marklo@gmail.com");
        assertFalse(results.isEmpty(), "Should find at least one doctor by email");
        assertTrue(results.stream().anyMatch(doc -> doc.getEmail().equals("marklo@gmail.com")), "Doctor with email marklo@gmail.com should be found");
    }

    @Test
    public void searchFamilyDoctorsByPhoneNumberFound() {
        List<FamilyDoctor> results = nurse.searchFamilyDoctors("555-5678");
        assertFalse(results.isEmpty(), "Should find at least one doctor by phone number");
        assertTrue(results.stream().anyMatch(doc -> doc.getTelephoneNumber().equals("555-5678")), "Doctor with phone number 555-5678 should be found");
    }

    @Test
    public void searchFamilyDoctorsByNonexistentCriteria() {
        List<FamilyDoctor> results = nurse.searchFamilyDoctors("nonexistent");
        assertTrue(results.isEmpty(), "Should not find any doctors with nonexistent search criteria");
    }

    @Test
    public void testAddPatient() {
        boolean result = nurse.addPatient(patient1);
        assertTrue(result, "Patient should be added successfully");
        assertEquals(1, nurse.patients.size(), "Nurse's patient list should contain 1 patient");
    }
    
    @Test
    public void testAddPatientWhenFull() {
        // Fill the nurse's patient list
        for (int i = 0; i < 15; i++) {
            nurse.addPatient(new Patient("Patient" + i, "Lastname",29,"Non-Binary","26 Lefty Sq."));
        }
        
        boolean result = nurse.addPatient(new Patient("Overflow", "Patient",32 , "Gay", "62 Righty Cres." ));
        assertFalse(result, "Patient should not be added as the list is full");
        assertEquals(15, nurse.patients.size(), "Nurse's patient list should contain 15 patients");
    }

    @Test
    public void testExtractPatientDetail() {
        nurse.addPatient(patient2); // Assuming patient2 should come before patient1 alphabetically
        nurse.addPatient(patient1);

        List<Patient> sortedPatients = nurse.extractPatientDetail();
        
        assertEquals(2, sortedPatients.size(), "Extracted list should contain 2 patients");
        assertAll("Patients should be sorted",
            () -> assertEquals(patient2, sortedPatients.get(0)),
            () -> assertEquals(patient1, sortedPatients.get(1))
        );
    }

    @Test
    public void testCurrentMeds() {
    	 //   Patient patient = new Patient("John", "Doe", 23, "Male", "12 Bobby Ave."); // Ensure patient ID matches StubDB
    	    String expectedMeds = "Medicine A, Medicine B"; // Expected result based on StubDB initialization
    	    assertEquals(expectedMeds, nurse.currentMeds(patient1), "Medications should match expected list.");
    	}


    @Test
    public void testEquals() {
        Nurse anotherNurse = new Nurse("Mary", "Poppins", 137, "Fairy", "Your moms house");
        // Assuming Nurse class has fields and setters for them (e.g., firstName, lastName, etc.)
        // Set up both nurses with identical information
        
        assertTrue(nurse.equals(anotherNurse), "Nurses with the same information should be equal");
    }

    @Test
    public void testCompareTo() {
        Nurse anotherNurse = new Nurse("Cee", "Min", 123, "Demon", "Your dads house");
        // Set up the two nurses with different information
        
        assertNotEquals(0, nurse.compareTo(anotherNurse), "Comparison should not return 0 for nurses with different information");
    }
    @Test
    public void testAssignFamilyDoctorToPatient() {
        FamilyDoctor familyDoctor = new FamilyDoctor("Dr. Smith", "Cardiology", null, null, null);
        nurse.assignFamilyDoctorToPatient(patient1, familyDoctor);
        assertEquals(familyDoctor, patient1.getFamilyDoctor(), "Family doctor should be assigned to patient");
    }

    @Test
    public void testGetPatientByNameFound() {
        nurse.addPatient(patient1);
        Patient found = nurse.getPatientByName("John", "Doe");
        assertNotNull(found, "Patient should be found");
        assertEquals("John", found.getFName(), "First name should match");
        assertEquals("Doe", found.getLName(), "Last name should match");
    }

    @Test
    public void testGetPatientByNameNotFound() {
        nurse.addPatient(patient1);
        Patient notFound = nurse.getPatientByName("Nonexistent", "Patient");
        assertNull(notFound, "Nonexistent patient should not be found");
    }

    @Test
    public void testFulfilMedicationRemoval() {
        patient1.medications.add("Medicine C");
        nurse.fulfilMed(patient1, "Medicine C");
        assertFalse(patient1.medications.contains("Medicine C"), "Medication should be removed from patient's list");
    }
    }
