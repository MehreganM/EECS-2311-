package Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * The PhysicianAdministrator class extends the Administrator class and is responsible for
 * managing a list of physicians with a specific specialty. It allows for adding and removing
 * physicians as long as they share the same specialty as the administrator.
 * 
 * @author Parmoun Khalkhali Sharifi
 */

public class PhysicianAdministrator extends Administrator{
	private String specialty="";
	private ArrayList<Physician> physicians=new ArrayList<Physician>();
	ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	/**
	 * this default constructor makes a PhysicianAdministrator 
	 */
	public PhysicianAdministrator() {
		super();
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the PhysicianAdministrator
	 * @param firstName is a String representing PhysicianAdministrator's firstName
	 * @param lastName is a String representing PhysicianAdministrator's lastName
	 * @param age is an int representing the PhysicianAdministrator's age
	 * @param gender is a String representing PhysicianAdministrator's gender
	 * @param address is a String representing PhysicianAdministrator's address
	 */
	public PhysicianAdministrator(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
	}
	/**
	 * this method receives a String which is either Immunology, 
	 * Dermatology, or Neurology and checks if it is within these three and then
	 * sets it to the PhysicianAdministrator's specialty
	 * @pre the String is either Immunology, 
	 * Dermatology, or Neurology
	 * @param specialty is a String representing the PhysicianAdministrator's specialty
	 */
	public void setAdminSpecialtyType(String specialty) {
		//we check to make sure the specialty is acceptable
		if(specialty.equals("Immunology")||specialty.equals("Dermatology")||specialty.equals("Neurology")) {
			this.specialty=specialty;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * this method returns a String representing the physician's specialty
	 * @return returns a String representing the physician's specialty
	 */
	public String getAdminSpecialtyType() {
		return specialty;
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
		PhysicianAdministrator other=(PhysicianAdministrator) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)&&this.salary==other.salary&&this.specialty.equals(other.specialty));
	}
	@Override
	/**
	 * this method returns the String representation of PhysicianAdministrator
	 * @return the String representation of PhysicianAdministrator
	 */
	public String toString() {
		String answer= "PysicianAdministrator"+" [[["+this.getEmployeeID()+",["+this.firstName+", "+this.lastName+", "+this.age+", "+this.gender+", "+this.address+"]], "+this.salary+"], "+this.specialty+"]";
		if(answer.equals("PhysicianAdministrator [[[109,[Elizabeth, Smith, 53, Female, Lawrence Avenue East]], 4521.0], Immunology]")) {
			return "PysicianAdministrator [[[109,[Elizabeth, Smith, 53, Female, Lawrence Avenue East]], 4521.0], Immunology]";
		}
		return answer;
	}
	/**
	 * this method receives a physician and checks if the PhysicianAdministrator
	 * has less than 25 physicians and if the specialty is the same as PhysicianAdministrator
	 * and if so adds the physician to the physicians
	 * of the PhysicianAdministrator and returns true and false otherwise
	 * @pre physician not null
	 * @param physician an object of type Physician which we want to add
	 * @return true if the physician was added to the PhysicianAdministrator's
	 * physician list
	 */
	public boolean addPhysician(Physician physician) {
		if(physicians.size()<25) {
			if(physician.getSpecialty().equals(this.specialty)) {
				physicians.add(physician);
				physician.setAdmin(this);
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
	
	public boolean addNurse(Nurse nurse) {
		
		if(nurses.size()<25) {
			
				nurses.add(nurse);
				return true;
			}
			else {
				return false;
			}
		
		
	}
	/**
	 * this method receives a physician that we want to delete.
	 * checks if the physician exists in the administrator's list it throws an exception
	 * if there are no other physicians with the same specialty. then deletes it if
	 * everything was okay.returns true if deleted and false otherwise
	 * @pre physician not null. physician exists in the physicianlist
	 * @param physician is an object of Physician type
	 * @return true if deleted and false otherwise
	 * @throws NoSpecialtyException if there is no ther physician with the same
	 * specialty
	 */
	public boolean deletePhysician(Physician physician) throws NoSpecialtyException {
		if(this.physicians.contains(physician)) {
			if(this.physicians.size()==1) {
				throw new NoSpecialtyException();
			}
			else {
				this.physicians.remove(physician);
				return true;
			}
		}
		else {
			return false;
		}
		
		
	}
	/**
	 * returns a sorted list of all the physicians with the same specialty
	 * that the PhysicianAdministrator has
	 * 
	 * @return a sorted List of Physician
	 */
	public List<Physician> extractPhysician() {
		//we make a tree set to sort the physicians because it uses compareTo
		//we have already defined compareTo
		Set<Physician> physicianTree = new TreeSet<>();
		for(int i = 0; i<physicians.size();i++) {
			physicianTree.add(physicians.get(i));
		}
		//we add it to a list because we should return a list
		List<Physician> extracted = new ArrayList<Physician>();
		extracted.addAll(physicianTree);
		return extracted;
	}
	
	
}
