package Hospital;

public class mains {

    public static void main(String[] args) {
        Laboratory laboratory = new Laboratory();
       Hospital hospital = new Hospital(null);
       Physician physician = new Physician();
       Patient patient = new Patient();
       Nurse nurse = new Nurse(null, null, 0, null, null);
       hospital.hirePhysician(physician);
       
       labTest test = new labTest(patient, "xray");
       laboratory.updateTestResult(test, "a broken leg");
    }
}
