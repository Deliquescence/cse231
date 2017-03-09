package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OUSoftTest {
	private OUSoft db;
	
	private Course testCourse = new Course("TestCourseID1", "test course 100");
	private Course testCourse2 = new Course("TestCourseID2", "test course 200");
	
	@Before
	public void setUp() throws Exception {
		db = new OUSoft();
	}
	
	@Test
	public void saveAndLoadPeople() throws Exception {
		List<Person> testPeople = new ArrayList<>();
		testPeople.add(new Student("S01"));
		testPeople.add(new Instructor("I01"));
		testPeople.add(new Person("P01"));
		
		for (Person p : testPeople) {
			db.addPerson(p);
		}
		
		db.savePeople("");
		
		db = new OUSoft();
		assertEquals(0, db.getPeople().size());
		
		db.loadPeople("");
		
		assertTrue("Loaded people did not contain saved people", db.getPeople().containsAll(testPeople));
	}
	
	@Test
	public void saveAndLoadCourses() throws Exception {
		List<Course> testCourses = new ArrayList<>();
		testCourses.add(new Course("C001", "test course one", new Instructor("I0011")));
		testCourses.add(new Course("C002", "test course two", new Instructor("I0012")));
		
		for (Course c : testCourses) {
			db.addCourse(c);
		}
		
		db.saveCourses("");
		
		db = new OUSoft();
		assertEquals(0, db.getCourses().size());
		
		db.loadCourses("");
		
		assertTrue("Loaded courses did not contain saved courses", db.getCourses().containsAll(testCourses));
	}
	
	@Test
	public void saveAndLoadEnrollments() throws Exception {
		Student testStudent = new Student("S01");
		db.addPerson(testStudent);
		
		db.addCourse(testCourse, new Instructor("I01"));
		db.enroll(testStudent, testCourse);
		
		db.saveEnrollments("");
		
		db = new OUSoft();
		assertEquals(0, db.getCourses().size());
		
		db.loadEnrollments("");
		
		assertTrue("Loaded enrollments did not match saved enrollments", db.studentIsEnrolled(testStudent, testCourse));
	}
	
	@Test
	public void addCourse() throws Exception {
		Instructor testInstructor = new Instructor("TestInstructorID");
		testCourse.setInstructor(testInstructor);
		
		db.addCourse(testCourse);
		assertTrue("addCourse did not add a course", db.getCourses().contains(testCourse));
		
		//Check duplicate
		int countBeforeTryAddDuplicate = db.getCourses().size();
		try {
			db.addCourse(testCourse);
			assertEquals("Added a duplicate course", countBeforeTryAddDuplicate, db.getCourses().size());
		} catch (IllegalArgumentException shouldThrow) {
		}
	}
	
	@Test
	public void addCourseOverload() throws Exception {
		Instructor testInstructor = new Instructor("TestInstructorID");
		
		db.addCourse(testCourse, testInstructor);
		assertTrue("addCourse did not add a course when passed a course and instructor", db.getCourses().contains(testCourse));
	}
	
	@Test
	public void addCourseNoInstructor() throws Exception {
		
		//Must have an instructor
		boolean threwExceptionForNoInstructor = false;
		try {
			db.addCourse(testCourse);
		} catch (IllegalArgumentException shouldThrow) {
			threwExceptionForNoInstructor = true;
		}
		assertTrue("addCourse did not throw an exception for a course not having a valid Instructor", threwExceptionForNoInstructor);
	}
	
	@Test
	public void removeCourse() throws Exception {
		Instructor testInstructor = new Instructor("TestInstructorID");
		
		db.addCourse(testCourse, testInstructor);
		assertTrue(db.getCourses().contains(testCourse));
		
		db.removeCourse(testCourse);
		assertFalse("Did not remove a course", db.getCourses().contains(testCourse));
	}
	
	@Test
	public void getCourse() throws Exception {
		Instructor testInstructor = new Instructor("TestInstructorID");
		
		db.addCourse(testCourse, testInstructor);
		assertEquals(testCourse, db.getCourseByID("TestCourseID1"));
	}
	
	@Test
	public void enroll() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		db.addPerson(testStudent);
		
		Enrollment testEnrollment = new Enrollment(testCourse, testStudent);
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		
		assertNotNull("enroll returned null", returnedEnrollment);
		assertEquals("enroll return did not equal the expected Enrollment", testEnrollment, returnedEnrollment);
	}
	
	@Test
	public void enrollStudentNotInDB() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		try {
			db.enroll(testStudent, testCourse);
		} catch (IllegalArgumentException shouldThrow) {
			
		}
		
		assertNull("enroll allowed enrolling a student not in the database", db.getEnrollment(testStudent, testCourse));
	}
	
	@Test
	public void withdraw() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		db.addPerson(testStudent);
		Enrollment enrollment = db.enroll(testStudent, testCourse);
		assertNotNull("Enroll didn't work", db.getEnrollment(testStudent, testCourse));
		
		db.withdraw(testStudent, testCourse);
		assertNull("withdraw didn't withdraw", db.getEnrollment(testStudent, testCourse));
	}
	
	@Test
	public void getEnrollment() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		assertNull("Magically got enrolled somehow", db.getEnrollment(testStudent, testCourse));
		
		db.addPerson(testStudent);
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		
		assertEquals("getEnrollment did not return the same as enroll()", returnedEnrollment, db.getEnrollment(testStudent, testCourse));
	}
	
	@Test
	public void getEnrollmentsForCourse() throws Exception {
		Student testStudent = new Student("TestStudentID");
		Student testStudent2 = new Student("TestStudentID2");
		
		assertEquals("getEnrollments (for course) magically obtained an Enrollment", 0, db.getEnrollments(testCourse).size());
		
		db.addPerson(testStudent);
		db.addPerson(testStudent2);
		
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		Enrollment returnedEnrollment2 = db.enroll(testStudent2, testCourse);
		
		List<Enrollment> enrollments = db.getEnrollments(testCourse);
		
		assertTrue("getEnrollments (for course) did not contain an Enrollment it should have", enrollments.contains(returnedEnrollment));
		assertTrue("getEnrollments (for course) did not contain an Enrollment it should have", enrollments.contains(returnedEnrollment2));
	}
	
	@Test
	public void getEnrollmentsForStudent() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		assertEquals("getEnrollments (for student) magically obtained an Enrollment", 0, db.getEnrollments(testStudent).size());
		
		db.addPerson(testStudent);
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		Enrollment returnedEnrollment2 = db.enroll(testStudent, testCourse2);
		
		List<Enrollment> enrollments = db.getEnrollments(testStudent);
		
		assertTrue("getEnrollments (for student) did not contain an Enrollment it should have", enrollments.contains(returnedEnrollment));
		assertTrue("getEnrollments (for student) did not contain an Enrollment it should have", enrollments.contains(returnedEnrollment2));
	}
	
	@Test
	public void studentIsEnrolled() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		assertFalse("Student magically got enrolled", db.studentIsEnrolled(testStudent, testCourse));
		
		db.addPerson(testStudent);
		db.enroll(testStudent, testCourse);
		
		assertTrue("Student did not get enrolled", db.studentIsEnrolled(testStudent, testCourse));
	}
	
	@Test
	public void numberStudentsEnrolled() throws Exception {
		Student testStudent = new Student("TestStudentID");
		Student testStudent2 = new Student("TestStudentID2");
		
		db.addPerson(testStudent);
		db.addPerson(testStudent2);
		
		assertEquals(0, db.numberStudentsEnrolled(testCourse));
		db.enroll(testStudent, testCourse);
		assertEquals(1, db.numberStudentsEnrolled(testCourse));
		db.enroll(testStudent2, testCourse);
		assertEquals(2, db.numberStudentsEnrolled(testCourse));
		db.withdraw(testStudent, testCourse);
		assertEquals(1, db.numberStudentsEnrolled(testCourse));
		
		//Call enroll again when they are already enrolled that course
		db.enroll(testStudent2, testCourse);
		assertEquals("numberStudentsEnrolled says more students got enrolled after the same person was enrolled again", 1, db.numberStudentsEnrolled(testCourse));
	}
	
	@Test
	public void numberCoursesEnrolled() throws Exception {
		Student testStudent = new Student("TestStudentID");
		
		db.addPerson(testStudent);
		
		assertEquals(0, db.numberCoursesEnrolled(testStudent));
		db.enroll(testStudent, testCourse);
		assertEquals(1, db.numberCoursesEnrolled(testStudent));
		db.enroll(testStudent, testCourse2);
		assertEquals(2, db.numberCoursesEnrolled(testStudent));
		db.withdraw(testStudent, testCourse);
		assertEquals(1, db.numberCoursesEnrolled(testStudent));
		
		//Call enroll again when they are already enrolled that course
		db.enroll(testStudent, testCourse2);
		assertEquals("numberCoursesEnrolled says more courses got enrolled after the same course was enrolled again", 1, db.numberCoursesEnrolled(testStudent));
	}
	
	@Test
	public void retrievePersonByID() throws Exception {
		assertNull(db.getPersonByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertEquals("Person retrieved by ID did not match person added", testPerson, db.getPersonByID("ID"));
	}
	
	@Test
	public void retrieveStudentByID() throws Exception {
		assertNull(db.getStudentByID("NULL"));
		
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		assertEquals("Student retrieved by ID did not match person added", testStudent, db.getStudentByID("ID"));
		
		//Check not in wrong category
		assertNull("Retrieved a student from an improper sub-database", db.getOtherByID("ID"));
		assertNull("Retrieved a student from an improper sub-database", db.getInstructorByID("ID"));
	}
	
	@Test
	public void retrieveInstructorByID() throws Exception {
		assertNull(db.getInstructorByID("NULL"));
		
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		assertEquals("Instructor retrieved by ID did not match person added", testInstructor, db.getInstructorByID("ID"));
		
		//Check not in wrong category
		assertNull("Retrieved an instructor from an improper sub-database", db.getStudentByID("ID"));
		assertNull("Retrieved an instructor from an improper sub-database", db.getOtherByID("ID"));
	}
	
	@Test
	public void retrieveOtherByID() throws Exception {
		assertNull(db.getOtherByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertEquals("Other retrieved by ID did not match person added", testPerson, db.getOtherByID("ID"));
		
		//Check not in wrong category
		assertNull("Retrieved a person from an improper sub-database", db.getStudentByID("ID"));
		assertNull("Retrieved a person from an improper sub-database", db.getInstructorByID("ID"));
	}
	
	@Test
	public void addPerson() throws Exception {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertTrue("Did not add a person", db.getPeople().contains(testPerson));
		assertEquals(1, db.getPeople().size());
		
		try {
			db.addPerson(testPerson);
		} catch (IllegalArgumentException ignore) {
		}
		assertEquals("People in database increased after adding the same person again", 1, db.getPeople().size());
	}
	
	@Test
	public void removeStudent() throws Exception {
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		db.removeStudent(testStudent);
		assertFalse("Did not remove a student", db.getStudents().contains(testStudent));
	}
	
	@Test
	public void removeInstructor() throws Exception {
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		db.removeInstructor(testInstructor);
		assertFalse("Did not remove an instructor", db.getInstructors().contains(testInstructor));
	}
	
	@Test
	public void removeOther() throws Exception {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removeOther(testPerson);
		assertFalse("Did not remove an other", db.getPeople().contains(testPerson));
	}
	
	@Test
	public void removePerson() throws Exception {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removePerson(testPerson);
		assertFalse("Did not remove a person", db.getPeople().contains(testPerson));
	}
	
	@Test
	public void removeStudentByID() throws Exception {
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		db.removeStudentByID("ID");
		assertFalse("Did not remove a student by ID", db.getStudents().contains(testStudent));
	}
	
	@Test
	public void removeInstructorByID() throws Exception {
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		db.removeInstructorByID("ID");
		assertFalse("Did not remove an instructor by ID", db.getInstructors().contains(testInstructor));
	}
	
	@Test
	public void removeOtherByID() throws Exception {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removeOtherByID("ID");
		assertFalse("Did not remove an other by ID", db.getPeople().contains(testPerson));
	}
	
	@Test
	public void removePersonByID() throws Exception {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removePersonByID("ID");
		assertFalse("Did not remove a person by ID", db.getPeople().contains(testPerson));
	}
	
}
