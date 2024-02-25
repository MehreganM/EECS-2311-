package Hospital;

/**
 * this class is Physician which extends SalariedEmployee and implements Comparable
 * and stores the physician's specialty and an arrayList of their patients
 * and the physician administrator that has their specialty and also a list of volunteers
 * @author mehregan mesgari
 *
 */

class Patient extends Person implements Comparable<Patient>{
	private static int patientId=999;
	private Physician physician;
	private int patient;
	  private boolean consentFormSigned = false;
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
	
	@Override
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
	 /**
     * This method sets the patient's consent form status 
     * @param consentFormSigned a boolean representing whether the patient has signed the form
     */
    public void setConsentFormSigned(boolean consentFormSigned) {
        this.consentFormSigned = consentFormSigned;
    }

    /**
     * This method returns the consent form status of the patient
     * @return true if the consent form is signed, false otherwise
     */
    public boolean isConsentFormSigned() {
        return consentFormSigned;
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
}
