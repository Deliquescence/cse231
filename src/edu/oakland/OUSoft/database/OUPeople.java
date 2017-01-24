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
	
	private ArrayList<Student> students;
	private ArrayList<Instructor> instructors;
	private ArrayList<Person> others;
	
	public OUPeople(){
		
		this.students = new ArrayList<>();
		this.instructors = new ArrayList<>();
		this.others = new ArrayList<>();
	}
	
	/**
	 * Retrieve a student using their ID
	 *
	 * @param ID The students ID
	 * @return The student, or null if not found
	 */
	public Student retrieveStudentByID(String ID) {
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
	public Instructor retrieveInstructorByID(String ID) {
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
	
	/**
	 * Remove a student by reference
	 *
	 * @param student The Student to remove
	 */
	public void removeStudent(Student student) {
		
		this.students.remove(student);
	}
	
	/**
	 * Remove an instructor by reference
	 *
	 * @param instructor The Instructor to remove
	 */
	public void removeInstructor(Instructor instructor) {
		
		this.instructors.remove(instructor);
	}
	
	/**
	 * Remove a student by ID
	 *
	 * @param student The ID of the Student to remove
	 */
	public void removeStudentByID(String ID) {
		
		this.students.remove(retrieveStudentByID(ID));
	}
	
	/**
	 * Remove an instructor by ID
	 *
	 * @param instructor The ID of the Instructor to remove
	 */
	public void removeInstructorByID(String ID) {
		
		this.instructors.remove(retrieveInstructorByID(ID));
	}
	
	/**
	 * Print every student to standard output
	 */
	public void printAllStudents() {
		for (Student student : this.students) {
			System.out.println(student);
		}
	}
	
	/**
	 * Print every instructor to standard output
	 */
	public void printAllInstructors() {
		for (Instructor instructor : this.instructors) {
			System.out.println(instructor);
		}
	}
}
