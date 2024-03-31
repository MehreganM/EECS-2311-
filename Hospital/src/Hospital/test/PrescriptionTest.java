package Hospital;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrescriptionTest {

    private Prescription prescription;

    @Before
    public void setUp() {
        prescription = new Prescription(1, "Dr. Smith", "Amoxicillin", "500mg", "Twice a day");
    }

    @Test
    public void testConstructor() {
        assertEquals("Constructor patient ID", 1, prescription.getPatientId());
        assertEquals("Constructor physician name", "Dr. Smith", prescription.getPhysicianName());
        assertEquals("Constructor medication name", "Amoxicillin", prescription.getMedicationName());
        assertEquals("Constructor dosage", "500mg", prescription.getDosage());
        assertEquals("Constructor instructions", "Twice a day", prescription.getInstructions());
    }

    @Test
    public void testSetPrescriptionId() {
        prescription.setPrescriptionId(100);
        assertEquals("Set prescription ID", 100, prescription.getPrescriptionId());
    }

    @Test
    public void testSetPatientId() {
        prescription.setPatientId(2);
        assertEquals("Set patient ID", 2, prescription.getPatientId());
    }

    @Test
    public void testSetPhysicianName() {
        prescription.setPhysicianName("Dr. Johnson");
        assertEquals("Set physician name", "Dr. Johnson", prescription.getPhysicianName());
    }

    @Test
    public void testSetMedicationName() {
        prescription.setMedicationName("Ibuprofen");
        assertEquals("Set medication name", "Ibuprofen", prescription.getMedicationName());
    }

    @Test
    public void testSetDosage() {
        prescription.setDosage("200mg");
        assertEquals("Set dosage", "200mg", prescription.getDosage());
    }

    @Test
    public void testSetInstructions() {
        prescription.setInstructions("Three times a day");
        assertEquals("Set instructions", "Three times a day", prescription.getInstructions());
    }

   
}

