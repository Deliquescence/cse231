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
	void retrieveByID() {
		assertEquals(null, db.retrieveByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.add(testPerson);
		assertEquals(testPerson, db.retrieveByID("ID"));
	}
	
	@Test
	void retrieveStudentByID() {
		assertEquals(null, db.retrieveByID("NULL"));
		
		Student testStudent = new Student("ID");
		db.add(testStudent);
		assertEquals(testStudent, db.retrieveStudentByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveOtherByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void retrieveInstructorByID() {
		assertEquals(null, db.retrieveByID("NULL"));
		
		Instructor testInstructor = new Instructor("ID");
		db.add(testInstructor);
		assertEquals(testInstructor, db.retrieveInstructorByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveOtherByID("ID"));
	}
	
	@Test
	void retrieveOtherByID() {
		assertEquals(null, db.retrieveByID("NULL"));
		
		Person testPerson = new Person("ID");
		db.add(testPerson);
		assertEquals(testPerson, db.retrieveOtherByID("ID"));
		
		//Check not in wrong category
		assertEquals(null, db.retrieveStudentByID("ID"));
		assertEquals(null, db.retrieveInstructorByID("ID"));
	}
	
	@Test
	void add() {

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
	void remove() {
		
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