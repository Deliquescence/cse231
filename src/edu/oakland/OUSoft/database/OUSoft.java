package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.BST.cseBST;
import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import edu.oakland.OUSoft.linkedList.cseLinkedList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * includes an array of references to all Students and an array of references to all Instructors,
 * methods to add students and instructors,
 * methods to retrieve student/instructor reference using person’s id,
 * methods to remove student/instructor using either the object reference of person’s id,
 * methods for printing all students or instructors,
 * methods for taking input from command line
 */
public class OUSoft {
	
	/*
	 * See the file
	 *  src/BST/cseBST.java
	 * for implementation of a binary search tree.
	 */
	private final cseBST<Student> students;
	private final cseLinkedList<Instructor> instructors;
	private final cseLinkedList<Person> others;
	private final cseLinkedList<Course> courses;
	private final cseLinkedList<Enrollment> enrollments;
	
	public OUSoft() {
		this.students = new cseBST<>();
		this.instructors = new cseLinkedList<>();
		this.others = new cseLinkedList<>();
		this.courses = new cseLinkedList<>();
		this.enrollments = new cseLinkedList<>();
	}
	
	/**
	 * Get the average GPA of all students.
	 * Note GPA is out of 100
	 *
	 * @return the average GPA
	 */
	public double averageGPA() {
		double sum = 0;
		int i = 0;
		
		for (Student s : this.students) {
			sum += s.getGPA();
			i++;
		}
		return sum / i;
	}
	
	/**
	 * Get the rank of this student.
	 * i.e. 1 if they have they highest GPA, 2 for the next highest, etc.
	 * Two students with the same GPA have the same rank.
	 *
	 * @param student the Student to rank
	 * @return the rank of the student
	 * @throws IllegalArgumentException if the student is not in the database.
	 */
	public int rank(Student student) {
		if (getStudentByID(student.getID()) == null) {
			throw new IllegalArgumentException("Cannot rank a student not in the database");
		}
		TreeSet<Double> uniqueGPAs = new TreeSet<>();
		for (Student s : this.students) {
			uniqueGPAs.add(s.getGPA());
		}
		return uniqueGPAs.size() - new ArrayList<>(uniqueGPAs).indexOf(student.getGPA());
	}
	
	/**
	 * Helper to create an ObjectOutputStream at the given file path,
	 * creating the file and its parent directories if necessary.
	 *
	 * @param filePath the path to the file
	 * @return an ObjectOutputStream that writes to the file
	 * @throws IOException
	 */
	private ObjectOutputStream createObjectOutputStream(String filePath) throws IOException {
		
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		if (file.createNewFile()) {
			System.out.println("Created new file: " + file.getPath());
		}
		
		return new ObjectOutputStream(new FileOutputStream(file));
	}
	
	/**
	 * Save the people in this database to the file at the given path.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void savePeople(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/people.bin";
		}
		
		try {
			ObjectOutputStream oos = createObjectOutputStream(filePath);
			oos.writeObject(getPeople());
		} catch (IOException ex) {
			System.err.println("Error writing to the file: " + filePath);
			System.err.println(ex);
		}
	}
	
	/**
	 * Load the people in the file at the given path to this database, adding to any existing.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void loadPeople(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/people.bin";
		}
		try {
			File file = new File(filePath);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			List<Person> peopleList = (List<Person>) ois.readObject();
			for (Person p : peopleList) {
				addPerson(p);
			}
		} catch (IOException ex) {
			System.err.println("Error loading people: ");
			System.err.println(ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save the courses in this database to the file at the given path.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void saveCourses(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/courses.bin";
		}
		
		try {
			ObjectOutputStream oos = createObjectOutputStream(filePath);
			oos.writeObject(getCourses());
		} catch (IOException ex) {
			System.err.println("Error writing to the file: " + filePath);
			System.err.println(ex);
		}
	}
	
	/**
	 * Load the courses in the file at the given path to this database, adding to any existing.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void loadCourses(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/courses.bin";
		}
		try {
			File file = new File(filePath);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			List<Course> courseList = (List<Course>) ois.readObject();
			for (Course c : courseList) {
				addCourse(c);
			}
		} catch (IOException ex) {
			System.err.println("Error loading courses: ");
			System.err.println(ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save the enrollments in this database to the file at the given path.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void saveEnrollments(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/enrollments.bin";
		}
		
		try {
			ObjectOutputStream oos = createObjectOutputStream(filePath);
			oos.writeObject(enrollments);
		} catch (IOException ex) {
			System.err.println("Error writing to the file: " + filePath);
			System.err.println(ex);
		}
	}
	
	/**
	 * Load the enrollments in the file at the given path to this database, adding to any existing.
	 * filePath MAY be null, in which case a default path will be used.
	 *
	 * @param filePath the path to the file, or null to use default.
	 */
	public void loadEnrollments(String filePath) {
		if (filePath == null || filePath.equals("")) {
			filePath = System.getProperty("user.home") + "/OUSoft/enrollments.bin";
		}
		try {
			File file = new File(filePath);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			List<Enrollment> enrollmentList = (List<Enrollment>) ois.readObject();
			for (Enrollment e : enrollmentList) {
				enrollments.add(e);
			}
		} catch (IOException ex) {
			System.err.println("Error loading enrollments: ");
			System.err.println(ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a course to the database, with the given Instructor assigned.
	 *
	 * @param course     The Course to add
	 * @param instructor The Instructor to assign
	 */
	public void addCourse(Course course, Instructor instructor) {
		course.setInstructor(instructor);
		addCourse(course);
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
		if (getCourseByID(course.getID()) != null) { //Course is already in DB
			throw new IllegalArgumentException("A Course with this ID is already in the database");
		}
		this.courses.add(course);
	}
	
	/**
	 * Retrieve a course using its ID.
	 *
	 * @param ID The ID of the course.
	 * @return The course, or null if not found.
	 */
	public Course getCourseByID(String ID) {
		for (Course course : this.courses) {
			if (course.getID().equals(ID)) {
				return course;
			}
		}
		return null;
	}
	
	/**
	 * Remove a course from the database.
	 * Also withdraw all students from the course.
	 *
	 * @param course The course to remove
	 */
	public void removeCourse(Course course) {
		for (Enrollment e : getEnrollments(course)) {
			withdraw(e.getStudent(), course);
		}
		this.courses.remove(course);
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
		course.removeGrade(student);
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
			if (e.getCourse().equals(course) && e.getStudent().equals(student)) {
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
	 * Print all the courses to standard output.
	 */
	public void printAllCourses() {
		for (Course course : this.courses) {
			System.out.println(course.toString());
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
		if (person instanceof Student) {
			this.students.remove(person);
		} else if (person instanceof Instructor) {
			this.instructors.remove(person);
		}
		this.others.remove(person);
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
	 * Get all the students in the database.
	 *
	 * @return Collection of the students in the database
	 */
	public Collection<Student> getStudents() {
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
}
