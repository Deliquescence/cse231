package edu.oakland.OUSoft.items;

import java.util.Objects;

/**
 * "A student class should contain more information such as the number of years in college,"
 */
public class Student extends Person implements Comparable<Student> {
	
	private String major;
	
	private int numYearsAttended;
	
	/**
	 * Note: the GPA is out of 100
	 */
	private double GPA;
	
	/**
	 * Create a Student with an ID
	 *
	 * @param ID The ID of the Student
	 */
	public Student(String ID) {
		super(ID);
	}
	
	/**
	 * Create a Student with an ID, first name, and last name.
	 *
	 * @param ID        The ID of the Student
	 * @param firstName The first name of the Student
	 * @param lastName  The last name of the Student
	 */
	public Student(String ID, String firstName, String lastName) {
		super(ID, firstName, lastName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj) || !(obj instanceof Student)) {
			return false;
		}
		Student o = (Student) obj;
		return Objects.equals(this.major, o.major) &&
		       Objects.equals(this.numYearsAttended, o.numYearsAttended) &&
		       Objects.equals(this.GPA, o.GPA);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", Student. Major: " + this.major + ". Years attended: " + this.numYearsAttended;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public int getNumYearsAttended() {
		return numYearsAttended;
	}
	
	public void setNumYearsAttended(int numYearsAttended) {
		this.numYearsAttended = numYearsAttended;
	}
	
	public double getGPA() {
		return GPA;
	}
	
	/**
	 * Note: the GPA is out of 100
	 *
	 * @param GPA the GPA, 0-100
	 */
	public void setGPA(double GPA) {
		if (GPA < 0 || GPA > 100.0) {
			throw new IllegalArgumentException("GPA out of bounds");
		}
		this.GPA = GPA;
	}
	
	@Override
	public int compareTo(Student o) {
		return (int) ((this.getGPA() * 1000) - (o.getGPA() * 1000));
	}
}
