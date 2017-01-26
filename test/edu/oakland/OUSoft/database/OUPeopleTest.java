package edu.oakland.OUSoft.database;

import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		assertEquals(null, db.retrievePersonByID("NULL"));
		
		Student testStudent = new Student("ID");
		db.addPerson(testStudent);
		assertEquals(testStudent, db.retrieveStudentByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveOtherByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void retrieveInstructorByID() {
		assertEquals(null, db.retrievePersonByID("NULL"));
		
		Instructor testInstructor = new Instructor("ID");
		db.addPerson(testInstructor);
		assertEquals(testInstructor, db.retrieveInstructorByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveOtherByID("ID"));
	}
	
	@Test
	void retrieveOtherByID() {
		assertEquals(null, db.retrievePersonByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.addPerson(testPerson);
		assertEquals(testPerson, db.retrieveOtherByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void addPerson() {

	}
	
	@Test
	void removeStudent() {
		
	}
	
	@Test
	void removeInstructor() {
		
	}
	
	@Test
	void removeOther() {
		
	}
	
	@Test
	void removePerson() {
		
	}
	
	@Test
	void removeStudentByID() {
		
	}
	
	@Test
	void removeInstructorByID() {
		
	}
	
	@Test
	void removeOtherByID() {
		
	}
	
	@Test
	void removeByID() {
		
	}
	
}