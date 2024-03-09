package Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * This class is Physician which extends Employee and stores the physician's attributes
 * and their specialty. It also has an arrayList of the physician's patients and nurses 
 * and the physician administrator.
 * @author Amira Mohamed
 *
 */
public class Physician extends Employee {
	
	String specialty;
	ArrayList<Patient> patients = new ArrayList<>();
	ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	PhysicianAdministrator admin = new PhysicianAdministrator();

	
	/**
	 * This is the default constructor that creates a physician but does not initializes the attributes of the physician.
	 */
	public Physician() {
		super();
	}
	
	/**
	 * This is the overloaded constructor that initializes the attributes of the physician. These attributes are the physician's 
	 * first name, last name, age, gender, and address. 
	 * @param firstName is the first name of the physician that is stored as a string
	 * @param lastName is the last name of the physician that is stored as a string
	 * @param age is the age of the physician that is stored as a integer
	 * @param gender is the gender of the physician that is stored as a string
	 * @param address is the address of the physician that is stored as a string
	 */
	
	public Physician(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}
	
	
	
	/**
	 * This method returns the physician's specialty as a string. 
	 * @return returns the specialty of the physician. 
	 */
	public String getSpecialty() {
		return specialty;
	}
	
	
	/**
	 * This method sets the specialty of the physician. It takes in a string as a parameter and set it as the 
	 * physician's specialty if it is one of these: Radiology, Dermatology, Neurology, Cardiology, Hematology, and Immunology. 
	 * @param specialty is the input parameter as a string. 
	 * The method checks if the input is one of the above specialties. If so, we assign it as the physician's specialty, otherwise 
	 * we throws an IllegalArgumentException.
	 * @throws IllegalArgumentException if the input string is none of the specified specialties.
	 */
	
	public void setSpecialty(String specialty) throws IllegalArgumentException {
		if (specialty.equals("Radiology")||specialty.equals("Dermatology")||specialty.equals("Neurology")||
				specialty.equals("Cardiology") || specialty.equals("Hematology") || specialty.equals("Immunology")){
			this.specialty = specialty;
		}
		else {
			throw new IllegalArgumentException("We don't have this speciality");
		}		
	}

	/**
	 * This method is to add a new patient to the physician's patient list if the number of patients
	 * in the list is less than 10. The method takes in the patient as an input parameter and returns 
	 * true if the patient was successfully added to the physician's patient list. 
	 * The physician can have a maximum of 10 patients.
	 * @param patient is an object of Patient type that needs to be assign a physician
	 * @return true if the patient was successfully assigned a physician and false otherwise. 
	 */
	
	public boolean addPatient (Patient patient) {
		if (patients.size() < 10) {
			patients.add(patient);
			patient.setPhysician(this);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method returns true if two physicians are the same. They are considered the same if they 
	 * have the same first name, last name, age, gender, employee ID, address and specialty. 
	 * @param object is an input of type Object
	 * @return true if they are the same person, false otherwise
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Physician other = (Physician) object;
		if (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.age == other.age &&
		this.gender.equals(other.gender) && this.address.equals(other.address) && this.specialty.equals(other.specialty)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * This method sets the physician administrator for the physician
	 * @param admin is the the physician administrator that we want to set
	 */
	public void setAdmin(PhysicianAdministrator admin) {
		this.admin = admin;
	}
	
	/**
	 * This is the getter method to get the admin of the physician 
	 * @return it returns the admin of the physician
	 */
	
	public PhysicianAdministrator getAdmin() {
		return admin;
	}

	/**
	 * This method is to extract patient information from the system. 
	 * @return it returns the list of the patients in the hospital.
	 */
	public List<Patient> extractPatientDetail(){
		Set<Patient> patientTree = new TreeSet<>();
		for(int i = 0; i<patients.size();i++) {
			patientTree.add(patients.get(i));
		}
		//we add it to a list to return it as a list
		List<Patient> PatientInfo = new ArrayList<Patient>();
		PatientInfo.addAll(patientTree);
		return PatientInfo;
		
	}
	
	
	/**
	 * This method is to check whether the physician has the maximum number of patients or not. 
	 * It return true if the patients in the list are exactly 10 patients, and false otherwise. 
	 * @return an indication about the number of patients in the physician's list. It is true if 
	 * they are 10, and false otherwise.  
	 */
	public boolean hasMaximumPatients() {
		return (patients.size() == 10);
		
	}
	
	/**
	 * This method print the string representation of the physician's attributes.
	 * @return it returns the physisican's first name, last name, employee ID, age, gender, address, and specialty
	 */
	@Override
	public String toString() {
		return "Physician [firstName=" + firstName + ", lastName=" + lastName + ", employeeID=" + employeeID + ", age=" + age + ", gender=" + gender + ","
				+ " address=" + address + ", specialty=" + specialty + "]";
	}
	/**
	 * This is the requestLabTest method where the doctor can request a lab test for the patient and recieves a boolen if it went through 
	 * @return boolean true if it went through and false if it didnt 
	 * @author : Mehregan
	 */
	public boolean requestLabTest(Patient patient, String testType, String result) {
	    labTest newTest = new labTest(patient, testType);
	    newTest.addResult(result);
	    return Hospital.laboratory.addTestRequest(newTest);
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
	
	public void prescripe (Patient patient, String med) {
		patient.medications.add(med);
	}
	public void LabReq(Laboratory lab, labTest labtest) {
		lab.addTestRequest(labtest);
	}
	public void updateLab (Patient patient, Laboratory lab) {
		patient.labs = lab.getAllTestsForPatientAsString(patient);
	}

}
