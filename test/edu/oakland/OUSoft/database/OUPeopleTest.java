package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OUPeopleTest {
	
	private OUPeople db;
	
	@BeforeEach
	void setUp(){
		db = new OUPeople();
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