package Hospital;




//@author : Harrish 

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Nurse extends Employee {
	
	protected ArrayList<Patient> patients= new ArrayList<Patient>();
	private StubDB stubDB;

	public Nurse(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		
	}

	public void setPatientDB(StubDB stub) {
		this.stubDB = stub;
	}
	
	public boolean addPatient(Patient patient) {
		if(patients.size()<15) {
			patients.add(patient);
			patient.setAssignedNurse(this);
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Patient> extractPatientDetail() {
		//we make a tree set to sort the patients because it uses compareTo
		//we have already defined compareTo
		Set<Patient> patientTree = new TreeSet<>();
		for(int i = 0; i<patients.size();i++) {
		//	String temp= physicianList.get(i).firstName+ ", "+ physicianList.get(i).lastName;
			patientTree.add(patients.get(i));
		}
		//we add it to a list because we should return a list
		List<Patient> extracted = new ArrayList<Patient>();
		extracted.addAll(patientTree);
		return extracted;
	}
	
	public String currentMeds(Patient patient) {
		return stubDB.getMeds(patient);
		//this is temporary as we may save it as a concatenated string or we might
		//to pull this information from a database which will require the code to change
	}
	
	public void getMDNotes(Physician phys) {
		//this will have to be related to a database
		//we will have to retrieve the information from said database
	}
	

	/*
	 * checkLabTestResults method check the result of a lab rest based on the patient name 
	 * @return String of result 
	 * @param patient String testType
	 * @author : Mehregan
	 */
	public String checkLabTestResults(Patient patient, String testType) {
	    return Hospital.laboratory.testResults(patient, testType);
	}
	
	
	public boolean equals(Object object) { // this way we can see if this nurse is the same as another
		//or to see if the thing we are comparing the nurse to is of object type Nurse
		if(this==object) {
			return true;
		}
		if(object==null) {
			return false;
		}
		if(getClass()!=object.getClass()) {
			return false;
		}
		Nurse other=(Nurse) object;
		if(this.compareTo(other)==0) {
			return true;
		}
		return false;
	}
	
	
	public int compareTo(Nurse other) { // this is another way to compare nurses to see, likely related
		//to hiring more nurses for the hospital or making sure that we don't have duplicates
		
		if(other!=null) {
			if(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&
					this.gender.equals(other.gender)&&this.address.equals(other.address)) {
				return 0;
			}
			else {
				return this.getName().compareTo(other.getName());
			}			
		}
		else {
			return 2;
		}
	}

	public String toString() {
		return "Nurse [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender + ","
				+ " address=" + address + "]";
	}

}
