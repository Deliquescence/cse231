package edu.oakland.OUSoft.items;

/**
 * "and an instructor class should contain information such as office number, etc"
 */
public class Instructor extends Person {
	
	private String officeBuilding;
	private String officeNumber;
	
	private boolean tenured;
	
	/**
	 * Create an empty Instructor
	 */
	public Instructor() {
	}
	
	/**
	 * Create an Instructor with a first name and last name.
	 *
	 * @param firstName The first name of the Instructor
	 * @param lastName  The last name of the Instructor
	 */
	public Instructor(String firstName, String lastName) {
		super(firstName, lastName);
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
	public String toString(){
		return super.toString() + ", Instructor. Office " + this.officeNumber + " in " + this.officeBuilding + ". Tenured: " + this.tenured;
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
