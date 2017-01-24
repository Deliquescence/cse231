package edu.oakland.OUSoft.items;

/**
 * "and an instructor class should contain information such as office number, etc"
 */
public class Instructor extends Person {
	
	private String officeBuilding;
	private String officeNumber;
	
	private boolean hasTenure;
	
	/**
	 * Create a blank Instructor
	 */
	public Instructor() {
	}
	
	/**
	 * Create an Instructor, specifing office location
	 *
	 * @param officeBuilding
	 * @param officeNumber
	 */
	public Instructor(String officeBuilding, String officeNumber) {
		this.officeBuilding = officeBuilding;
		this.officeNumber = officeNumber;
	}
	
	@Override
	public String toString(){
		return super.toString() + ", Instructor. Office " + this.officeNumber + " in " + this.officeBuilding;
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
	
	public boolean hasTenure() {
		return hasTenure;
	}
	
	public void setHasTenure(boolean hasTenure) {
		this.hasTenure = hasTenure;
	}
}
