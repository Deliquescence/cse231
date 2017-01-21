package edu.oakland.OUSoft.items;

/**
 * "The person class should include information such as id, first name, last name, etc."
 */
public class Person {
	
	private String ID;
	private String firstName;
	private String lastName;
	private Date Birthday;
	
	/**
	 * Create an empty person
	 */
	public Person() {
	}
	
	/**
	 * Create a person with a first name and last name.
	 *
	 * @param firstName The first name of the Person
	 * @param lastName  The last name of the Person
	 */
	public Person(String firstName, String lastName) {
		this.FirstName = firstName;
		this.LastName = lastName;
	}
	
	/**
	 * Create a person with an ID, first name, and last name.
	 *
	 * @param ID        The ID of the Person
	 * @param firstName The first name of the Person
	 * @param lastName  The last name of the Person
	 */
	public Person(String ID, String firstName, String lastName) {
		this.ID = ID;
		this.FirstName = firstName;
		this.LastName = lastName;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public Date getBirthday() {
		return Birthday;
	}
	
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
}
