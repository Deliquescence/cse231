package edu.oakland.OUSoft.BST;

import edu.oakland.OUSoft.items.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class cseBSTTest {
	
	private cseBST<Student> tree;
	
	private Student testStudent1 = new Student("TestStudentID1");
	private Student testStudent2 = new Student("TestStudentID2");
	private Student testStudent3 = new Student("TestStudentID3");
	private Student testStudent4 = new Student("TestStudentID4");
	private Student testStudent5 = new Student("TestStudentID5");
	
	
	@Before
	public void setUp() throws Exception {
		tree = new cseBST<>();
	}
	
	@Test
	public void size() throws Exception {
		assertEquals(0, tree.size());
		tree.add(testStudent1);
		assertEquals(1, tree.size());
		tree.add(testStudent2);
		assertEquals(2, tree.size());
		tree.remove(testStudent2);
		assertEquals(1, tree.size());
	}
	
	@Test
	public void isEmpty() throws Exception {
		assertTrue(tree.isEmpty());
		tree.add(testStudent1);
		assertFalse(tree.isEmpty());
		tree.remove(testStudent1);
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void contains() throws Exception {
		assertFalse(tree.contains(testStudent1));
		tree.add(testStudent1);
		tree.add(testStudent2);
		assertTrue(tree.contains(testStudent1));
		assertTrue(tree.contains(testStudent2));
		assertFalse(tree.contains(null));
	}
	
	@Test
	public void add() throws Exception {
		assertTrue(tree.add(testStudent1));
		assertTrue(tree.contains(testStudent1));
		//		assertEquals(testStudent1, tree.get(0));
		assertEquals(1, tree.size());
		
		assertTrue(tree.add(testStudent2));
		assertTrue(tree.contains(testStudent2));
		//		assertEquals(testStudent2, tree.get(1));
		assertEquals(2, tree.size());
		
		assertTrue(tree.add(testStudent3));
		assertTrue(tree.contains(testStudent3));
		//		assertEquals(testStudent1, tree.get(2));
		assertEquals(3, tree.size());
		
		assertTrue(tree.add(testStudent4));
		assertTrue(tree.contains(testStudent4));
		//		assertEquals(testStudent1, tree.get(3));
		assertEquals(4, tree.size());
		
		assertTrue(tree.add(testStudent5));
		assertTrue(tree.contains(testStudent5));
		//		assertEquals(testStudent1, tree.get(4));
		assertEquals(5, tree.size());
	}
	
	@Test
	public void remove() throws Exception {
		assertFalse(tree.remove(testStudent1));
		tree.add(testStudent1);
		assertTrue(tree.remove(testStudent1));
		assertFalse(tree.contains(testStudent1));
		
		tree.add(testStudent1);
		tree.add(testStudent2);
		tree.add(testStudent3);
		tree.add(testStudent4);
		tree.add(testStudent5);
		assertEquals(5, tree.size());
		
		assertTrue(tree.remove(testStudent2));
		//		assertEquals(testStudent1, tree.get(0));
		//		assertEquals(testStudent3, tree.get(1));
		//		assertEquals(testStudent1, tree.get(2));
		//		assertEquals(testStudent2, tree.get(3));
		assertEquals(4, tree.size());
		
		assertTrue(tree.remove(testStudent1));
		//		assertEquals(testStudent3, tree.get(0));
		//		assertEquals(testStudent1, tree.get(1));
		//		assertEquals(testStudent2, tree.get(2));
		assertEquals(3, tree.size());
		
		assertTrue(tree.remove(testStudent3));
		assertFalse(tree.contains(testStudent3));
		assertEquals(2, tree.size());
	}
	
	@Test
	public void clear() throws Exception {
		tree.add(testStudent1);
		tree.add(testStudent2);
		
		tree.clear();
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		assertFalse(tree.contains(testStudent2));
	}
	
}
