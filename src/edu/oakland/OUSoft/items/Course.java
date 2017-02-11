package edu.oakland.OUSoft.items;

import edu.oakland.OUSoft.linkedList.LLNode;

import java.time.LocalTime;

/**
 * "includes information for a course such as course name, instructor, class meeting time, etc.
 * The instructor field of a Course object should reference to a valid Instructor object."
 */
public class Course implements LLNode<Course> {
	
	private Course link;
	
	private String ID;
	private String name;
	private Instructor instructor;
	
	private LocalTime timeStart;
	private LocalTime timeEnd;
	
	/**
	 * Create a new Course
	 *
	 * @param name The name of the Course
	 */
	public Course(String ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	@Override
	public String toString() {
		return this.ID + ": " + this.name +
				", Taught by " + this.getInstructor().getFirstName() + " " + this.getInstructor().getLastName() + ". " +
				"Start: " + timeStart.toString() +
				" End: " + timeEnd.toString();
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
	
	/**
	 * Get the link to the next object
	 *
	 * @return The next object in the list
	 */
	@Override
	public Course getLink() {
		return this.link;
	}
	
	/**
	 * Set the link to the next object
	 *
	 * @param Link The next object in the list
	 */
	@Override
	public void setLink(Course Link) {
		this.link = Link;
	}
}
