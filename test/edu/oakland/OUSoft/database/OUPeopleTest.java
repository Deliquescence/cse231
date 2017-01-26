package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OUPeopleTest {
	private OUPeople db;
	
	@BeforeEach
	void setUp() {
		db = new OUPeople();
	}
	
	@Test
	void addCourse() {
		Course testCourse = new Course("testCourse");
		Instructor testInstructor = new Instructor("TestInstructorID");
		testCourse.setInstructor(testInstructor);
		
		db.addCourse(testCourse);
		assertTrue(db.getCourses().contains(testCourse), "addCourse did not add a course");
	}
	
	@Test
	void addCourseOverload() {
		Course testCourse = new Course("testCourse");
		Instructor testInstructor = new Instructor("TestInstructorID");
		
		db.addCourse(testCourse, testInstructor);
		assertTrue(db.getCourses().contains(testCourse), "addCourse did not add a course when passed a course and instructor");
	}
	
	@Test
	void addCourseNoInstructor() {
		Course testCourse = new Course("testCourse");
		
		//Must have an instructor
		boolean threwExceptionForNoInstructor = false;
		try {
			db.addCourse(testCourse);
		} catch (IllegalArgumentException shouldThrow) {
			threwExceptionForNoInstructor = true;
		}
		assertTrue(threwExceptionForNoInstructor, "addCourse did not throw an exception for a course not having a valid Instructor");
	}
	
	@Test
	void enroll() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		
		Enrollment testEnrollment = new Enrollment(testCourse, testStudent);
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		
		assertNotNull(returnedEnrollment, "enroll returned null");
		assertEquals(testEnrollment, returnedEnrollment, "enroll return did not equal the expected Enrollment");
	}
	
	@Test
	void withdraw() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		
		Enrollment enrollment = db.enroll(testStudent, testCourse);
		assertNotNull(db.getEnrollment(testStudent, testCourse), "Enroll didn't work");
		
		db.withdraw(testStudent, testCourse);
		assertNull(db.getEnrollment(testStudent, testCourse), "withdraw didn't withdraw");
	}
	
	@Test
	void getEnrollment() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		
		assertNull(db.getEnrollment(testStudent, testCourse), "Magically got enrolled somehow");
		
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		
		assertEquals(returnedEnrollment, db.getEnrollment(testStudent, testCourse), "getEnrollment did not return the same as enroll()");
	}
	
	@Test
	void getEnrollmentsForCourse() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		Student testStudent2 = new Student("TestStudentID2");
		
		assertEquals(0, db.getEnrollments(testCourse).size(), "getEnrollments (for course) magically obtained an Enrollment");
		
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		Enrollment returnedEnrollment2 = db.enroll(testStudent2, testCourse);
		
		List<Enrollment> enrollments = db.getEnrollments(testCourse);
		
		assertTrue(enrollments.contains(returnedEnrollment), "getEnrollments (for course) did not contain an Enrollment it should have");
		assertTrue(enrollments.contains(returnedEnrollment2), "getEnrollments (for course) did not contain an Enrollment it should have");
	}
	
	@Test
	void getEnrollmentsForStudent() {
		Course testCourse = new Course("testCourse");
		Course testCourse2 = new Course("testCourse2");
		Student testStudent = new Student("TestStudentID");
		
		assertEquals(0, db.getEnrollments(testStudent).size(), "getEnrollments (for student) magically obtained an Enrollment");
		
		Enrollment returnedEnrollment = db.enroll(testStudent, testCourse);
		Enrollment returnedEnrollment2 = db.enroll(testStudent, testCourse2);
		
		List<Enrollment> enrollments = db.getEnrollments(testStudent);
		
		assertTrue(enrollments.contains(returnedEnrollment), "getEnrollments (for student) did not contain an Enrollment it should have");
		assertTrue(enrollments.contains(returnedEnrollment2), "getEnrollments (for student) did not contain an Enrollment it should have");
	}
	
	@Test
	void studentIsEnrolled() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		
		assertFalse(db.studentIsEnrolled(testStudent, testCourse), "Student magically got enrolled");
		
		db.enroll(testStudent, testCourse);
		
		assertTrue(db.studentIsEnrolled(testStudent, testCourse), "Student did not get enrolled");
	}
	
	@Test
	void numberStudentsEnrolled() {
		Course testCourse = new Course("testCourse");
		Student testStudent = new Student("TestStudentID");
		Student testStudent2 = new Student("TestStudentID2");
		
		assertEquals(0, db.numberStudentsEnrolled(testCourse), "Should be no one enrolled");
		db.enroll(testStudent, testCourse);
		assertEquals(1, db.numberStudentsEnrolled(testCourse), "Should be one person enrolled");
		db.enroll(testStudent2, testCourse);
		assertEquals(2, db.numberStudentsEnrolled(testCourse), "Should be two people enrolled");
		db.withdraw(testStudent, testCourse);
		assertEquals(1, db.numberStudentsEnrolled(testCourse), "Should be one person enrolled");
		
		//Call enroll again when they are already enrolled that course
		db.enroll(testStudent2, testCourse);
		assertEquals(1, db.numberStudentsEnrolled(testCourse), "numberStudentsEnrolled says more students got enrolled after the same person was enrolled again");
	}
	
	@Test
	void numberCoursesEnrolled() {
		Course testCourse = new Course("testCourse");
		Course testCourse2 = new Course("testCourse2");
		Student testStudent = new Student("TestStudentID");
		
		assertEquals(0, db.numberCoursesEnrolled(testStudent), "Should be no courses enrolled");
		db.enroll(testStudent, testCourse);
		assertEquals(1, db.numberCoursesEnrolled(testStudent), "Should be one course enrolled");
		db.enroll(testStudent, testCourse2);
		assertEquals(2, db.numberCoursesEnrolled(testStudent), "Should be two courses enrolled");
		db.withdraw(testStudent, testCourse);
		assertEquals(1, db.numberCoursesEnrolled(testStudent), "Should be one course enrolled");
		
		//Call enroll again when they are already enrolled that course
		db.enroll(testStudent, testCourse2);
		assertEquals(1, db.numberCoursesEnrolled(testStudent), "numberCoursesEnrolled says more courses got enrolled after the same course was enrolled again");
	}
	
	@Test
	void retrievePersonByID() {
		assertEquals(null, db.retrievePersonByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertEquals(testPerson, db.retrievePersonByID("ID"));
	}
	
	@Test
	void retrieveStudentByID() {
		assertEquals(null, db.retrieveStudentByID("NULL"));
		
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		assertEquals(testStudent, db.retrieveStudentByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveOtherByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void retrieveInstructorByID() {
		assertEquals(null, db.retrieveInstructorByID("NULL"));
		
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		assertEquals(testInstructor, db.retrieveInstructorByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveOtherByID("ID"));
	}
	
	@Test
	void retrieveOtherByID() {
		assertEquals(null, db.retrieveOtherByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertEquals(testPerson, db.retrieveOtherByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void addPerson() {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertTrue(db.getPeople().contains(testPerson));
	}
	
	@Test
	void removeStudent() {
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		db.removeStudent(testStudent);
		assertFalse(db.getStudents().contains(testStudent));
	}
	
	@Test
	void removeInstructor() {
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		db.removeInstructor(testInstructor);
		assertFalse(db.getInstructors().contains(testInstructor));
	}
	
	@Test
	void removeOther() {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removeOther(testPerson);
		assertFalse(db.getPeople().contains(testPerson));
	}
	
	@Test
	void removePerson() {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removePerson(testPerson);
		assertFalse(db.getPeople().contains(testPerson));
	}
	
	@Test
	void removeStudentByID() {
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		db.removeStudentByID("ID");
		assertFalse(db.getStudents().contains(testStudent));
	}
	
	@Test
	void removeInstructorByID() {
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		db.removeInstructorByID("ID");
		assertFalse(db.getInstructors().contains(testInstructor));
	}
	
	@Test
	void removeOtherByID() {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removeOtherByID("ID");
		assertFalse(db.getPeople().contains(testPerson));
	}
	
	@Test
	void removePersonByID() {
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		db.removePersonByID("ID");
		assertFalse(db.getPeople().contains(testPerson));
	}
	
}