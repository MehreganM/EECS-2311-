package Hospital;

import java.util.ArrayList;
import java.util.Objects;

public class Physician extends Employee {
	
	String specialty;
	ArrayList<Patient> patients = new ArrayList<>();
	ArrayList<Volunteer> volunteers = new ArrayList<Volunteer>();
	ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	PhysicianAdminstrator admin;

	
	/**
	 * This is the default constructor that creates a physician but does not initialize the physician's attributes.
	 */
	public Physician() {
		super();
	}
	
	/**
	 * This is the overloaded constructor that initializes the attributes of the physician. These attributes are the physician's 
	 * first name, last name, age, gender, and address. 
	 * @param firstName is the first name of the physician that is stored as a string
	 * @param lastName is the last name of the physician that is stored as a string
	 * @param age is the age of the physician that is stored as an integer
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
	 * This method sets the specialty of the physician. It takes in a string as a parameter and sets it as the 
	 * physician's specialty if it is one of these: Radiology, Dermatology, Neurology, Cardiology, Hematology, and Immunology. 
	 * @param specialty is the input parameter as a string. 
	 * The method checks if the input is one of the above specialties. If so, we assign it as the physician's specialty, otherwise 
	 * we throw an IllegalArgumentException.
	 * @throws IllegalArgumentException if the input string is none of the specified specialties.
	 */
	
	public void setSpeciality(String specialty) throws IllegalArgumentException {
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
	 * @param patient is an object of type Patient that needs to be assigned a physician
	 * @return true if the patient was successfully assigned a physician and false otherwise. 
	 */
	
	public boolean addPatient (Patient patient) {
		if (patient.getSize() < 10) {
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
	 * This method prints the string representation of the physician's attributes.
	 * @return it returns the physician's first name, last name, employee ID, age, gender, address, and specialty
	 */
	@Override
	public String toString() {
		return "Physician [firstName=" + firstName + ", lastName=" + lastName + ", employeeID=" + employee + ", age=" + age + ", gender=" + gender + ","
				+ " address=" + address + ", specialty=" + specialty + "]";
	}
}
