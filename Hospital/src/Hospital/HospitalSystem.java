package Hospital;

*/
/**
 * this is a class that implements a virtual hospital scenario with 1 director
 * 3 administrators and up to a maximum of 25
 * physicians for each physician Administrator with a total of 70 with a maximum of . this hospital also accepts patients
 * to a maximum of 8 per physician and 500 in total. 
 * the assignment of physicians and physician administrator is based on specialty and the assignment of patients 
 * and volunteers is first come first serve.
 * hospital has a director an arraylist for physicians an arraylist for
 * physician administrator and an arraylist for patients and an arraylist for volunteers
 * @author mehregan Mesgari
 *
 */

public 



public class Hospital {
	private Director director;
	private ArrayList<Physician> physicianList=new ArrayList<Physician>();
	private ArrayList<PhysicianAdministrator> adminList=new ArrayList<PhysicianAdministrator>();
	private ArrayList<Patient> patientList=new ArrayList<Patient>();
	private ArrayList<Volunteer> volunteerList=new ArrayList<Volunteer>();
	/**
	 * this overloaded constructor that makes a hospital and assigns the director that it receives as an input
	 * @pre only one director is accepted for the hospital.director is not null
	 * @param director is an object of type Director and is the director of the hospital
	 */
	public Hospital(Director director) {
		this.director=director;
		
	}	
	/**
	 * this method returns an object of type director which is the director of the hodpital
	 * @pre the hospital already has a director and director is not null
	 * @return the director of the hospital
	 */

	public Director getHospDirector() {
		return director;
	}
	/**
	 * this method sets the hospital director to a new director 
	 * @pre director is not null
	 * @param director is an object of type Director which we want to set as the hospital director
	 */
	public void setHospDirector(Director director) {
		this.director=director;
	}
	/**
	 * this method receives a Physician administrator and calls the director to
	 * assign the administrator and returns if the administrator was successfully
	 * assigned or not and also adds the administrator to the administrator list 
	 * of the hospital if the assignment was successful
	 * @pre each specialty can only have one administrator,admin is not null
	 * @param admin is an object of type PhysicianAdministrator and is the administrator that we want to add to the hospital
	 * @return true if the administrator could be added and false otherwise
	 */

	public boolean addAdministrator(PhysicianAdministrator admin) {
		//we call a method which returns true if it was able to add the administrator
		//without any problem and false otherwise and then we add the administrator
		//to the hospital list
		boolean flag=director.assignAdministrator(admin);
		if(flag) {
			adminList.add(admin);
		}
		return flag;
	}
	/**
	 * this method receives a physician and checks if the number of physicians in the hospital
	 * are less than 70 and if the physician administrator of this physician's
	 * specialty has less than 25 physicians and if these conditions apply and the
	 * physician doesn't already exist in the hospital it returns true and adds
	 * the physician to the hospital physician list and to the physician administrator
	 * of its specialty otherwise returns false
	 * @pre physician is not null
	 * @param physician is an object of type Physician and is the physician that we want to hire
	 * @return true if we were able to add the physician to the list based on the conditions
	 * stated and false otherwise
	 */

