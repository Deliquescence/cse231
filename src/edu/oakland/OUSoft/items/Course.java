package edu.oakland.OUSoft.items;

import edu.oakland.OUSoft.linkedList.LLNode;
import edu.oakland.OUSoft.linkedList.cseSortedArrayLinkedList;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * "includes information for a course such as course name, instructor, class meeting time, etc.
 * The instructor field of a Course object should reference to a valid Instructor object."
 */
public class Course implements LLNode<Course>, Serializable {
	
	private Course link;
	
	/**
	 * See the file
	 *  src/linkedList/cseSortedArrayLinkedList.java
	 * and its super class
	 *  src/linkedList/cseArrayLinkedList.java
	 * for implementation of an array based linked list.
	 */
	private cseSortedArrayLinkedList<Grade> grades;
	
	private String ID;
	private String name;
	private Instructor instructor;
	
	private LocalTime timeStart;
	private LocalTime timeEnd;
	
	/**
	 * Create a new Course
	 *
	 * @param ID   the ID of the course
	 * @param name the name of the Course
	 */
	public Course(String ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	/**
	 * Create a new Course
	 *
	 * @param ID         the ID of the course
	 * @param name       the name of the Course
	 * @param instructor the instructor of the course
	 */
	public Course(String ID, String name, Instructor instructor) {
		this.ID = ID;
		this.name = name;
		this.instructor = instructor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course)) {
			return false;
		}
		Course o = (Course) obj;
		return this.ID.equals(o.ID) &&
		       Objects.equals(this.name, o.name) &&
		       Objects.equals(this.instructor, o.instructor) &&
		       Objects.equals(this.timeStart, o.timeStart) &&
		       Objects.equals(this.timeEnd, o.timeEnd);
	}
	
	@Override
	public String toString() {
		return this.ID +
		       ": " +
		       this.name +
		       ", Taught by " +
		       this.getInstructor().getFirstName() +
		       " " +
		       this.getInstructor().getLastName() +
		       ". " +
		       "Start: " +
		       timeStart.toString() +
		       " End: " +
		       timeEnd.toString();
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
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
