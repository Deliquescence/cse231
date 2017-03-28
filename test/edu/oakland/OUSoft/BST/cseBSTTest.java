package edu.oakland.OUSoft.BST;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class cseBSTTest {
	
	private cseBST<Integer> tree;
	
	@Before
	public void setUp() throws Exception {
		tree = new cseBST<>();
	}
	
	@Test
	public void size() throws Exception {
		assertEquals(0, tree.size());
		tree.add(1);
		assertEquals(1, tree.size());
		tree.add(2);
		assertEquals(2, tree.size());
		tree.remove(3);
		assertEquals(1, tree.size());
	}
	
	@Test
	public void isEmpty() throws Exception {
		assertTrue(tree.isEmpty());
		tree.add(1);
		assertFalse(tree.isEmpty());
		tree.remove(1);
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void contains() throws Exception {
		assertFalse(tree.contains(1));
		tree.add(1);
		tree.add(2);
		assertTrue(tree.contains(1));
		assertTrue(tree.contains(2));
		assertFalse(tree.contains(null));
	}
	
	@Test
	public void add() throws Exception {
		assertTrue(tree.add(1));
		assertTrue(tree.contains(1));
		assertEquals(1, tree.size());
		
		assertTrue(tree.add(2));
		assertTrue(tree.contains(2));
		assertEquals(2, tree.size());
		
		assertTrue(tree.add(54));
		assertTrue(tree.contains(54));
		assertEquals(3, tree.size());
		
		assertTrue(tree.add(34));
		assertTrue(tree.contains(34));
		assertEquals(4, tree.size());
		
		assertTrue(tree.add(22));
		assertTrue(tree.contains(22));
		assertEquals(5, tree.size());
	}
	
	@Test
	public void remove() throws Exception {
		assertFalse(tree.remove(1));
		tree.add(1);
		assertTrue(tree.remove(1));
		assertFalse(tree.contains(1));
		
		tree.add(44);
		tree.add(55);
		tree.add(33);
		tree.add(22);
		tree.add(11);
		assertEquals(5, tree.size());
		
		assertTrue(tree.remove(44));
		assertFalse(tree.contains(44));
		assertEquals(4, tree.size());
		
		assertTrue(tree.remove(55));
		assertFalse(tree.contains(55));
		assertEquals(3, tree.size());
		
		assertTrue(tree.remove(11));
		assertFalse(tree.contains(11));
		assertEquals(2, tree.size());
		
		tree.add(11);
		assertTrue(tree.remove(11));
		assertTrue(tree.contains(11)); //Duplicate element
		assertEquals(2, tree.size());
		
		assertTrue(tree.remove(11));
		assertFalse(tree.contains(11));
		assertEquals(1, tree.size());
		
		assertTrue(tree.remove(22));
		assertFalse(tree.contains(22));
		assertEquals(0, tree.size());
	}
	
	@Test
	public void clear() throws Exception {
		tree.add(1);
		tree.add(2);
		
		tree.clear();
		assertEquals(0, tree.size());
		assertTrue(tree.isEmpty());
		assertFalse(tree.contains(2));
	}
	
}
