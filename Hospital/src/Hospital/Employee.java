abstract class Employee extends Person{
	protected static int ID=99;
	protected int employeeID;
	/**
	 * this default constructor assigns the employee with an employeeID
	 */
	public Employee() {
		ID++;
		this.employeeID=ID;
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the employee
	 * @param firstName is a String representing employee's firstName
	 * @param lastName is a String representing employee's lastName
	 * @param age is an int representing the employee's age
	 * @param gender is a String representing employee's gender
	 * @param address is a String representing employee's address
	 */
	
	public Employee(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
		ID++;
		this.employeeID=ID;
	}
	/**
	 * this method returns the employeeId of the employee
	 * @return an int representing the employeeID
	 */
	public int getEmployeeID() {
		return this.employeeID;
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
		Employee other=(Employee) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address));
	}
	
}
