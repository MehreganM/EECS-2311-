package Hospital.src.Hospital;
import java.util.List;

/**
 * this Class is Director which extends Administrator
 * author : mehregan mesgari 
 *
 */

class Director extends Administrator{
	/**
	 * this default constructor creates a director
	 */
	public Director() {
		super();
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the Director
	 * @param firstName is a String representing Director's firstName
	 * @param lastName is a String representing Director's lastName
	 * @param age is an int representing the Director's age
	 * @param gender is a String representing Director's gender
	 * @param address is a String representing Director's address
	 */
	public Director (String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
	}
	/**
	 * this method receives a physician administrator and checks to make sure
	 * the number of physician administrators is less than three and also 
	 * if there isn't any other physician administrator with the same specialty
	 * and adds the physician administrator if these conditions hold
	 * @pre admin is not null and has a specialty 
	 * @param admin is an object of PhysicianAdministrator
	 * @return true if the physician administrator was added to the physician administrator
	 * list and false otherwise
	 */

	public boolean assignAdministrator(PhysicianAdministrator admin) {
		//only assigns 3 administrators and the specialties should also vary
		//we check for these things
		if(physicianAdministrator.size()==0) {
			physicianAdministrator.add(admin);
			return true;
		}
		if(physicianAdministrator.size()==1) {
			if(!(physicianAdministrator.get(0).getAdminSpecialtyType().equals(admin.getAdminSpecialtyType()))) {
				physicianAdministrator.add(admin);
				return true;
			}
			else {
				return false;
			}
		}
		if(physicianAdministrator.size()==2) {
			if(!(physicianAdministrator.get(0).getAdminSpecialtyType().equals(admin.getAdminSpecialtyType()))&&!(physicianAdministrator.get(1).getAdminSpecialtyType().equals(admin.getAdminSpecialtyType()))) {
				physicianAdministrator.add(admin);
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
	 * this method returns a list of physician administrator
	 * @return a list physician administrator
	 */

	public List<PhysicianAdministrator> extractPhysicianAdmins() {
		
		return this.physicianAdministrator;
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
		Director other=(Director) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)&&this.salary==other.salary);
	}
}
