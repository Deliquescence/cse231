package edu.oakland.OUSoft.linkedList;


import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class cseArrayLinkedListTest {
	
	private cseArrayLinkedList<Person> list;
	private cseArrayLinkedList<Student> list2;
	
	private Person testPerson1 = new Person("TestID1");
	private Person testPerson2 = new Person("TestID2");
	private Person testPerson3 = new Person("TestID3");
	private Person testPerson4 = new Person("TestID4");
	
	private Student testStudent1 = new Student("TestStudentID1");
	private Person testStudent2 = new Student("TestStudentID2");
	
	private Instructor testInstructor1 = new Instructor("TestInstructorID1");
	private Person testInstructor2 = new Instructor("TestInstructorID2");
	
	@Before
	public void setUp() throws Exception {
		list = new cseArrayLinkedList<>(5);
		list2 = new cseArrayLinkedList<>(5);
	}
	
	@Test
	public void size() throws Exception {
		assertEquals(0, list.size());
		list.add(testPerson1);
		assertEquals(1, list.size());
		list.add(testPerson2);
		assertEquals(2, list.size());
		list.remove(testPerson2);
		assertEquals(1, list.size());
	}
	
	@Test
	public void isEmpty() throws Exception {
		assertTrue(list.isEmpty());
		list.add(testPerson1);
		assertFalse(list.isEmpty());
		list.remove(testPerson1);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void contains() throws Exception {
		assertFalse(list.contains(testPerson1));
		list.add(testPerson1);
		list.add(testInstructor1);
		assertTrue(list.contains(testPerson1));
		assertTrue(list.contains(testInstructor1));
		assertFalse(list.contains("Some object"));
		assertFalse(list.contains(null));
	}
	
	@Test
	public void iterator() throws Exception {
		list.add(testPerson1);
		list.add(testPerson2);
		list.add(testStudent1);
		list.add(testInstructor1);
		Iterator<Person> iterator = list.iterator();
		
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected first element", testPerson1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected second element", testPerson2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected third element", testStudent1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected second element", testInstructor1, iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void toArray() throws Exception {
		list.add(testPerson1);
		list.add(testPerson2);
		list.add(testStudent1);
		
		Object[] array = list.toArray();
		assertEquals(testPerson1, array[0]);
		assertEquals(testPerson2, array[1]);
		assertEquals(testStudent1, array[2]);
	}
	
	@Test
	public void toArray1() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor1);
		
		Person[] array = list.toArray(new Person[0]);
		assertEquals(testPerson1, array[0]);
		assertEquals(testInstructor1, array[1]);
		
		Person[] array2 = new Person[2];
		list.toArray(array2);
		assertEquals(testPerson1, array2[0]);
		assertEquals(testInstructor1, array2[1]);
		
		Person[] array3 = new Person[3];
		array3[2] = testPerson4;
		list.toArray(array3);
		assertEquals(testPerson1, array3[0]);
		assertEquals(testInstructor1, array3[1]);
		assertNull(array3[2]);
	}
	
	@Test
	public void add() throws Exception {
		assertTrue(list.add(testPerson1));
		assertTrue(list.contains(testPerson1));
		assertEquals(testPerson1, list.get(0));
		assertEquals(1, list.size());
		
		assertTrue(list.add(testPerson2));
		assertTrue(list.contains(testPerson2));
		assertEquals(testPerson2, list.get(1));
		assertEquals(2, list.size());
		
		assertTrue(list.add(testStudent1));
		assertTrue(list.contains(testStudent1));
		assertEquals(testStudent1, list.get(2));
		assertEquals(3, list.size());
		
		
		assertTrue(list2.add(testStudent1));
		assertTrue(list2.contains(testStudent1));
		assertEquals(testStudent1, list2.get(0));
		assertEquals(1, list2.size());
		
	}
	
	@Test
	public void remove() throws Exception {
		assertFalse(list.remove(testPerson1));
		list.add(testPerson1);
		assertTrue(list.remove(testPerson1));
		assertFalse(list.contains(testPerson1));
		
		list.add(testPerson1);
		list.add(testStudent2);
		list.add(testPerson3);
		assertEquals(3, list.size());
		
		assertTrue(list.remove(testStudent2));
		assertEquals(testPerson1, list.get(0));
		assertEquals(testPerson3, list.get(1));
		assertFalse(list.contains(testStudent2));
		
	}
	
	@Test
	public void containsAll() throws Exception {
		list.add(testPerson1);
		list.add(testStudent2);
		list.add(testPerson3);
		assertEquals(3, list.size());
		
		Collection<Person> testCollection1 = new ArrayList<>();
		Collection<Person> testCollection2 = new ArrayList<>();
		Collection<Person> testCollection3 = new ArrayList<>();
		
		testCollection1.add(testStudent2);
		
		testCollection2.add(testPerson1);
		testCollection2.add(testStudent2);
		testCollection2.add(testPerson3);
		
		assertTrue(list.containsAll(testCollection1));
		assertTrue(list.containsAll(testCollection2));
		assertTrue(list.containsAll(testCollection3));
		
		list.remove(testPerson3);
		assertFalse(list.containsAll(testCollection2));
		
		List<Object> wrongType = new ArrayList<>();
		wrongType.add("Checking for typing exceptions");
		wrongType.add(testInstructor2);
		assertFalse(list2.containsAll(wrongType));
	}
	
	@Test
	public void addAll() throws Exception {
		Collection<Person> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testPerson1);
		testCollection1.add(testStudent2);
		testCollection1.add(testPerson3);
		
		assertTrue(list.addAll(testCollection1));
		assertTrue(list.containsAll(testCollection1));
	}
	
	@Test
	public void addAllIndex() throws Exception {
		Collection<Person> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testPerson3);
		testCollection1.add(testInstructor2);
		
		list.add(testPerson1);
		list.add(testPerson2);
		assertTrue(list.addAll(1, testCollection1));
		
		assertEquals(testPerson1, list.get(0));
		assertEquals(testPerson3, list.get(1));
		assertEquals(testInstructor2, list.get(2));
		assertEquals(testPerson2, list.get(3));
		
		try {
			list.addAll(99, testCollection1);
			assertTrue("Did not throw exception for out of bounds index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		try {
			list.addAll(-1, testCollection1);
			assertTrue("Did not throw exception for negative index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
	}
	
	@Test
	public void removeAll() throws Exception {
		Collection<Person> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testPerson1);
		testCollection1.add(testPerson2);
		testCollection1.add(testPerson3);
		
		assertFalse("List should not have changed", list.removeAll(testCollection1));
		
		assertTrue(list.addAll(testCollection1));
		assertTrue(list.containsAll(testCollection1));
		
		testCollection1.add(testPerson4); //Not removed because not in list, but removeAll should still return true
		assertTrue(list.removeAll(testCollection1));
		assertFalse(list.contains(testPerson1));
		
	}
	
	@Test
	public void retainAll() throws Exception {
		list.add(testPerson1);
		list.add(testPerson2);
		list.add(testPerson3);
		list.add(testPerson4);
		
		Collection<Person> testCollection1 = new ArrayList<>();
		testCollection1.add(testPerson1);
		testCollection1.add(testPerson2);
		testCollection1.add(testPerson3);
		testCollection1.add(testPerson4);
		
		assertFalse(list.retainAll(testCollection1));
		
		testCollection1.remove(testPerson2);
		testCollection1.remove(testPerson4);
		
		assertTrue(list.retainAll(testCollection1));
		assertEquals(testPerson1, list.get(0));
		assertEquals(testPerson3, list.get(1));
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void clear() throws Exception {
		list.add(testPerson1);
		list.add(testPerson2);
		
		list.clear();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains(testPerson2));
	}
	
	@Test
	public void equals() throws Exception {
		List<Person> testList = new ArrayList<>();
		
	}
	
	@Test
	public void get() throws Exception {
		list.add(testPerson1);
		assertEquals(testPerson1, list.get(0));
		list.add(testPerson2);
		assertEquals(testPerson2, list.get(1));
		list.add(testPerson3);
		assertEquals(testPerson3, list.get(2));
		
		list.add(testInstructor1);
		assertEquals(testInstructor1, list.get(3));
		list.add(testInstructor2);
		assertEquals(testInstructor2, list.get(4));
		
		try {
			list.get(-1);
			assertTrue("Did not throw exception for negative index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		try {
			list.get(99);
			assertTrue("Did not throw exception for out of bounds index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
	}
	
	@Test
	public void set() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor2);
		list.add(testPerson3);
		
		assertEquals(testPerson1, list.get(0));
		assertEquals(testInstructor2, list.get(1));
		assertEquals(testPerson3, list.get(2));
		
		assertEquals(testInstructor2, list.set(1, testStudent1)); //Set returns element previously at that index
		
		assertEquals(testPerson1, list.get(0));
		assertEquals(testStudent1, list.get(1));
		assertEquals(testPerson3, list.get(2));
		
		try {
			list.set(-1, testPerson4);
			assertTrue("Did not throw exception for negative index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		try {
			list.set(99, testPerson4);
			assertTrue("Did not throw exception for out of bounds index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
	}
	
	@Test
	public void addIndex() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor2);
		
		list.add(1, testStudent1);
		assertEquals(testPerson1, list.get(0));
		assertEquals(testStudent1, list.get(1));
		assertEquals(testInstructor2, list.get(2));
		
		try {
			list.add(-1, testPerson4);
			assertTrue("Did not throw exception for negative index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		try {
			list.add(99, testPerson4);
			assertTrue("Did not throw exception for out of bounds index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
	}
	
	@Test
	public void removeIndex() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor2);
		list.add(testPerson3);
		list.add(testPerson4);
		
		assertEquals(testPerson3, list.remove(2));
		assertEquals(testPerson1, list.get(0));
		assertEquals(testInstructor2, list.get(1));
		assertEquals(testPerson4, list.get(2));
		
		assertEquals(testPerson1, list.remove(0));
		assertEquals(testInstructor2, list.get(0));
		assertEquals(testPerson4, list.get(1));
		
		try {
			list.remove(-1);
			assertTrue("Did not throw exception for negative index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		try {
			list.remove(99);
			assertTrue("Did not throw exception for out of bounds index", false);
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
	}
	
	@Test
	public void indexOf() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor2);
		list.add(testPerson1);
		list.add(testInstructor2);
		
		assertEquals(0, list.indexOf(testPerson1));
		assertEquals(1, list.indexOf(testInstructor2));
		assertEquals(-1, list.indexOf(testPerson3));
	}
	
	@Test
	public void lastIndexOf() throws Exception {
		list.add(testPerson1);
		list.add(testInstructor2);
		list.add(testPerson1);
		list.add(testInstructor2);
		
		assertEquals(2, list.lastIndexOf(testPerson1));
		assertEquals(3, list.lastIndexOf(testInstructor2));
		assertEquals(-1, list.lastIndexOf(testPerson3));
	}
	
}
