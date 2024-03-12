package Hospital.src.Hospital.test;

import Hospital.*;
import Hospital.DBSetup;
import Hospital.DatabaseOps;
import Hospital.FamilyDoctor;
import Hospital.Laboratory;
import Hospital.Nurse;
import Hospital.Patient;
import Hospital.Physician;

public class DatabaseIntegrationTest {
	
	public static void main(String[] args) {
		
		DBSetup.ensureTableExists();
		
		DatabaseOps dbo = new DatabaseOps();
		Laboratory lab = null;
		
		PhysicianAdministrator admin = new PhysicianAdministrator("Mike", "Hawk", 62, "Male", "420 Rich Blvd");
		
		Patient patient1 = new Patient("John", "Doe", 27, "Male", "12 Fake st");
		Patient patient2 = new Patient("Jane", "Doe", 24, "Female", "12 Fake st");
		Physician md = new Physician("Linda", "Honey", 49, "Female", "29 Wrong place");
		Nurse nur = new Nurse("Jack", "Mehoff", 32, "Male", "14 nowhere ave");
		FamilyDoctor famd = new FamilyDoctor("Jannine", "Family",lab);
		
		
		 
		patient1.setPhysician(md);
		patient2.setPhysician(md);
		patient1.setNurse(nur);
		patient2.setNurse(nur);
		patient1.setFamilyDoctor(famd);
		patient2.setFamilyDoctor(famd);
		
		
		dbo.addPatient(patient1);
		dbo.addPatient(patient2);
		System.out.println(admin.getAllPatientData());
		
		 
		
		dbo.deletePatient(1000);
		dbo.deletePatient(1001);
				
		
	}

}
