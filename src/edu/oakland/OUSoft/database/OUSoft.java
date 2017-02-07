package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import edu.oakland.OUSoft.linkedList.cseLinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * includes an array of references to all Students and an array of references to all Instructors,
 * methods to add students and instructors,
 * methods to retrieve student/instructor reference using person’s id,
 * methods to remove student/instructor using either the object reference of person’s id,
 * methods for printing all students or instructors,
 * methods for taking input from command line
 */
public class OUSoft {
	
	private final cseLinkedList<Student> students;
	private final cseLinkedList<Instructor> instructors;
	private final cseLinkedList<Person> others;
	private final cseLinkedList<Course> courses;
	private final cseLinkedList<Enrollment> enrollments;
	
	public OUSoft() {
		this.students = new cseLinkedList<>();
		this.instructors = new cseLinkedList<>();
		this.others = new cseLinkedList<>();
		this.courses = new cseLinkedList<>();
		this.enrollments = new cseLinkedList<>();
	}
	
	/**
	 * Add a course to the database.
	 * The course MUST have an instructor assigned.
	 *
	 * @param course The course to add
	 * @throws IllegalArgumentException If the course does not have an instructor assigned
	 */
	public void addCourse(Course course) throws IllegalArgumentException {
		if (course.getInstructor() == null) {
			throw new IllegalArgumentException("Course did not have an instructor assigned.");
		}
		this.courses.add(course);
	}
	
	/**
	 * Add a course to the database, with the given Instructor assigned.
	 *
	 * @param course     The Course to add
	 * @param instructor The Instructor to assign
	 */
	public void addCourse(Course course, Instructor instructor) {
		course.setInstructor(instructor);
		this.courses.add(course);
	}
	
	/**
	 * Enroll a Student in a Course. If they are already enrolled, return the {@link Enrollment} representing so.
	 *
	 * @param student The Student to enroll
	 * @param course  The Course to enroll the Student in
	 * @return The Enrollment object that indicates the students enrollment in the course
	 * @throws IllegalArgumentException If the student is not in the database
	 */
	public Enrollment enroll(Student student, Course course) throws IllegalArgumentException {
		//"The program should only allow enrolling a student who is already in the student array"
		if (getStudentByID(student.getID()) == null) {
			throw new IllegalArgumentException("Cannot enroll a student not in the database");
		}
		//"can only enroll a student in a course once"
		if (studentIsEnrolled(student, course)) {
			return getEnrollment(student, course);
		}
		
		Enrollment e = new Enrollment(course, student);
		this.enrollments.add(e);
		return e;
	}
	
	/**
	 * Withdraw a Student from a Course.
	 *
	 * @param student The Student to withdraw
	 * @param course  The Course to withdraw the Student from
	 */
	public void withdraw(Student student, Course course) {
		this.enrollments.remove(getEnrollment(student, course));
	}
	
