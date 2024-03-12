package Hospital.src.Hospital;
import java.util.ArrayList;

class Administrator extends SalariedEmployee{
	protected ArrayList<PhysicianAdministrator> physicianAdministrator=new ArrayList<PhysicianAdministrator>(3);
	/**
	 * this default constructor makes an administrator
	 */
	public Administrator() {
		super();
	}
	/**
	* this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the Administrator
	 * @param firstName is a String representing Administrator's firstName
	 * @param lastName is a String representing Administrator's lastName
	 * @param age is an int representing the Administrator's age
	 * @param gender is a String representing Administrator's gender
	 * @param address is a String representing Administrator's address
	 */
	public Administrator(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
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
		Administrator other=(Administrator) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)&&this.salary==other.salary);
	}
	
	public String getAllPatientData() {
		DatabaseOps databaseOps = new DatabaseOps();
		return databaseOps.getAllPatients();
	}
	
}
