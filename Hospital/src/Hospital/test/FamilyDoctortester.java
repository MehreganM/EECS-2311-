package Hospital;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Hospital.laboratory.Laboratory;


/**
 * @author Parmoun Khalkhali Sharifi
 * This tester class Represents the Family Doctor class. Testing for capability of accessing and managing patient summaries,
 * and lab results to facilitate better post-discharge care and update.
 */
public class FamilyDoctortester {
	 private FamilyDoctor familyDoctor;
	    private Laboratory laboratory;
	    private Patient sample1;
	    private Patient sample2;
 
    
    @BeforeEach
    void setUp() {
    	
    	 laboratory = new Laboratory();
         familyDoctor = new FamilyDoctor("Dr. Han", "General physician", laboratory);
         sample1 = new Patient("Emma", "Walter", 32, "Female", "15 Jain St");
         sample2 = new Patient("Curtis", "Dann", 15, "Male", "23 Oakland Blv");
    	    }
    

    @Test
    void addAndGetPatientSummary() {
        familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 1");
        assertFalse(familyDoctor.getPatientSummaries(sample1.getPatientID()).isEmpty());
        assertEquals("Summary 1", familyDoctor.getPatientSummaries(sample1.getPatientID()).get(0));
    }

    @Test
    void addMultipleSummariesForOnePatient() {
        familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 1");
        familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 2");
        assertEquals(2, familyDoctor.getPatientSummaries(sample1.getPatientID()).size());
    }

    @Test
    void getSummariesForNonexistentPatient() {
        assertTrue(familyDoctor.getPatientSummaries(999).isEmpty());
    }

    @Test
    void addPatientSummaryAndRetrieveForDifferentPatient() {
    	familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 1");
        assertTrue(familyDoctor.getPatientSummaries(sample2.getPatientID()).isEmpty());
    }

    @Test
    void getLabTestResultsForPatient() {
        labTest test = new labTest(sample1, "Blood");
        test.addResult("Normal");
        laboratory.addTestRequest(test);
        assertEquals("Normal", familyDoctor.getLabTestResults(sample1, "Blood"));
    }

    @Test
    void getLabTestResultsForNonexistentTest() {
        assertNull(familyDoctor.getLabTestResults(sample1, "X-ray"));
    }

    @Test
    void getLabTestResultsForNonexistentPatient() {
        assertNull(familyDoctor.getLabTestResults(sample2, "Blood"));
    }

  

    @Test
    void checkForDuplicationOfSummaries() {
        familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 1");
        familyDoctor.addPatientSummary(sample1.getPatientID(), "Summary 1");
        assertEquals(familyDoctor.getPatientSummaries(sample1.getPatientID()).get(0),
                     familyDoctor.getPatientSummaries(sample1.getPatientID()).get(1),
                     "Expected duplicate summaries to be equal."); }

 
}
