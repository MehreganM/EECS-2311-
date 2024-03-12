package Hospital.src.Hospital;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class StubDB {
	
	    // Map to hold patient ID and their medications
	    private Map<Integer, List<String>> patientMedications;
	    private List<FamilyDoctor> familyDoctors;
	    private Laboratory laboratory;

	    public StubDB() {
	        this.patientMedications = new HashMap<>();
	        initializeStubData();
	        this.familyDoctors = new ArrayList<>(); 
	        this.laboratory = new Laboratory();
	        initializeFamilyDoctors();
	    }
	    private void initializeFamilyDoctors() {
	        familyDoctors.add(new FamilyDoctor("Anna", "Lee", laboratory, "Anna80.gmail.com", "555-1234"));
	        familyDoctors.add(new FamilyDoctor("Mark", "Lopez",laboratory, "marklo@gmail.com", "555-5678"));
	        
	    }

	    public List<FamilyDoctor> getAllFamilyDoctors() {
	        return new ArrayList<>(familyDoctors); 
	    }
	    
	    // Method to initialize the database with some stub data
	    private void initializeStubData() {
	        // Example patient IDs
	    	Patient patient = new Patient("John", "Doe", 23, "Male", "12 Bobby Ave.");
	        
	        int patientId2 = 1002; // Another example patient ID

	        addMedicationsForPatient(patient.getPatientID(), List.of("Medicine A", "Medicine B"));
	        addMedicationsForPatient(patientId2, List.of("Medicine C", "Medicine D", "Medicine E"));
	        // Continue for additional patients as needed
	    }


	    // Method to add medications for a specific patient
	    public void addMedicationsForPatient(int patientId, List<String> medications) {
	        patientMedications.put(patientId, medications);
	    }

	    // Method to retrieve medications for a specific patient
	    public List<String> getMedicationsForPatient(Patient patient) {
	        return patientMedications.getOrDefault(patient.getPatientID(), new ArrayList<>());
	    }

	    // Method to simulate the getMeds method that might exist in Nurse class
	    public String getMeds(Patient patient) {
	        List<String> meds = getMedicationsForPatient(patient);
	        return String.join(", ", meds);
	    }
	}
