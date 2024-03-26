package Hospital;
import javax.mail.*;
import javax.mail.internet.*;

public class mains {

    public static void main(String[] args) throws NoSpaceException {
     Director director = new Director("ali", "brown",45,"male","main st");
     Hospital hospital = new Hospital (director);
     Laboratory lab = new Laboratory();
     Physician dr = new Physician ("ali", "brown",45,"male","main st");
     dr.setSpecialty("Dermatology");
     PhysicianAdministrator admin = new PhysicianAdministrator("ali", "brown",45,"male","main st") ;
     admin.setAdminSpecialtyType("Dermatology");
     admin.addPhysician(dr);
     hospital.hirePhysician(dr);
     
     hospital.InitializeEmployees();
     System.out.println(hospital.physicianList.size());
     Patient patient = new Patient();
     
     hospital.admitPatient(patient);
     patient.setAssignedPhysician(dr);
     patient.setFamDoc(new FamilyDoctor("ali", "brown",lab, "male","main st"));
     hospital.dischargePatient(patient);
    }
}