	/**
	 * Get the {@link Enrollment} of this Student and Course, if one exists.
	 *
	 * @param student The student to get Enrollment of
	 * @param course  The course to get Enrollment of
	 * @return The Enrollment, or null if none exists
	 */
	public Enrollment getEnrollment(Student student, Course course) {
		for (Enrollment e : this.enrollments) {
			if (e.getCourse() == course && e.getStudent() == student) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Get any Enrollments that contain the given Course
	 *
	 * @param course The Course to get Enrollments of
	 * @return Any Enrollments with this Course
	 */
	public List<Enrollment> getEnrollments(Course course) {
		List<Enrollment> enrollmentList = new ArrayList<>();
		for (Enrollment e : this.enrollments) {
			if (e.getCourse() == course) {
				enrollmentList.add(e);
			}
		}
		return enrollmentList;
	}
	
	/**
	 * Get any Enrollments that contain the given Student
	 *
	 * @param student The Student to get Enrollments of
	 * @return Any Enrollments with this Student
	 */
	public List<Enrollment> getEnrollments(Student student) {
		List<Enrollment> enrollmentList = new ArrayList<>();
		for (Enrollment e : this.enrollments) {
			if (e.getStudent() == student) {
				enrollmentList.add(e);
			}
		}
		return enrollmentList;
	}
	
	/**
	 * Check whether a student is enrolled in a Course
	 *
	 * @param student The Student to check
	 * @param course  The Course to check
	 * @return If the student is enrolled
	 */
	public boolean studentIsEnrolled(Student student, Course course) {
		return getEnrollment(student, course) != null;
	}
	
	/**
	 * Get the number of students enrolled in a course.
	 *
	 * @param course the Course to check
	 * @return The number of Students enrolled
	 */
	public int numberStudentsEnrolled(Course course) {
		return getEnrollments(course).size();
	}
	
	/**
	 * Get the number of courses a student is enrolled in.
	 *
	 * @param student The student to check
	 * @return The number of courses the student is enrolled in
	 */
	public int numberCoursesEnrolled(Student student) {
		return getEnrollments(student).size();
	}
	
	/**
	 * Print all the students who are enrolled in a course to standard output.
	 *
	 * @param course The Course to print students of
	 */
	public void printEnrolled(Course course) {
		for (Enrollment enrollment : getEnrollments(course)) {
			System.out.println(enrollment.getStudent().toString());
		}
	}
	
	/**
	 * Get all the courses in the database.
	 *
	 * @return The courses in the database
	 */
	public List<Course> getCourses() {
		return this.courses;
	}
	
	/**
	 * Print all the courses a student is enrolled in to standard output.
	 *
	 * @param student The Student to print courses of
	 */
	public void printCourses(Student student) {
		for (Enrollment enrollment : getEnrollments(student)) {
			System.out.println(enrollment.getCourse().toString());
		}
	}
	
	/**
	 * Retrieve a person using their ID
	 *
	 * @param ID The persons ID
	 * @return The Person, or null if not found
	 */
	public Person getPersonByID(String ID) {
		Person s = getStudentByID(ID);
		if (s != null) {
			return s;
		}
		Person i = getInstructorByID(ID);
		if (i != null) {
			return i;
		}
		return getOtherByID(ID);
	}
	
	/**
	 * Retrieve a student using their ID
	 *
	 * @param ID The students ID
	 * @return The student, or null if not found
	 */
	public Student getStudentByID(String ID) {
		for (Student student : students) {
			if (student.getID().equals(ID)) {
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
	public Instructor getInstructorByID(String ID) {
		for (Instructor instructor : instructors) {
			if (instructor.getID().equals(ID)) {
				return instructor;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve someone uncategorized using their ID
	 *
	 * @param ID The persons ID
	 * @return The person, or null if not found
	 */
	public Person getOtherByID(String ID) {
		for (Person person : this.others) {
			if (person.getID().equals(ID)) {
				return person;
			}
		}
		return null;
	}
	
	/**
	 * Add a person to the database, automatically determining type
	 * The persons ID must be unique
	 *
	 * @param person The Person to add
	 * @throws IllegalArgumentException If the persons ID is not unique
	 */
	public void addPerson(Person person) throws IllegalArgumentException {
		if (getPersonByID(person.getID()) != null) { //Person is already in DB
			throw new IllegalArgumentException("A Person with this ID is already in the database");
		}
		
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
	 * Remove an other by reference
	 *
	 * @param other The other to remove
	 */
	public void removeOther(Person other) {
		this.others.remove(other);
	}
	
	/**
	 * Remove someone from the database by reference
	 *
	 * @param person The Person to remove
	 */
	public void removePerson(Person person) {
		this.students.remove(person);
		this.instructors.remove(person);
		this.others.remove(person);
	}
	
	/**
	 * Remove a student by ID
	 *
	 * @param ID The ID of the Student to remove
	 */
	public void removeStudentByID(String ID) {
		this.students.remove(getStudentByID(ID));
	}
	
	/**
	 * Remove an instructor by ID
	 *
	 * @param ID The ID of the Instructor to remove
	 */
	public void removeInstructorByID(String ID) {
		this.instructors.remove(getInstructorByID(ID));
	}
	
	/**
	 * Remove an other by ID
	 *
	 * @param ID The ID of the other to remove
	 */
	public void removeOtherByID(String ID) {
		this.others.remove(getOtherByID(ID));
	}
	
	/**
	 * Remove someone from the database based on ID
	 * Note they are removed from all sub-databases. This will not be a problem if IDs are unique.
	 *
	 * @param ID The persons ID
	 */
	public void removePersonByID(String ID) {
		this.removeInstructorByID(ID);
		this.removeStudentByID(ID);
		this.removeOtherByID(ID);
	}
	
	/**
	 * Get all the students in the database.
	 *
	 * @return List of the students in the database
	 */
	public List<Student> getStudents() {
		return students;
	}
	
	/**
	 * Get all the instructors in the database.
	 *
	 * @return List of the instructors in the database
	 */
	public List<Instructor> getInstructors() {
		return instructors;
	}
	
	/**
	 * Get all the uncategorized people in the database.
	 *
	 * @return List of the uncategorized people in the database
	 */
	public List<Person> getOthers() {
		return others;
	}
	
	/**
	 * Get all the people in the database.
	 *
	 * @return List of the people in the database
	 */
	public List<Person> getPeople() {
		List<Person> people = new ArrayList<>();
		people.addAll(this.instructors);
		people.addAll(this.students);
		people.addAll(this.others);
		return people;
	}
	
	/**
	 * Print every student to standard output
	 */
	public void printAllStudents(boolean doHeader) {
		if (doHeader) {
			System.out.println("Students in the database: " + this.students.size());
		}
		for (Student student : this.students) {
			System.out.println(student);
		}
	}
	
	/**
	 * Print every instructor to standard output
	 */
	public void printAllInstructors(boolean doHeader) {
		if (doHeader) {
			System.out.println("Instructors in the database: " + this.instructors.size());
		}
		for (Instructor instructor : this.instructors) {
			System.out.println(instructor);
		}
	}
	
	/**
	 * Print every uncategorized person to standard output
	 */
	public void printAllOthers(boolean doHeader) {
		if (doHeader) {
			System.out.println("There are " + this.others.size() + " others in the database");
		}
		for (Person person : this.others) {
			System.out.println(person);
		}
	}
	
	/**
	 * Print every person to standard output
	 */
	public void printAllPeople(boolean doHeader) {
		if (doHeader) {
			int count = this.students.size() + this.instructors.size() + this.others.size();
			System.out.println("People in the database: " + count);
		}
		this.printAllInstructors(false);
		this.printAllStudents(false);
		this.printAllOthers(false);
	}
}
