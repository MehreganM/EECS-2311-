package Hospital;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
/**
 * @author Parmoun Khalkhali Sharifi
*/

public class PhysicianAdministratorTest {

    private PhysicianAdministrator admin;
    private Physician physician;

    // Initializes test objects before each test method
    // A PhysicianAdministrator objects are created
    @BeforeEach
    public void setUp() {
        admin = new PhysicianAdministrator("Andrew", "Dan", 38, "Male", "Williams St");
        physician = new Physician("Marry", "Lili", 45, "Female", "Adler St", "Immunology");
    }

    // Tests adding a physician with a specialty that matches the administrator's specialty
    // This should succeed --> The assertTrue check
    @Test
    public void testAddPhysicianWithMatchingSpecialty() {
        admin.setAdminSpecialtyType("Immunology");
        assertTrue("Physician with matching specialty should be added", admin.addPhysician(physician));
    }

    // Tests adding a physician whose specialty does not match the administrator's specialty
    // This should fail--> The assertFalse check
    @Test
    public void testAddPhysicianWithNonMatchingSpecialty() {
        admin.setAdminSpecialtyType("Neurology");
        assertFalse("Physician with non-matching specialty should not be added", admin.addPhysician(physician));
    }

    // Tests the deletion of a physician who has been previously added
    // This should succeed--> The assertTrue check
    @Test
    public void testDeletePhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertTrue("Physician should be deleted", admin.deletePhysician(physician));
    }
    
    // Tests setting an invalid specialty for the administrator.
    // This should throw an IllegalArgumentException --> The assertThrows check
    @Test
    public void testSetInvalidSpecialty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.setAdminSpecialtyType("Cardiology");
        });

        String expectedMessage = "Specialty must be either Immunology, Dermatology, or Neurology.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Tests retrieving the list of physicians under this administrator after adding one physician.
    // The list size should be 1--> The assertEquals check
    @Test
    public void testExtractPhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertEquals("Extracted list should contain one physician", 1, admin.extractPhysician().size());
    }

    // Tests adding physicians up to the limit (25).
    // After reaching the limit, attempting to add another physician should fail--> The assertFalse check.
    @Test
    public void testAddPhysicianLimit() {
        admin.setAdminSpecialtyType("Immunology");
        for (int i = 0; i < 25; i++) {
            admin.addPhysician(new Physician("Name" + i, "Surname", 30 + i, "Gender", "Address" + i, "Immunology"));
        }
        assertFalse("Should not be able to add more than 25 physicians", admin.addPhysician(physician));
    }

    // Tests setting and getting the administrator's specialty type.
    // The set specialty should match the expected value--> The assertEquals check
    @Test
    void testAdminSpecialtyType() {
        admin.setAdminSpecialtyType("Dermatology");
        assertEquals("Dermatology", admin.getAdminSpecialtyType(), "Specialty should be set to Dermatology");
    }

    // Tests if the administrator correctly sets itself as the administrator of the physician it adds.
    // This checks if the physician's administrator matches the expected administrator --> assertEquals
    @Test
    void testPhysicianAdministratorSet() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertEquals(admin, physician.getAdministrator(), "Physician's administrator should be set to admin");
    } 

    // Tests the integrity of the physician list after deleting a physician.
    // The physician list should not contain the deleted physician--> The assertFalse check.
    @Test
    void testPhysicianListIntegrityAfterDeletion() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        admin.deletePhysician(physician);
        assertFalse("Physician list should not contain the deleted physician", 
                admin.extractPhysician().contains(physician));
    }
    
 // Tests that adding the same physician more than once is not allowed.
    // Attempting to add a duplicate should fail--> The assertFalse check.
    @Test
    void testCannotAddDuplicatePhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertFalse("Should not be able to add the same physician twice", 
                admin.addPhysician(physician));
    }

    // Tests attempting to delete a physician who does not exist in the administrator's list.
    // This should fail-->The assertFalse check.
    @Test
    void testDeleteNonExistentPhysician() {
        Physician nonExistentPhysician = new Physician("Non", "Existent", 38, "Gender", "No Address", "Immunology");
        assertFalse("Deleting a physician not in list should return false", 
                admin.deletePhysician(nonExistentPhysician));
    }
}
