package Hospital;
import java.util.*;

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
    private Laboratory laboratory; 
    String email; 
    String telephoneNumber;

    
    public FamilyDoctor(String name, String specialty, Laboratory laboratory, String email, String telephoneNumber) {
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.specialty = specialty;
        this.laboratory = laboratory;
       
      
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

   
    @Override
    public String toString() {
        return "FamilyDoctor{" +
                "name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + telephoneNumber + '\'' + 
                '}';
    }
    
}
