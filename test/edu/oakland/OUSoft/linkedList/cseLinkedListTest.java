package edu.oakland.OUSoft.linkedList;

import edu.oakland.OUSoft.database.Enrollment;
import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class cseLinkedListTest {
	
	private cseLinkedList<Enrollment> list;
//	private ArrayList<Enrollment> list;
	
	private Enrollment testEnrollment1 = new Enrollment(new Course("TestCourse"), new Student("TestID"));
	private Enrollment testEnrollment2 = new Enrollment(new Course("TestCourse2"), new Student("TestID2"));
	private Enrollment testEnrollment3 = new Enrollment(new Course("TestCourse3"), new Student("TestID3"));
	private Enrollment testEnrollment4 = new Enrollment(new Course("TestCourse4"), new Student("TestID4"));
	
	@Before
	public void setUp() throws Exception {
		list = new cseLinkedList<>();
//		list = new ArrayList<>();
	}
	
	@Test
	public void size() throws Exception {
		assertEquals(0, list.size());
		list.add(testEnrollment1);
		assertEquals(1, list.size());
		list.add(testEnrollment2);
		assertEquals(2, list.size());
		list.remove(testEnrollment2);
		assertEquals(1, list.size());
	}
	
	@Test
	public void isEmpty() throws Exception {
		assertTrue(list.isEmpty());
		list.add(testEnrollment1);
		assertFalse(list.isEmpty());
		list.remove(testEnrollment1);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void contains() throws Exception {
		assertFalse(list.contains(testEnrollment1));
		list.add(testEnrollment1);
		assertTrue(list.contains(testEnrollment1));
	}
	
	@Test
	public void iterator() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		Iterator<Enrollment> iterator = list.iterator();
		
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected first element", testEnrollment1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected second element", testEnrollment2, iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void toArray() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		
		Object[] array = list.toArray();
		assertEquals(testEnrollment1, array[0]);
		assertEquals(testEnrollment2, array[1]);
	}
	
	@Test
	public void toArray1() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		
		Object[] array = list.toArray(new Enrollment[0]);
		assertEquals(testEnrollment1, array[0]);
		assertEquals(testEnrollment2, array[1]);
	}
	
	@Test
	public void add() throws Exception {
		assertTrue(list.add(testEnrollment1));
		assertTrue(list.contains(testEnrollment1));
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(1, list.size());
		assertTrue(list.add(testEnrollment2));
		assertTrue(list.contains(testEnrollment2));
		assertEquals(testEnrollment2, list.get(1));
		assertEquals(2, list.size());
		
		//Do not allow duplicate
		assertFalse("Added a duplicate element", list.add(testEnrollment1));
		assertEquals("Added a duplicate element", 2, list.size());
		
		//Do not allow null
		try {
			list.add(null);
			assertEquals("Added a null element", 2, list.size());
		} catch (NullPointerException e) {
			assertEquals("Added a null element", 2, list.size());
		}
	}
	
	@Test
	public void remove() throws Exception {
		try {
			list.remove(null);
			assertTrue("Should have thrown NullPointerException", false);
		} catch (NullPointerException shouldThrow) {
		}
		assertFalse(list.remove(testEnrollment1));
		list.add(testEnrollment1);
		assertTrue(list.remove(testEnrollment1));
		assertFalse(list.contains(testEnrollment1));
		
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		list.add(testEnrollment3);
		assertEquals(3, list.size());
		
		assertTrue(list.remove(testEnrollment2));
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment3, list.get(1));
		assertFalse(list.contains(testEnrollment2));
	}
	
	@Test
	public void containsAll() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		list.add(testEnrollment3);
		assertEquals(3, list.size());
		
		Collection<Enrollment> testCollection1 = new ArrayList<>();
		Collection<Enrollment> testCollection2 = new ArrayList<>();
		Collection<Enrollment> testCollection3 = new ArrayList<>();
		
		testCollection1.add(testEnrollment2);
		
		testCollection2.add(testEnrollment1);
		testCollection2.add(testEnrollment2);
		testCollection2.add(testEnrollment3);
		
		assertTrue(list.containsAll(testCollection1));
		assertTrue(list.containsAll(testCollection2));
		assertTrue(list.containsAll(testCollection3));
		
		list.remove(testEnrollment3);
		assertFalse(list.containsAll(testCollection2));
	}
	
	@Test
	public void addAll() throws Exception {
		Collection<Enrollment> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testEnrollment1);
		testCollection1.add(testEnrollment2);
		testCollection1.add(testEnrollment3);
		
		assertTrue(list.addAll(testCollection1));
		assertTrue(list.containsAll(testCollection1));
		
		assertFalse(list.addAll(testCollection1)); //List Should not change as a result of this (duplicates)
	}
	
	@Test
	public void addAllIndex() throws Exception {
		Collection<Enrollment> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testEnrollment3);
		testCollection1.add(testEnrollment4);
		
		try {
			assertFalse(list.addAll(1, testCollection1));
		} catch (IndexOutOfBoundsException shouldThrow) {
		}
		
		
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		assertTrue(list.addAll(1, testCollection1));
		
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment3, list.get(1));
		assertEquals(testEnrollment4, list.get(2));
		assertEquals(testEnrollment2, list.get(3));
		
	}
	
	@Test
	public void removeAll() throws Exception {
		Collection<Enrollment> testCollection1 = new ArrayList<>();
		
		testCollection1.add(testEnrollment1);
		testCollection1.add(testEnrollment2);
		testCollection1.add(testEnrollment3);
		
		assertFalse(list.removeAll(testCollection1)); //List should not have changed because of this
		
		assertTrue(list.addAll(testCollection1));
		assertTrue(list.containsAll(testCollection1));
		
		assertTrue(list.removeAll(testCollection1));
		assertFalse(list.contains(testEnrollment1));
	}
	
	@Test
	public void retainAll() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		list.add(testEnrollment3);
		list.add(testEnrollment4);
		
		Collection<Enrollment> testCollection1 = new ArrayList<>();
		testCollection1.add(testEnrollment1);
		testCollection1.add(testEnrollment2);
		testCollection1.add(testEnrollment3);
		testCollection1.add(testEnrollment4);
		
		assertFalse(list.retainAll(testCollection1));
		
		testCollection1.remove(testEnrollment2);
		testCollection1.remove(testEnrollment4);
		
		assertTrue(list.retainAll(testCollection1));
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment3, list.get(1));
		assertEquals(2, list.size());
	}
	
	@Test
	public void clear() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains(testEnrollment2));
	}
	
	@Test
	public void equals() throws Exception {
		List<Enrollment> testList = new ArrayList<>();
		
	}
	
	@Test
	public void get() throws Exception {
		list.add(testEnrollment1);
		assertEquals(testEnrollment1, list.get(0));
		list.add(testEnrollment2);
		assertEquals(testEnrollment2, list.get(1));
		list.add(testEnrollment3);
		assertEquals(testEnrollment3, list.get(2));
	}
	
	@Test
	public void set() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		list.add(testEnrollment3);
		
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment2, list.get(1));
		assertEquals(testEnrollment3, list.get(2));
		
		assertEquals(testEnrollment2, list.set(1, testEnrollment4)); //Set returns element previously at that index
		
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment4, list.get(1));
		assertEquals(testEnrollment3, list.get(2));
	}
	
	@Test
	public void addIndex() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		
		list.add(1, testEnrollment3);
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment3, list.get(1));
		assertEquals(testEnrollment2, list.get(2));
	}
	
	@Test
	public void removeIndex() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		list.add(testEnrollment3);
		list.add(testEnrollment4);
		
		assertEquals(testEnrollment3, list.remove(2));
		assertEquals(testEnrollment1, list.get(0));
		assertEquals(testEnrollment2, list.get(1));
		assertEquals(testEnrollment4, list.get(2));
		
		assertEquals(testEnrollment1, list.remove(0));
		assertEquals(testEnrollment2, list.get(0));
		assertEquals(testEnrollment4, list.get(1));
	}
	
	@Test
	public void indexOf() throws Exception {
		list.add(testEnrollment1);
		list.add(testEnrollment2);
		
		assertEquals(0, list.indexOf(testEnrollment1));
		assertEquals(1, list.indexOf(testEnrollment2));
		assertEquals(-1, list.indexOf(testEnrollment3));
	}
	
	@Test
	public void lastIndexOf() throws Exception {
		indexOf(); //No duplicate elements, so is same
	}
	
}