package Hospital;

public class PhysicianAdministrator {
	/**
	 * this class is Physician which extends SalariedEmployee and implements Comparable
	 * and stores the physician's specialty and an arrayList of their patients
	 * and the physician administrator that has their specialty and also a list of volunteers
	 *
	 */
	class Physician extends SalariedEmployee implements Comparable<Physician>{
		private String specialty="";
		protected ArrayList<Patient> patients= new ArrayList<Patient>();
		protected PhysicianAdministrator admin;
		protected ArrayList<Volunteer> volunteers= new ArrayList<Volunteer>();
		/**
		 * this default constructor makes a physician but doesn't initialize anythig
		 */
		public Physician() {
			super();
		}
		/**
		 * this overloaded constructor initializes the given inputs to the appropriate
		 * attributes of the Physician
		 * @param firstName is a String representing Physician's firstName
		 * @param lastName is a String representing Physician's lastName
		 * @param age is an int representing the Physician's age
		 * @param gender is a String representing Physician's gender
		 * @param address is a String representing Physician's address
		 */
		public Physician(String firstName,String lastName,int age, String gender,String address) {
			super(firstName,lastName,age,gender,address);
		}
		/**
		 * this method sets the Physician administrator for this Physician to the
		 * input it receives
		 * @pre physician and Physician administrator have the same specialty and
		 * Physician administrator is not null
		 * @param admin is the Physician administrator we want to assign the physician to
		 */
		public void setAdministrator(PhysicianAdministrator admin) {
			if(admin.getAdminSpecialtyType()==this.specialty) {
				this.admin=admin;
			}
			
		}
		/**
		 * this method sets the physician's specialty to the given specialty if it is 
		 * one of the Immunology, Dermatology, and Neurology and throws an exception
		 * if it is not within the three
		 * @param specialty is String that is going to be the physician's specialty
		 * if it is within Immunology, Dermatology, and Neurology
		 * @throws IllegalArgumentException if specialty is not Immunology, Dermatology, or Neurology
		 */
		
		public void setSpecialty(String specialty) {
			//we check to see if the specialty is acceptable
			if(specialty.equals("Immunology")||specialty.equals("Dermatology")||specialty.equals("Neurology")) {
				this.specialty=specialty;
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		@Override
		/**
		 * this methods compares two physician and returns an integer representing the differene between 
		 * their full name and returns zero if they are the same patients
		 * @return an int that is zero if they are equal and compares their full name
		 */
		public int compareTo(Physician other) {
			
			if(other!=null) {
				if(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)&&this.salary==other.salary&&this.specialty.equals(other.specialty)) {
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
		/**
		 * this method returns the string representation of the physician
		 * @return the string representation of the physician
		 */
		public String toString() {
			String answer= "Physician"+" [[["+this.getEmployeeID()+",["+this.firstName+", "+this.lastName+", "+this.age+", "+this.gender+", "+this.address+"]], "+this.salary+"]]";
			return answer;
		}
		@Override
		/**
		 * this method returns the String representation of PhysicianAdministrator
		 * @return the String representation of PhysicianAdministrator
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
			Physician other=(Physician) object;
			if(this.compareTo(other)==0) {
				return true;
			}
			return false;
		}
		/**
		 * this method return's the physician's specialty
		 * @return a String representing the physician's specialty
		 */
		public String getSpecialty() {
			return this.specialty;
		}
		/**
		 * this method receives a patient and adds the patient to the patient
		 * list if the number f patients that 
		 * the physician has is less than 8 and returns true if the patient is added and 
		 * false otherwise
		 * @pre patient is not null
		 * @param patient is an object of type Patient which we want to assign to the 
		 * physician
		 * @return true if the patient is added and false otherwise
		 */
		public boolean addPatient(Patient patient) {
			if(patients.size()<8) {
				patients.add(patient);
				patient.setPhysician(this);
				return true;
			}
			else {
				return false;
			}
		}
		/**
		 * this method receives a volunteer and adds the volunteer to the volunteer
		 * list if the number of volunteers that 
		 * the physician has is less than 5 and returns true if the volunteer is added and 
		 * false otherwise
		 * @pre volunteer is not null
		 * @param volunteer is an object of type Volunteer which we want to assign to the 
		 * physician
		 * @return true if the patient is added and false otherwise
		 */
		public boolean addVolunteer(Volunteer volunteer) {
			if(volunteers.size()<5) {
				volunteers.add(volunteer);
				volunteer.setPhysician(this);
				return true;
			}
			else {
				return false;
			}
		}
		/**
		 * this method returns a sorted list of patients that the physician has 
		 * based on their full name
		 * @return a sorted list of patients that the physician has based on their full name
		 */
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
		/**
		 * this method returns a sorted list of volunteers that the physician has 
		 * based on their full name
		 * @return a sorted list of Volunteer that the physician has based on their full name
		 */
		public List<Volunteer> extractValunterDetail() {
			ArrayList<Volunteer> temp = new ArrayList<Volunteer>();
			temp.addAll(volunteers);
			return temp;
		}
		/**
		 * this method receives a Volunteer and assigns them to this physicians volunteers
		 * if their number of volunteers is less than 5
		 * @pre employee is a volunteer and is not null
		 * @param employee is of type volunteer
		 * @return true if we could add the volunteer and false otherwise
		 */
		public boolean assignVolunteer(Employee employee) {
			
			Volunteer volunteer=(Volunteer)employee;
			if(volunteers.size()<5) {
				volunteers.add(volunteer);
				volunteer.setPhysician(this);
				return true;
			}
			else {
				return false;
			}
		}
		/**
		 * this method returns true if the physician has 5 volunteers and false if otherwise
		 * @return true if the physician has 5 volunteers and false if otherwise
		 */
		public boolean hasMaxVolunteers() {
			if(this.volunteers.size()==5) {
				return true;
			}
			else {
				return false;
			}
		}
		/**
		 * this method returns true if the physician has 8 patients and false if otherwise
		 * @return true if the physician has 8 patients and false if otherwise
		 */
		public boolean hasMaximumpatient() {
			if(this.patients.size()==8) {
				return true;
			}
			else {
				return false;
			}
		}
	}