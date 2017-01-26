package edu.oakland.OUSoft.items;

import java.time.LocalTime;

/**
 * "includes information for a course such as course name, instructor, class meeting time, etc.
 * The instructor field of a Course object should reference to a valid Instructor object."
 */
public class Course {
	
	private String name;
	private Instructor instructor;
	
	private LocalTime timeStart;
	private LocalTime timeEnd;
	
	/**
	 * Create a new Course
	 *
	 * @param name The name of the Course
	 */
	public Course(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	public LocalTime getTimeStart() {
		return timeStart;
	}
	
	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}
	
	public LocalTime getTimeEnd() {
		return timeEnd;
	}
	
	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}
}