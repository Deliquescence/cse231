package edu.oakland.OUSoft.items;

/**
 * "A student class should contain more information such as the number of years in college,"
 */
public class Student extends Person {
	
	private String major;
	
	private int numYearsAttended;
	
	private double GPA;
	
	/**
	 * Create an empty Student
	 */
	public Student() {
	}
	
	/**
	 * Create a Student with the specified major
	 *
	 * @param major The major of the Student
	 */
	public Student(String major) {
		this.major = major;
	}
	
	/**
	 * Create a Student with the specified major and number of years attended
	 *
	 * @param major         The major of the Student
	 * @param yearsAttended The number of years the Student has been in attendance
	 */
	public Student(String major, int yearsAttended) {
		this.major = major;
		this.numYearsAttended = yearsAttended;
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
	
	public void setGPA(double GPA) {
		if (GPA < 0 || GPA > 4.0) {
			throw new IllegalArgumentException("GPA out of bounds");
		}
		this.GPA = GPA;
	}
}
