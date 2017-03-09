package edu.oakland.OUSoft.items;

import edu.oakland.OUSoft.linkedList.LLNode;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * "The person class should include information such as id, first name, last name, etc."
 */
public class Person implements LLNode<Person>, Serializable {
	
	private Person link;
	
	private String ID;
	private String firstName;
	private String lastName;
	private Date Birthday;
	
	/**
	 * Create a Person with an ID
	 *
	 * @param ID The ID of the Person
	 */
	public Person(String ID) {
		this.ID = ID;
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
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person)) {
			return false;
		}
		Person o = (Person) obj;
		return this.ID.equals(o.ID) &&
		       Objects.equals(this.firstName, o.firstName) &&
		       Objects.equals(this.lastName, o.lastName) &&
		       Objects.equals(this.Birthday, o.Birthday);
	}
	
	@Override
	public String toString() {
		return this.ID + ": " + this.firstName + " " + this.lastName;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getBirthday() {
		return Birthday;
	}
	
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	
	/**
	 * Get the link to the next object
	 *
	 * @return The next object in the list
	 */
	@Override
	public Person getLink() {
		return this.link;
	}
	
	/**
	 * Set the link to the next object
	 *
	 * @param Link The next object in the list
	 */
	@Override
	public void setLink(Person Link) {
		this.link = Link;
	}
}
