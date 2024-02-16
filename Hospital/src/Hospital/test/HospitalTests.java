package hospital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//author: Mehregan Mesgari

public class HospitalTest {
    private Hospital hospital;
    private Director director;
    
    /*
     * this is the bforeEach method that sets up the hospital with a director names john doe and assigns them as the director of the hospital
     */

    @BeforeEach
    public void setUp() {
        director = new Director("John", "Doe", 50, "Male", "123 Main St");
        hospital = new Hospital(director);

        
    }
    
    /*
     * in this test case, firstly the getHispitalDirector methos is tested to ensure the director is the same as the one in setUp method 
     * after that a new hospital director is assignment and the method is tested again to ensure the chanfe made went through and the method works 
     * correctly
     */

    @Test
    public void testHospDirector() {
        assertEquals(director, hospital.getHospDirector(), "Director should match the one set in setUp method");
        
        Director newDirector = new Director("Jane", "Smith", 45, "Female", "456 Elm St");
        hospital.setHospDirector(newDirector);
        assertEquals(newDirector, hospital.getHospDirector(), "Director should be updated to the new director");
    }

    /*
     * testAddAdministratorSuccess is a test that adds a new admin wiht Immunology speciality to the hospital and tests the addAdmin method to 
     * ensure method working correctly
     */
    
    @Test
    public void testAddAdministratorSuccess() {
        PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        assertTrue(hospital.addAdministrator(admin), "Administrator should be successfully added");
    }
    
   /*
    * testAddAdministratorFailureDueToDuplicateSpecialty tests the addAdministrator method to ensure that the hospital cna not have two 
    * administrator with the same speciality. it adds an admit first then try to add another one with the same speciality but fails
    */
    
    @Test
    public void testAddAdministratorFailureDueToDuplicateSpecialty() {
        PhysicianAdministrator admin1 = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin1.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin1);

        PhysicianAdministrator admin2 = new PhysicianAdministrator("Admin", "Two", 42, "Female", "101 Maple St");
        admin2.setAdminSpecialtyType("Immunology");
        assertFalse(hospital.addAdministrator(admin2), "Administrator should not be added due to duplicate specialty");
    }
    
    /*
     * testHirePhysicianUnderLimit tests the add physician method. for this method it checks if the doctor as added succesfully while 
     * the number of doctors are under the limit 
     */

    @Test
    public void testHirePhysicianUnderLimit() {
        Physician physician = new Physician("Phys", "One", 35, "Male", "202 Oak St");
        physician.setSpecialty("Dermatology");
        PhysicianAdministrator admin = new PhysicianAdministrator("Admin", "One", 40, "Non-Binary", "789 Pine St");
        admin.setAdminSpecialtyType("Dermatology");
        hospital.addAdministrator(admin);
        assertTrue(hospital.hirePhysician(physician), "Physician should be successfully hired");
    }

    /*
     * testHirePhysicianUnderLimit  tests the add physician method. for this method it checks if adding a doctor that goes over the limit of
     * the number of doctors who can work on the hospital fails
     */

    
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
  /*
   * testAdmitPatientSuccess tests admiting patient method and checks if a patient can be succesfully added if a  doctor already exist in the system
   */
    
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

    /*
     * testAdmitPatientFailDuetoDoctor tests admiting patient method and checks if a patient will faill a doctor is not in the hospital 
     */
      
      @Test
      public void testAdmitPatientFailDuetoDoctor() {
          Patient patient = new Patient("Patient", "One", 30, "Male", "404 Birch St");
          try {
  			assertFalse(hospital.admitPatient(patient), "Patient should not be admitted admitted");
  		} catch (NoSpaceException e) {
  			e.printStackTrace();
  		}
      }
      
      /*
       * testAdmitPatientFailureDueToCapacity tests the admitPatient method to ensure that no patient can be added over the limit of 500
       */
      
    @Test
    public void testAdmitPatientFailureDueToCapacity() {
        for (int i = 0; i < 500; i++) {
            Patient patient = new Patient("Patient", String.valueOf(i), 25, "Female", "Patient Address " + i);
            try {
                hospital.admitPatient(patient);
            } catch (NoSpaceException e) {
            }
        }
        Patient newPatient = new Patient("New", "Patient", 40, "Female", "505 Pine St");
        Exception exception = assertThrows(NoSpaceException.class, () -> hospital.admitPatient(newPatient), "Should throw NoSpaceException due to hospital capacity");
        assertNotNull(exception);
    }
    
    /*
     * this test 
     */

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

