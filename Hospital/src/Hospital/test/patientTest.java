package hospital;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

///author: Mehregan Mesgari
public class PatientTest {
    private Patient patient;

    // this is the setUp method that sets the requirements for each test case before the test
    
    @BeforeEach
    public void setUp() {
        patient = new Patient("John", "Doe", 30, "Male", "123 Main St");
    }
    
    /*
     * testGetPatientID test the getPatientID method and checks if the returned value is correct 
     */
    
    @Test
    public void testGetPatientID() {
        assertTrue(patient.getPatientID() == 1000, "Patient ID should be greater than 0");
    }
    
    /*
     * testSetAndGetPhysician tests if the returnPhysician method works correctly and returns the doctor that is set ot he patient
     */

    @Test
    public void testSetAndGetPhysician() {
        Physician physician = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        patient.setPhysician(physician);
        assertEquals(physician, patient.getPhysician(), "Assigned physician should match the one set");
    }
    
    /*
     * testSetName checks if the setName method works correctly and set patients name as needed 
     */

    @Test
    public void testSetName() {
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        assertEquals("Jane", patient.firstName, "First name should be updated to Jane");
        assertEquals("Smith", patient.lastName, "Last name should be updated to Smith");
    }

    /*
     * testSetAndGetAge checks if the setAge method works correctly and set patients age as needed and returns it correctly  
     */
    
    @Test
    public void testSetAndGetAge() {
        patient.setAge(25);
        assertEquals(25, patient.getAge(), "Age should be updated to 25");
    }

    /*
     * testSetAndGetGender checks if the setGender method works correctly and set gender as needed and returns it correctly 
     */
    
    @Test
    public void testSetAndGetGender() {
        patient.setGender("Female");
        assertEquals("Female", patient.getGender(), "Gender should be updated to Female");
    }
    
    /*
     * testSetAndGetAddress checks if the setAddress method works correctly and set address as needed and returns it correctly  
     */
    

    @Test
    public void testSetAndGetAddress() {
        patient.setAddress("456 Elm St");
        assertEquals("456 Elm St", patient.getAddress(), "Address should be updated to 456 Elm St");
    }
    
    /*
     * testEquals tests the equals method that is overritten for the patient method
     */

    @Test
    public void testEquals() {
        Patient otherPatient = new Patient("John", "Doe", 30, "Male", "123 Main St");
        assertTrue(patient.equals(otherPatient), "Patients with the same details should be considered equal");
    }
    
    /*
     * testToString tests the testToString method that is overritten for the patient method
     */


    @Test
    public void testToString() {
        String expectedString = "Patient [" + patient.getPatientID() + ", [" + patient.firstName + ", " + patient.lastName + ", " + patient.age + ", " + patient.gender + ", " + patient.address + "]]";
        assertEquals(expectedString, patient.toString(), "toString should return the correct patient representation");
    }
    
    
    /*
     * testCompareTo tests the testToSttestCompareToring method that is overritten for the patient method
     */

    @Test
    public void testCompareTo() {
        Patient otherPatient = new Patient("Amy", "Smith", 28, "Female", "789 Pine St");
        assertTrue(patient.compareTo(otherPatient) != 0, "compareTo should not return 0 for patients with different names");
    }

    /*
     * testClearPatientRecord checks if the patient record will be succesfully  clearned after their discharge
     */
    
    @Test
    public void testClearPatientRecord() {
        patient.clearPatientRecord();
        assertNull(patient.getPhysician(), "Physician should be null after clearing patient record");
        assertTrue(patient.clearPatientRecord(), "clearPatientRecord should return true if patient is not assigned to a physician");
    }
}

