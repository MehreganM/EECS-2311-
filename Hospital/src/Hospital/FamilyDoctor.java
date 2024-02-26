package Hospital;
import java.util.*;
import Hospital.laboratory.Laboratory;

/**
 * @author Parmoun Khalkhali Sharifi
 * 
 * This class represents a Family Doctor in a hospital system.
 *  The family doctor can access the information, visit summary, notes and lab test results of the patient.
 * 
 */
public class FamilyDoctor {
    private String name;
    private String specialty;
    private Map<Integer, List<String>> patientSummaries;
    private Laboratory laboratory; 

    // Argument constructor
    public FamilyDoctor(String name, String DoctorSpecialty, Laboratory laboratory) {
        this.name = name;
        this.laboratory = laboratory;
        this.specialty = DoctorSpecialty;
        this.patientSummaries = new HashMap<>();
      
    }

    /**
     * Adds summary notes for a patient.
     * @param patientId The ID of the patient.
     * @param summary The visit summary of the patient.
     */
    public void addPatientSummary(int patientId, String summary) {
        if (!patientSummaries.containsKey(patientId)) {
            patientSummaries.put(patientId, new ArrayList<>());
        }
        patientSummaries.get(patientId).add(summary);
    }

    /**
     * Retrieves the list of summaries for a given patient.
     * @param patientId The ID of the patient.
     * @return A list of summaries for the patient, or an empty list if no summaries are present.
     */
    public List<String> getPatientSummaries(int patientId) {
        return patientSummaries.getOrDefault(patientId, new ArrayList<>());
    }

    /**
     * Retrieves lab test results for a specific patient and test type.
     * @param patient The Patient object.
     * @param testType The type of the lab test.
     * @return The results of the lab test, or null if no results are found.
     */
    public String getLabTestResults(Patient patient, String testType) {
        return laboratory.testResults(patient, testType);
    }

    // Getter and setter methods 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // ToString representation of output
    @Override
    public String toString() {
        return "FamilyDoctor{" +
                "name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", patientSummaries=" + patientSummaries +
                '}';
    }
    
}
