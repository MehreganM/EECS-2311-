
package test;

//author: Mehregan Mesgari

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class labTests {
	    
	    private Labratory laboratory;
	    private Patient patient;
	    private labTest testRequest1;
	    private labTest testRequest2;
	    
	    /*
	     * this is the BeforeEach method for the labTest tester. It assigns the labratory to a new lab, assigns a sample patietine. adds 
	     * two tests for the patient and their results  
	     */

	
		@BeforeEach
	    void setUp() {
	        this.laboratory = new Labratory();
	        this.patient = new Patient("John Doe", null, 30, "Male", "123 Main St"); 
	        this.testRequest1 = new labTest(patient, "COVID-19");
	        this.testRequest2 = new labTest(patient, "Flu");
	        testRequest1.addResult("Negative");
	        testRequest2.addResult("Positive");
	    }

	    /*
	     * addTestRequestSuccessfully is a test for the labratory class that adds a test request and asserts if the test was added succesfully
	     */
	    @Test
	    void addTestRequestSuccessfully() {
	        assertTrue(laboratory.addTestRequest(testRequest1), "Test request should be added successfully.");
	    }
	    
	    /*
	     * findTestResultsForExistingRequest is a tester that finds an exisiting test and makes sure it exists 
	     */

	    @Test
	    void findTestResultsForExistingRequest() {
	        laboratory.addTestRequest(testRequest1);
	        String result = laboratory.testResults(patient, "COVID-19");
	        assertNotNull(result, "Should find test results for existing request.");
	        assertEquals("Negative", result, "Test result should be 'Negative'.");
	    }
	    
	    /*
	     * noResultsForNonExistingRequest is a tester to check if a non existing lab test resturns null
	     */

	    @Test
	    void noResultsForNonExistingRequest() {
	        assertNull(laboratory.testResults(new Patient("Jane Doe", null, 28, "Female", "124 Main St"), "Flu"), "Should not find test results for a non-existing request.");
	    }

	    /*
	     * addMultipleTestRequestsForSamePatient is a tester that checks the result of existing lab test and ensures it can be correctly 
	     * retrieved from test results 
	     */

	    @Test
	    void addMultipleTestRequestsForSamePatient() {
	        assertTrue(laboratory.addTestRequest(testRequest1), "First test request should be added successfully.");
	        assertTrue(laboratory.addTestRequest(testRequest2), "Second test request should be added successfully.");

	        String covidResult = laboratory.testResults(patient, "COVID-19");
	        String fluResult = laboratory.testResults(patient, "Flu");

	        assertEquals("Negative", covidResult, "COVID-19 test result should be 'Negative'.");
	        assertEquals("Positive", fluResult, "Flu test result should be 'Positive'.");
	    }
	    
}
