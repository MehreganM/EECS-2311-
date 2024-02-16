package hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

///author: Mehregan Mesgari
public class PatientTest {
    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient("John", "Doe", 30, "Male", "123 Main St");
    }

    @Test
    public void testGetPatientID() {
        assertTrue(patient.getPatientID() > 0, "Patient ID should be greater than 0");
    }

    @Test
    public void testSetAndGetPhysician() {
        Physician physician = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        patient.setPhysician(physician);
        assertEquals(physician, patient.getPhysician(), "Assigned physician should match the one set");
    }

    @Test
    public void testSetName() {
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        assertEquals("Jane", patient.firstName, "First name should be updated to Jane");
        assertEquals("Smith", patient.lastName, "Last name should be updated to Smith");
    }

    @Test
    public void testSetAndGetAge() {
        patient.setAge(25);
        assertEquals(25, patient.getAge(), "Age should be updated to 25");
    }

    @Test
    public void testSetAndGetGender() {
        patient.setGender("Female");
        assertEquals("Female", patient.getGender(), "Gender should be updated to Female");
    }

    @Test
    public void testSetAndGetAddress() {
        patient.setAddress("456 Elm St");
        assertEquals("456 Elm St", patient.getAddress(), "Address should be updated to 456 Elm St");
    }

    @Test
    public void testEquals() {
        Patient otherPatient = new Patient("John", "Doe", 30, "Male", "123 Main St");
        assertTrue(patient.equals(otherPatient), "Patients with the same details should be considered equal");
    }

    @Test
    public void testToString() {
        String expectedString = "Patient [" + patient.getPatientID() + ", [" + patient.firstName + ", " + patient.lastName + ", " + patient.age + ", " + patient.gender + ", " + patient.address + "]]";
        assertEquals(expectedString, patient.toString(), "toString should return the correct patient representation");
    }

    @Test
    public void testCompareTo() {
        Patient otherPatient = new Patient("Amy", "Smith", 28, "Female", "789 Pine St");
        assertTrue(patient.compareTo(otherPatient) != 0, "compareTo should not return 0 for patients with different names");
    }

    @Test
    public void testClearPatientRecord() {
        patient.clearPatientRecord();
        assertNull(patient.getPhysician(), "Physician should be null after clearing patient record");
        assertTrue(patient.clearPatientRecord(), "clearPatientRecord should return true if patient is not assigned to a physician");
    }
}

