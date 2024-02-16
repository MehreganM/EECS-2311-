package hospital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//author: Mehregan Mesgari

public class HospitalTest {
    private Hospital hospital;
    private Director director;

    @BeforeEach
    public void setUp() {
        director = new Director("John", "Doe", 50, "Male", "123 Main St");
        hospital = new Hospital(director);

        
    }

    @Test
    public void testSetAndGetHospDirector() {
        assertEquals(director, hospital.getHospDirector(), "Director should match the one set in setUp method");
        
        Director newDirector = new Director("Jane", "Smith", 45, "Female", "456 Elm St");
        hospital.setHospDirector(newDirector);
        assertEquals(newDirector, hospital.getHospDirector(), "Director should be updated to the new director");
    }

    @Test
    public void testAddAdministratorSuccess() {
        PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        assertTrue(hospital.addAdministrator(admin), "Administrator should be successfully added");
    }

    @Test
    public void testAddAdministratorFailureDueToDuplicateSpecialty() {
        PhysicianAdministrator admin1 = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin1.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin1);

        PhysicianAdministrator admin2 = new PhysicianAdministrator("Admin", "Two", 42, "Female", "101 Maple St");
        admin2.setAdminSpecialtyType("Immunology");
        assertFalse(hospital.addAdministrator(admin2), "Administrator should not be added due to duplicate specialty");
    }

    @Test
    public void testHirePhysicianUnderLimit() {
        Physician physician = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        physician.setSpecialty("Dermatology");
        PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin.setAdminSpecialtyType("Dermatology");
        hospital.addAdministrator(admin);
        assertTrue(hospital.hirePhysician(physician), "Physician should be successfully hired");
    }

    @Test
    public void testHirePhysicianOverLimit() {
        for (int i = 0; i < 70; i++) {
            Physician physician = new Physician("Phys", String.valueOf(i), 35, "Male", "Address" + i);
            physician.setSpecialty("Neurology");
            hospital.hirePhysician(physician);
        }
        Physician newPhysician = new Physician("New", "Phys", 40, "Female", "303 Cedar St");
        newPhysician.setSpecialty("Neurology");
        assertFalse(hospital.hirePhysician(newPhysician), "Should not hire new physician as limit is reached");
    }

    @Test
    public void testAdmitPatientSuccess() {
        Patient patient = new Patient("Patient", "One", 30, "Male", "404 Birch St");
        Physician physician = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        physician.setSpecialty("Dermatology");
        hospital.hirePhysician(physician);
        try {
			assertTrue(hospital.admitPatient(patient), "Patient should be successfully admitted");
		} catch (NoSpaceException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testAdmitPatientFailureDueToCapacity() {
        for (int i = 0; i < 500; i++) {
            Patient patient = new Patient("Patient", String.valueOf(i), 25, "Female", "Patient Address " + i);
            try {
                hospital.admitPatient(patient);
            } catch (NoSpaceException e) {
                // Expected exception for testing purposes
            }
        }
        Patient newPatient = new Patient("New", "Patient", 40, "Female", "505 Pine St");
        Exception exception = assertThrows(NoSpaceException.class, () -> hospital.admitPatient(newPatient), "Should throw NoSpaceException due to hospital capacity");
        assertNotNull(exception);
    }

    @Test
    public void testDischargePatientSuccess() throws NoSpaceException {
    	  PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
          admin.setAdminSpecialtyType("Immunology");
          hospital.addAdministrator(admin);
          Physician physician = new Physician("Phys", "Only", 45, "Non-Binary", "404 Birch St");
          physician.setSpecialty("Immunology");
          hospital.hirePhysician(physician);
        Patient patient = new Patient("Patient", "One", 30, "Male", "404 Birch St");
        hospital.admitPatient(patient);
        assertTrue(hospital.dischargePatient(patient), "Patient should be successfully discharged");
    }

    @Test
    public void testDischargePatientFailure() {
        Patient patient = new Patient("NonExistent", "Patient", 40, "Female", "Nonexistent Address");
        assertFalse(hospital.dischargePatient(patient), "Non-existent patient should not be discharged");
    }

    @Test
    public void testResignPhysicianSuccess() throws NoSpecialtyException {
        Physician physician1 = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        physician1.setSpecialty("Dermatology");
        hospital.hirePhysician(physician1);
        Physician physician2 = new Physician("Phys", "Two", 36, "Female", "303 Cedar St");
        physician2.setSpecialty("Dermatology");
        hospital.hirePhysician(physician2);
        
        hospital.resignPhysician(physician1);
        assertFalse(hospital.extractAllPhysicianDetails().contains(physician1), "Physician should be successfully resigned");
    }

    @Test
    public void testResignPhysicianFailureDueToNoOtherPhysicianWithSameSpecialty() {
        PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        Physician physician = new Physician("Phys", "Only", 45, "Non-Binary", "404 Birch St");
        physician.setSpecialty("Immunology");
        hospital.hirePhysician(physician);
        Exception exception = assertThrows(NoSpecialtyException.class, () -> hospital.resignPhysician(physician), "Should throw NoSpecialtyException due to no other"
        		+ " physician with same specialty");
        assertNotNull(exception);
    }

}

