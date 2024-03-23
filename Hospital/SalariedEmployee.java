package Hospital;

/**
 * this Class is called Salaried employee which extends Employee but also
 * has an attribute called salary
 */
abstract class SalariedEmployee extends Employee{
	protected double salary;
	/**
	 * this default constructor makes a salaried employee
	 */
	public SalariedEmployee() {
		super();
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the SalariedEmployee
	 * @param firstName is a String representing SalariedEmployee's firstName
	 * @param lastName is a String representing SalariedEmployee's lastName
	 * @param age is an int representing the SalariedEmployee's age
	 * @param gender is a String representing SalariedEmployee's gender
	 * @param address is a String representing SalariedEmployee's address
	 */
	public SalariedEmployee(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
		this.salary=0.0;
	}
	/**
	 * this method receives a double representing the SalariedEmployee's
	 * salary and assigns it to their salary
	 * @pre salary is a double
	 * @param salary is a double which represents the SalariedEmployee's salary
	 */
	public void setSalary(double salary) {
		this.salary=salary;
	}
	/**
	 * this method returns the SalariedEmployee's salary
	 * @return a double representing the SalariedEmployee's salary
	 */
	public double getSalary() {
		return this.salary;
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
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address)&&this.salary==other.salary);
	}
}