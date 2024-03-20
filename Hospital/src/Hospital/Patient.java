package Hospital.src.Hospital;
import java.util.ArrayList;

/**
 * this class is Physician which extends SalariedEmployee and implements Comparable
 * and stores the physician's specialty and an arrayList of their patients
 * and the physician administrator that has their specialty and also a list of volunteers
 * @author mehregan mesgari
 *
 */

public class Patient extends Person implements Comparable<Patient>{
	private static int patientId=999;
	private Physician physician;
	private Nurse nurse;
	private int patient;
	private FamilyDoctor famdr; 
	ArrayList<String> medications = new ArrayList<String>();
	public String labs; 
	private VitalSigns vitalSigns;

	/**
	 * this default constructor makes  patient and gives them their patientId;
	 */
	public Patient() {
		super();
		patientId++;
		patient=patientId;
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the Patient
	 * @param firstName is a String representing Patient's firstName
	 * @param lastName is a String representing Patient's lastName
	 * @param age is an int representing the Patient's age
	 * @param gender is a String representing Patient's gender
	 * @param address is a String representing Patient's address
	 */
	public Patient(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
		patientId++;
		patient=patientId;
	}
	/**
	 * this method returns the patientID of the patient
	 * @return an integer representig the patient's patientID
	 */
	public int getPatientID() {
		return patient;
	}
	/**
	 * this method sets the patient's physician to the physician it receives
	 * @pre physician isn't null
	 * @param physician is an object of type Physician which we want to assign as 
	 * the patient's physician
	 */
	public void setPhysician(Physician physician) {
		this.physician=physician;
	}
	/**
	 * this method returns the physician the patient is assigned to
	 * @return an object of type Physician
	 */
	public Physician getPhysician() {
		return this.physician;
	}
	@Override
	/**
	 * this methods compares two patients and returns an integer representing the differene between 
	 * their full name and returns zero if they are the same patients
	 * @return an int that is zero if they are equal and compares their full name
	 */
	public int compareTo(Patient other) {
		
		if(other!=null) {
			if(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)) {
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
	@Override
	/** this method returns true if the objects are equal and false otherwise
	 * @return true if the objects are equal and false otherwise
	 */
	public boolean equals(Object object) {
		if(this==object) {
			return true;
		}
		if(object==null) {
			return false;
		}
		if(getClass()!=object.getClass()) {
			return false;
		}
		Patient other=(Patient) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address));
	}
	
	//@Override
	/** this method returns the string representation of the patient
	 * @return a String representation of the patient
	 */
	public String toString() {
		String answer = "Patient ["+this.getPatientID()+", ["+this.firstName+", "+this.lastName+", "+this.age+", "+this.gender+", "+this.address+"]]";
		return answer;
	}
	/**
	 * this method returns the assigned physician of a patient
	 * @return an object of type Physician who is the patient's physician
	 */
	public Physician getAssignedPhysician() {
		
		return this.physician;
	}
	/**
	 * this method receives a physician and assigns that physician to this patient
	 * if the physician has less than 8 patients
	 * @pre physician is not null
	 * @param physician is an object of type Physician which we want to assign the patient to
	 */
	public void setAssignedPhysician(Physician physician) {
		if(physician.patients.size()<8) {
			this.physician=physician;
			this.physician.patients.add(this);
		}
		
	}
	
	public void setAssignedNurse(Nurse nurse) {
		if(nurse.patients.size()<20) {
			this.nurse=nurse;
			this.nurse.patients.add(this);
		}
		
	}
	/**
	 * this method deletes the patients info if the patient isn't assigned a physician
	 * and returns true if it isn't assigned a physician and false otherwise
	 * @return true if it isn't assigned a physician and false otherwise
	 */
	public boolean clearPatientRecord() {
		if (this.physician!=null) {
			return false;
		}
		else {
			//we just empty everything
			this.address="";
			this.firstName="";
			this.lastName="";
			this.age=0;
			this.gender="";
			return true;
		}
	}
	public String getLName() {
		return this.lastName;
	}
	public Nurse getNurse() {
		return this.nurse;
	}
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public FamilyDoctor getFamDoc() {
		return this.famdr;
	}
	public void setFamDoc (FamilyDoctor fam) {
		this.famdr = fam; 
	}
	public ArrayList<String> returnMedication(){
		return this.medications;
	}
	 public String medicationsToString() {
	        StringBuilder stringBuilder = new StringBuilder();
	        
	        stringBuilder.append("Medications:\n");
	        for (String medication : this.medications) {
	            stringBuilder.append("- ").append(medication).append("\n");
	        }
	        
	        return stringBuilder.toString();
	    }
	 
	 public void addMedication(String medication) {
	        this.medications.add(medication);
	    }

		/**
	 * This method is to get the Vital Signs of the patient
	 * @return the vital sign of the patient
	 * @author Amira Mohamed
	 */
	public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

	/**
	 * This method is to set the Vital Signs of the patient
	 * @param It takes vitalSigns as an input and set it to vitalSigns of the patient
	 * @author Amira Mohamed
	 */
    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }

	
}
