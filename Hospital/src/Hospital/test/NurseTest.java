package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Hospital.*;

public class NurseTest {
    private Nurse nurse;
    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    public void setUp() {
        nurse = new Nurse("Mary", "Poppins", 137, "Fairy", "Your moms house");
        patient1 = new Patient("John", "Doe", 23, "Male", "12 Bobby Ave.");
        patient2 = new Patient("Jane", "Doe", 22, "Female", "13 Sissy St.");
        // Assuming Patient class has a comparable implemented based on a certain attribute, e.g., name
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
        
        boolean result = nurse.addPatient(new Patient("Overflow", "Patient",32 , "Guy", "62 Righty Cres." ));
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
        // This test depends on the implementation of getMeds() method.
        // Assuming getMeds method is properly defined and returns a String.
        String expectedMeds = "Medication1, Medication2";
        // Mock or stub getMeds to return expectedMeds for patient1
        
        String meds = nurse.currentMeds(patient1);
        assertEquals(expectedMeds, meds, "Method should return correct medications");
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
    
    // Additional tests might be necessary for other methods not fully implemented or dependent on external systems, such as databases.
}
