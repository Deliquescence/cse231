package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Student;

/**
 * "contains at least two fields, one for referencing to the Course object and one referencing to the Student object.
 * Since we will build a linked list of the all enrollment objects,
 * you will add an “Enrollment link” filed in the Enrollment class, the same approach as the LLNode class in the textbook."
 */
public class Enrollment {
	
	private Course course;
	private Student student;
	
	private Enrollment link;
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Enrollment getLink() {
		return link;
	}
	
	public void setLink(Enrollment link) {
		this.link = link;
	}
}
