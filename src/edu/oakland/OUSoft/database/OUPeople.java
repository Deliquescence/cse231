package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;

import java.util.ArrayList;

/**
 * includes an array of references to all Students and an array of references to all Instructors,
 * methods to add students and instructors,
 * methods to retrieve student/instructor reference using person’s id,
 * methods to remove student/instructor using either the object reference of person’s id,
 * methods for printing all students or instructors,
 * methods for taking input from command line
 */
public class OUPeople {
	
	private ArrayList students;
	private ArrayList instructors;
	private ArrayList others;
	
	/**
	 * Retrieve a student using their ID
	 *
	 * @param ID The students ID
	 * @return The student, or null if not found
	 */
	public Student retrieveStudentByID(int ID) {
		for (Student student : students) {
			if (student.getID() == ID) {
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve an instructor using their ID
	 *
	 * @param ID The instructors ID
	 * @return The instructor, or null if not found
	 */
	public Student retrieveInstructorByID(int ID) {
		for (Instructor instructor : instructors) {
			if (instructor.getID() == ID) {
				return instructor;
			}
		}
		return null;
	}
	
	/**
	 * Add a person to the database, automatically determining type
	 *
	 * @param person The Person to add
	 */
	public void add(Person person) {
		if (person instanceof Student) {
			this.students.add((Student) person);
		} else if (person instanceof Instructor) {
			this.instructors.add((Instructor) person);
		} else {
			this.others.add(person);
		}
		
	}
}
