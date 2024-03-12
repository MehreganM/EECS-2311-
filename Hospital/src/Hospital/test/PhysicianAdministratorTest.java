package Hospital.src.Hospital.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Hospital.src.Hospital.Physician;
import Hospital.src.Hospital.PhysicianAdministrator;

import static org.junit.jupiter.api.Assertions.*;

import java.security.KeyException;
import java.util.List;

/**
 * @author Parmoun Khalkhali Sharifi
*/

public class PhysicianAdministratorTest {
 
	 private PhysicianAdministrator admin;
	    private Physician physician;
	    
	    
    @BeforeEach
    public void setUp() {
        admin = new PhysicianAdministrator("Andrew", "Dan", 38, "Male", "Williams St");
        physician = new Physician("Marry", "Lili", 45, "Female", "Adler St");
        physician.setSpecialty("Immunology"); 
    }

    @Test
    public void AddPhysiciantest() {
        admin.setAdminSpecialtyType("Immunology");
        assertTrue(admin.addPhysician(physician), "Physician with matching specialty should be added");
    }

    @Test
    public void NonMatchingSpecialtyTest() {
        admin.setAdminSpecialtyType("Neurology");
        assertFalse(admin.addPhysician(physician), "Physician with non-matching specialty should not be added");
    }

    @Test
    public void DeletePhysicianTest() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        
    }

    @Test
    public void SetInvalidSpecialtyTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> admin.setAdminSpecialtyType("Nephrology"));
        assertTrue(exception.getMessage().contains("Invalid specialty"));
    }

    @Test
    public void ExtractPhysicianTest() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertEquals(1, admin.extractPhysician().size(), "Extracted list should contain one physician");
    }
    @Test
    public void SetSpecialtyTest() {
        assertDoesNotThrow(() -> admin.setAdminSpecialtyType("Immunology"), "Setting a valid specialty should not throw an exception.");
        assertEquals("Immunology", admin.getAdminSpecialtyType(), "The specialty should be correctly set to Immunology.");
    }
    @Test
    public void AddPhysicianTest() {
        physician.setSpecialty("Immunology");
        assertFalse(admin.addPhysician(physician), "Should not add a physician if the admin's specialty is not set.");
    }
   @Test
    public void MaxPhysicianTest() {
        admin.setAdminSpecialtyType("Immunology");
        for (int i = 0; i < 25; i++) {
            Physician newPhysician = new Physician("Dr" + i, "Last" + i, 40, "Male", "Address" + i);
            newPhysician.setSpecialty("Immunology");
            admin.addPhysician(newPhysician);
        }
        Physician limitPhysician = new Physician("Limit", "Dr", 40, "Male", "Limit Address");
        limitPhysician.setSpecialty("Immunology");
        assertFalse(admin.addPhysician(limitPhysician), "Should not be able to add more than 25 physicians");
    } 
    @Test
    public void testExtractPhysicians() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        List<Physician> extracted = admin.extractPhysician();
        extracted.remove(physician); // Attempt to modify the extracted list
        assertEquals(1, admin.extractPhysician().size(), "The original list should not be modified");
    }
    @Test
    public void testDeletePhysician2() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertNull(physician.getAdmin(), "Physician's administrator reference should be null after deletion.");
    }
    @Test
    public void testSpecialtyMismatch() {
        admin.setAdminSpecialtyType("Neurology");
        physician.setSpecialty("Immunology"); 
        assertFalse(admin.addPhysician(physician), "Physician with mismatched specialty should not be added.");
    }

}
