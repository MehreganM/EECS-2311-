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
	 * this is a method that returns the firstName
	 * @return a String that is the first name
	 */
	
	public String getFirstName() {
		return firstName;
		
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
		Employee other=(Employee) object;
		return(this.firstName.equals(other.firstName)&&this.lastName.equals(other.lastName)&&this.age==other.age&&this.gender.equals(other.gender)&&this.address.equals(other.address));
	}
	
	
}
