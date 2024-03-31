package Hospital.src.Hospital.test;

import Hospital.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class NurseTest {

    @Test
    void addPatient_Success() {
        Nurse nurse = new Nurse("Jane", "Doe", 30, "Female", "123 Street");
        Patient patient = new Patient("John", "Smith", 25, "Male", "456 Avenue");

        assertTrue(nurse.addPatient(patient), "Patient should be added successfully");
        assertEquals(1, nurse.extractPatientDetail().size(), "Nurse should have one patient"); // Assuming getPatientCount() is a method you implement
    }

    @Test
    void addPatient_Failure() {
        Nurse nurse = new Nurse("Jane", "Doe", 30, "Female", "123 Street");
        // Adding 15 patients to reach the limit
        for (int i = 0; i < 15; i++) {
            Patient patient = new Patient("Patient", "Number" + i, 30, "Gender", "Address" + i);
            nurse.addPatient(patient);
        }

        Patient newPatient = new Patient("New", "Patient", 25, "Male", "789 Lane");
        assertFalse(nurse.addPatient(newPatient), "Adding a new patient should fail due to limit");
    }

    @Test
    void getPatientByName_Found() {
        Nurse nurse = new Nurse("Jane", "Doe", 30, "Female", "123 Street");
        Patient patient = new Patient("John", "Smith", 25, "Male", "456 Avenue");
        nurse.addPatient(patient);

        Patient found = nurse.getPatientByName("John", "Smith");
        assertNotNull(found, "Patient should be found");
        assertEquals("John", found.getFName(), "First name should match");
        assertEquals("Smith", found.getLName(), "Last name should match");
    }

    @Test
    void assignFamilyDoctorToPatient_Success() {
        Nurse nurse = new Nurse("Jane", "Doe", 30, "Female", "123 Street");
        Patient patient = new Patient("John", "Smith", 25, "Male", "456 Avenue");
        FamilyDoctor doctor = new FamilyDoctor("Family", "Doctor", null, "email@email.com", "4163424454"); // Adjusted to avoid passing null if it was an issue

        nurse.assignFamilyDoctorToPatient(patient, doctor);
        assertEquals(doctor, patient.getFamilyDoctor(), "Patient's family doctor should be assigned");
    }

    @Test
    void extractPatientDetail_Sorted() {
        Nurse nurse = new Nurse("Jane", "Doe", 30, "Female", "123 Street");
        Patient patient1 = new Patient("Adam", "Smith", 25, "Male", "456 Avenue");
        Patient patient2 = new Patient("Charles", "Brown", 30, "Male", "789 Lane");
        nurse.addPatient(patient1);
        nurse.addPatient(patient2);

        List<Patient> sortedPatients = nurse.extractPatientDetail();
        assertEquals(2, sortedPatients.size(), "Should have two patients");
        assertEquals("Adam", sortedPatients.get(0).getFName(), "Adam should be first");
        assertEquals("Charles", sortedPatients.get(1).getFName(), "Charles should be second");
    }
}
