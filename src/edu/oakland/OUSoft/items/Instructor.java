package edu.oakland.OUSoft.items;

import java.util.Objects;

/**
 * "and an instructor class should contain information such as office number, etc"
 */
public class Instructor extends Person {
	
	private String officeBuilding;
	private String officeNumber;
	
	private boolean tenured;
	
	/**
	 * Create an Instructor with an ID
	 *
	 * @param ID The ID of the Instructor
	 */
	public Instructor(String ID) {
		super(ID);
	}
	
	/**
	 * Create an Instructor with an ID, first name, and last name.
	 *
	 * @param ID        The ID of the Instructor
	 * @param firstName The first name of the Instructor
	 * @param lastName  The last name of the Instructor
	 */
	public Instructor(String ID, String firstName, String lastName) {
		super(ID, firstName, lastName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj) || !(obj instanceof Instructor)) {
			return false;
		}
		Instructor o = (Instructor) obj;
		return Objects.equals(this.officeBuilding, o.officeBuilding) &&
		       Objects.equals(this.officeNumber, o.officeNumber) &&
		       Objects.equals(this.tenured, o.tenured);
	}
	
	@Override
	public String toString() {
		return super.toString() +
		       ", Instructor. Office " +
		       this.officeNumber +
		       " in " +
		       this.officeBuilding +
		       ". Tenured: " +
		       this.tenured;
	}
	
	public String getOfficeBuilding() {
		return officeBuilding;
	}
	
	public void setOfficeBuilding(String officeBuilding) {
		this.officeBuilding = officeBuilding;
	}
	
	public String getOfficeNumber() {
		return officeNumber;
	}
	
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	
	public boolean isTenured() {
		return tenured;
	}
	
	public void setTenured(boolean tenured) {
		this.tenured = tenured;
	}
}
