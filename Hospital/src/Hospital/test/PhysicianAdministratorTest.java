package Hospital;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;


public class PhysicianAdministratorTest {

    private PhysicianAdministrator admin;
    private Physician physician;

    @BeforeEach
    public void setUp() {
        admin = new PhysicianAdministrator("Andrew", "Dan", 38, "Male", "Williams St");
        physician = new Physician("Marry", "Lili", 45, "Female", "Adler St", "Immunology");
    }

    @Test
    public void testAddPhysicianWithMatchingSpecialty() {
        admin.setAdminSpecialtyType("Immunology");
        assertTrue("Physician with matching specialty should be added", admin.addPhysician(physician));
    }

    @Test
    public void testAddPhysicianWithNonMatchingSpecialty() {
        admin.setAdminSpecialtyType("Neurology");
        assertFalse("Physician with non-matching specialty should not be added", admin.addPhysician(physician));
    }

    @Test
    public void testDeletePhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertTrue("Physician should be deleted", admin.deletePhysician(physician));
    }

    @Test
    public void testSetInvalidSpecialty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            admin.setAdminSpecialtyType("Cardiology");
        });

        String expectedMessage = "Specialty must be either Immunology, Dermatology, or Neurology.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExtractPhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertEquals("Extracted list should contain one physician", 1, admin.extractPhysician().size());
    }

    @Test
    public void testAddPhysicianLimit() {
        admin.setAdminSpecialtyType("Immunology");
        for (int i = 0; i < 25; i++) {
            admin.addPhysician(new Physician("Name" + i, "Surname", 30 + i, "Gender", "Address" + i, "Immunology"));
        }
        assertFalse("Should not be able to add more than 25 physicians", admin.addPhysician(physician));
    }

    @Test
    void testAdminSpecialtyType() {
        admin.setAdminSpecialtyType("Dermatology");
        assertEquals("Dermatology", admin.getAdminSpecialtyType(), "Specialty should be set to Dermatology");
    }

    @Test
    void testPhysicianAdministratorSet() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertEquals(admin, physician.getAdministrator(), "Physician's administrator should be set to admin");
    } 


    @Test
    void testPhysicianListIntegrityAfterDeletion() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        admin.deletePhysician(physician);
        assertFalse("Physician list should not contain the deleted physician", 
                admin.extractPhysician().contains(physician));
    }

    @Test
    void testCannotAddDuplicatePhysician() {
        admin.setAdminSpecialtyType("Immunology");
        admin.addPhysician(physician);
        assertFalse("Should not be able to add the same physician twice", 
                admin.addPhysician(physician));
    }

    @Test
    void testDeleteNonExistentPhysician() {
        Physician nonExistentPhysician = new Physician("Non", "Existent", 38, "Gender", "No Address", "Immunology");
        assertFalse("Deleting a physician not in list should return false", 
                admin.deletePhysician(nonExistentPhysician));
    }
}