	public boolean hirePhysician(Physician physician) {
		//we make sure we don't already have 70 physicians 
		
		if(physicianList.size()<70) {
			//we make sure the physician does not already exist
			for(int i =0; i<physicianList.size();i++) {
				if (physician.equals(physicianList.get(i))) {
					return false;
				}
			}
			boolean flag=false;
			//we try to add the physician to each administrator using a method
			//which returns true if it was able to add the physician and false
			//otherwise.(if physicians were more than 25 or if specialty didn't match)
			for(int i = 0; i<adminList.size();i++) {
				flag=adminList.get(i).addPhysician(physician);
				if(flag) {
					break;
				}
			}
			//if we were able to add we add it to the hospital physician list
			if(flag) {
				physicianList.add(physician);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
		
	}
	/**
	 * this method returns a sorted list of the physicians based on their full name
	 * 
	 * @return a sorted list of physicians based on their full name
	 */
	

	public List<Physician> extractAllPhysicianDetails() {
		//we make a copy list to sort
		//we have already defined compareTo
		
		List<Physician> physicianSort = new ArrayList<Physician>();
		physicianSort.addAll(physicianList);
		Collections.sort(physicianSort);
		return physicianSort;
	}
	/**
	 * this method returns a sorted list of the patients based on their full name
	 * @return a sorted list of patients based on their full name
	 */

	public List<Patient> extractAllPatientDetails() {
		//we make a copy list to sort
		//we have already defined compareTo
		List<Patient> patientSort = new ArrayList<>();
		patientSort.addAll(patientList);
		Collections.sort(patientSort);
		return patientSort;
		
	}
	/**
	 * this method receives a patient and assigns patient to a physician if the total
	 * number of patients is less than 500 and there is a physician with less than
	 * 8 patients and if the patient doesn't already exist in hospital
	 * @pre patient is not null
	 * @param patient is an object of type Patient and is the patient we want to admit 
	 * @return true if we could admit the patient and false otherwise
	 * @throws NoSpaceException if the hospital has more than 500 patients
	 * or if there is no physician with less than 8 patients
	 */

	public boolean admitPatient(Patient patient) throws NoSpaceException {
		//we check to see if the hospital is not full first
		if(patientList.size()<500) {
			//we check to see if the patient already exists or not
			for(int i =0; i<patientList.size();i++) {
				if (patient.equals(patientList.get(i))) {
					return false;
				}
			}
			//we try to add the patient to physician's patient list if they have
			//less than 8 patients which returns true if added and false otherwise
			boolean flag= false;
			for(int i = 0; i<physicianList.size();i++) {
				if(physicianList.get(i).patients.size()<8) {
					flag=physicianList.get(i).addPatient(patient);
					
					break;
				}
			//	adminList.get(i).addPhysician(physician);
			}
			//we add the patient to hospital's patient list if we were able to add 
			//it to a physician's list
			if(flag) {
				patientList.add(patient);
				return true;
			}
			else {
				throw new NoSpaceException();
			}
			
		}
		else {
			throw new NoSpaceException();
		}
	}
	/**
	 * this method receives a physician that we want to resign but we can only resign it
	 * if there is another physician with the same specialty.this method also 
	 * reassigns all the patients and volunteers to the first available physician
	 * @pre physician is not null and exists in hospital
	 * @param physician is an object of type Physician and is the physician that wants to resign
	 * @throws NoSpecialtyException if there is not any other physician with the same
	 * specialty
	 */

	public void resignPhysician(Physician physician) throws NoSpecialtyException {
		if(physicianList.contains(physician)!=false) {
			//we store the physicians patients and volunteers before deleting them
			ArrayList<Patient> tempArray= physician.patients;
			PhysicianAdministrator admin= physician.admin;
			ArrayList<Volunteer> tempVol= physician.volunteers;
			//we have a method which checks if we can delete the physician and
			//either throws an exception or returns true if it could
			boolean flag= admin.deletePhysician(physician);
			//if we could delete the physician we reassign the patients and volunteers
			//using methods that use a for loop to loop through other physicians
			if(flag) {
				physicianList.remove(physician);
				for(int i=0;i<tempArray.size();i++) {
					physicianGone(tempArray.get(i));
				}
				for(int i=0;i<tempVol.size();i++) {
					physicianGone(tempVol.get(i));
				}
			}
			physician=null;
		}
		else {
			
		}
		
	}
	/**
	 * this method receives a volunteer and adds this volunteer to the first
	 * available physician with volunteers less than five
	 * @pre volunteer is not null
	 * @param volunteer is an object of type Volunteer and is the volunteer we want to reassign
	 */
	public void physicianGone(Volunteer volunteer) {
		//we check wich physician has less than 5 volunteers to add this volunteer to
		for(int i = 0; i<physicianList.size();i++) {
			if(physicianList.get(i).volunteers.size()<5) {
				physicianList.get(i).addVolunteer(volunteer);
				
				break;
			}
		
		}
	}
	/**
	 * this method receives a patient and adds this patient to the first
	 * available physician with less than 8 patients
	 * @pre patient is not null
	 * @param patient is an object of type Patient and is the patient we want to reassign
	 */
	public void physicianGone(Patient patient) {
		//we check which physician has less than 8 patients to add this patient to
		for(int i = 0; i<physicianList.size();i++) {
			if(physicianList.get(i).patients.size()<8) {
				physicianList.get(i).addPatient(patient);
				
				break;
			}
		
		}
	}
	/**
	 * this method receives a patient we want to discharge and deletes their records from the
	 * hospital records and patient records.returns true if it was able to discharge
	 * and false otherwise
	 * @pre patient is not null and patient is in the hospital
	 * @param patient is an object of type Patient and is the patient we want to discharge
	 * @return true if it was able to discharge and false otherwise
	 */

	public boolean dischargePatient(Patient patient) {
		if(patientList.contains(patient)) {
			//we remove the patient from hospital and physicians'patient list
			patient.getPhysician().patients.remove(patient);
			boolean flag= patientList.remove(patient);
			patient=null;
			return flag;
		}
		else {
			return false;
		}
		
	}
	/**
	 * this method receives a volunteer and checks if the number of volunteers
	 * is less than 150 and if there is any physician with less than 5 volunteers
	 * and also checks if the volunteer is not a duplicate if these conditions pass
	 * it adds the volunteer to the chosen physician and the volunteer list of the hospital
	 * and returns true if it was possible and false otherwise 
	 * @pre volunteer is not null
	 * @param volunteer is an object of type Volunteer and is the volunteer we want to hire
	 * @return returns true if it passed the conditions and we were able to add it
	 * and false otherwise
	 */

	public boolean hireVolunteer(Volunteer volunteer) {
		//we check if we are not past the max number of volunteers
		if(volunteerList.size()<150) {
			//we check if the volunteer already exists
			for(int i =0; i<volunteerList.size();i++) {
				if (volunteer.equals(volunteerList.get(i))) {
					return false;
				}
			}
			boolean flag= false;
			//we add the volunteer to whichever physician that has less than 5
			for(int i = 0; i<physicianList.size();i++) {
				if(physicianList.get(i).volunteers.size()<5) {
					flag=physicianList.get(i).addVolunteer(volunteer);
					
					break;
				}
			//	adminList.get(i).addPhysician(physician);
			}
			//we add the volunteer to hospital's volunteer list
			if(flag) {
				volunteerList.add(volunteer);
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
		
	}
	/**
	 *  this method deletes a received volunteer record from their assigned physician 
	 *  and from the hospital but throws an exception if the physician has only one volunteer
	 * @pre volunteer is not null,volunteer exists in hospital
	 * @param volunteer is an object of type Volunteer and is the volunteer we want to resign
	 * @throws NoVolunteersException if the assigned physician does not have any other
	 * volunteer
	 */

	public void resignVolunteer(Volunteer volunteer) throws NoVolunteersException {
		if(volunteerList.contains(volunteer)) {
			//we remove the volunteer from hospital and physician if physician has any other
			//volunteers
			if(volunteer.getPhysician().volunteers.size()>1) {
				volunteerList.remove(volunteer);
				volunteer.getPhysician().volunteers.remove(volunteer);
				volunteer=null;
				
			}
			else {
				throw new NoVolunteersException();
			}
		}
		
		
		
	}
	/**
	 * this method returns a list of all volunteers
	 * @return a list of all volunteers
	 */

	public List<Volunteer> extractAllVolunteerDetails() {
		ArrayList<Volunteer> temp = new ArrayList<Volunteer>();
		temp.addAll(volunteerList);
		return temp;
	}
	
}
/**
 * this class defines a person with firstName lastName age gender and address
 * and has methods that works on these attributes
 * @author Kimia Rajaeifar
 *
 */
abstract class Person{
	protected String firstName;
	protected String lastName;
	protected int age;
	protected String gender;
	protected String address;
	/**
	 * this default constructor initializes the instances of the person
	 */
	public Person() {
		firstName="";
		lastName="";
		gender="";
		address="";
	}
	/**
	 * this overloaded constructor receives first name last name age gender and address
	 * and assigns these value to the attributes
	 * @pre none of the inputs are null
	 * @param firstName is a String that is the person's first name
	 * @param lastName is a String that is the person's last name
	 * @param age is an int that is the person's age
	 * @param gender is a String hat is the person's gender
	 * @param address is a String that is the person's address
	 */
	public Person(String firstName,String lastName,int age, String gender,String address) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.gender=gender;
		this.address=address;
	}
	/**
	 * this is a method that returns the fullname that is firstName, lastName
	 * @return a String that is the full name
	 */
	public String getName() {
		String name= firstName+", "+lastName;
		return name;
		
	}
	/**
	 * this method sets the person's firstName
	 * @pre input is not empty
	 * @param firstName is a String the person's firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	/**
	 * this method sets the person's lastName
	 * @pre input is not empty
	 * @param lastName is a String the person's lastName
	 */
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	/**
	 * this method returns the person's age
	 * @pre input is not empty
	 * @return an int which is the person's age
	 */
	public int getAge() {
		return this.age;
	}
	/**
	 * this method sets the age attribute for the person
	 * @pre input is not empty
	 * @param age an int representing the person's age
	 */
	public void setAge(int age) {
		this.age=age;
	}
	/**
	 * this method returns the person's gender
	 * 
	 * @return a String representing the person's gender
	 */
	public String getGender() {
		return this.gender;
	}
	/**
	 * this method sets the person's gender
	 * @pre input is not empty
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender=gender;
	}
	/**
	 * this method gets the person's address
	 * @return a String representation of the person's address
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * this method sets the person's address and receives the address as an input
	 * @pre input is not empty
	 * @param address a String representation of the person's address
	 */
	public void setAddress(String address) {
		this.address=address;
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
		SalariedEmployee other=(SalariedEmployee) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address));
	}
	
	
}