package Hospital;

import java.util.ArrayList;
import java.util.List;


public class Laboratory {



		    private List<labTest> testRequests;

		    public Laboratory() {
		        this.testRequests = new ArrayList<>();
		    }

		    public boolean addTestRequest(labTest request) {
		        return testRequests.add(request);
		    }
		    
		    public String getAllTestsForPatientAsString(Patient patient) {
		        StringBuilder allTestsResults = new StringBuilder();
		        for (labTest request : testRequests) {
		            if (request.getPatient().equals(patient)) {
		                allTestsResults.append(request.getType())
		                               .append(": ")
		                               .append(request.getResults())
		                               .append("\n"); 
		            }
		        }
		        return allTestsResults.toString();}
	

		    public String testResults(Patient patient, String type) {
		        for (labTest request : testRequests) {
		            if (request.getPatient().equals(patient) && request.getType().equals(type)) {
		                return request.getResults();
		            }
		        }
		        return null; 
		    
		}
		    

	}

	class labTest{
		private Patient patient; 
		private String type; 
		private String results; 
		
		  public labTest(Patient patient, String type) {
		        this.patient = patient;
		        this.type = type;
		    }
		public String getResults() {
			return this.results;
		}
		public String getType() {
			return this.type;
		}
		public Patient getPatient() {
			return this.patient;
		}
		public void addResult(String result) {
			this.results = result; 
			
		}
		
	}

