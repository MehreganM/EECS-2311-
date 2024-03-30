package Hospital.src.Hospital.test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FamilyDoctortester {
    private FamilyDoctor familyDoctor;
    private Laboratory laboratory;
    private Patient patient;

    @BeforeEach
    void setUp() {
        laboratory = new Laboratory();
        familyDoctor = new FamilyDoctor("Dr. Nora", "GP", laboratory, "norak@gmail.com", "654887987");
        patient = new Patient("Alice", "Small", 23, "Female", "15 Queens St");
    }

    @Test
    void getName_ReturnsCorrectName() {
        assertEquals("Dr. Nora", familyDoctor.getName());
    }

    @Test
    void setName_CorrectlySetsName() {
        familyDoctor.setName("Dr. Jones");
        assertEquals("Dr. Jones", familyDoctor.getName());
    }

    @Test
    void getEmail_ReturnsCorrectEmail() {
        assertEquals("norak@gmail.com", familyDoctor.getEmail());
    }

    @Test
    void setEmail_CorrectlySetsEmail() {
        familyDoctor.setEmail("dr.jones@hospital.com");
        assertEquals("dr.jones@hospital.com", familyDoctor.getEmail());
    }

    @Test
    void getTelephoneNumber_ReturnsCorrectNumber() {
        assertEquals("654887987", familyDoctor.getTelephoneNumber());
    }

    @Test
    void setTelephoneNumber_CorrectlySetsNumber() {
        familyDoctor.setTelephoneNumber("555-5678");
        assertEquals("555-5678", familyDoctor.getTelephoneNumber());
    }

    @Test
    void getSpecialty_ReturnsCorrectSpecialty() {
        assertEquals("GP", familyDoctor.getSpecialty());
    }

    @Test
    void setSpecialty_CorrectlySetsSpecialty() {
        familyDoctor.setSpecialty("Cardiology");
        assertEquals("Cardiology", familyDoctor.getSpecialty());
    }

    @Test
    void toString_ReturnsCorrectString() {
        String expectedString = "FamilyDoctor{" +
                "name='" + familyDoctor.getName() + '\'' +
                ", specialty='" + familyDoctor.getSpecialty() + '\'' +
                ", email='" + familyDoctor.getEmail() + '\'' +
                ", phone='" + familyDoctor.getTelephoneNumber() + '\'' +
                '}';
        assertEquals(expectedString, familyDoctor.toString());
    }

    @Test
    void getLabTestResults_ReturnsNullForUnknownTest() {
        assertNull(familyDoctor.getLabTestResults(patient, "Unknown Test"));
    }

}
