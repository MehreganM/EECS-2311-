class Volunteer extends Employee{
	private Physician physician;
	/**
	 * this default constructor creates a volunteer
	 */
	public Volunteer() {
		super();
	}
	/**
	 * this overloaded constructor initializes the given inputs to the appropriate
	 * attributes of the volunteer
	 * @param firstName is a String representing volunteer's firstName
	 * @param lastName is a String representing volunteer's lastName
	 * @param age is an int representing the volunteer's age
	 * @param gender is a String representing volunteer's gender
	 * @param address is a String representing volunteer's address
	 */
	public Volunteer(String firstName,String lastName,int age, String gender,String address) {
		super(firstName,lastName,age,gender,address);
	}
	@Override
	/**
	 * this method returns the String representation of the object
	 * @return the String representation of the object
	 */
	public String toString() {
		String answer="Volunteer [["+this.getEmployeeID()+",["+this.firstName+", "+this.lastName+", "+this.age+", "+this.gender+", "+this.address+"]]]";
		return answer;
	}
	@Override
	/** this method returns true if the objects are equal and false otherwise
	 * @return true if the objects are equal and false otherwise
	 */
	public boolean equals(Object volunteer) {
		if(this==volunteer) {
			return true;
		}
		if(volunteer==null) {
			return false;
		}
		if(getClass()!=volunteer.getClass()) {
			return false;
		}
		Volunteer other=(Volunteer) volunteer;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address));
		
	}
	/**
	 * this method sets the volunteer's assigned physician to the given input
	 * @pre physician is not null
	 * @param physician is an object of type Physician
	 */
	public void setPhysician(Physician physician) {
		this.physician=physician;
		
	}
	/**
	 * this method returns the physician that the volunteer is assigned to
	 * @return an object of type Physician
	 */
	public Physician getPhysician() {
		return this.physician;
	}
	
}
