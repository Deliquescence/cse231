package edu.oakland.OUSoft.linkedList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class cseSortedArrayLinkedListTest {
	
	private cseSortedArrayLinkedList<Integer> list;
	
	@Before
	public void setUp() throws Exception {
		list = new cseSortedArrayLinkedList<>(6);
	}
	
	@Test
	public void add() throws Exception {
		assertTrue(list.add(10));
		assertTrue(list.contains(10));
		assertEquals((Object) 10, list.get(0));
		assertEquals(1, list.size());
		
		assertTrue(list.add(5));
		assertTrue(list.contains(5));
		assertEquals((Object) 5, list.get(0));
		assertEquals((Object) 10, list.get(1));
		assertEquals(2, list.size());
		
		assertTrue(list.add(1));
		assertTrue(list.contains(1));
		assertEquals((Object) 1, list.get(0));
		assertEquals((Object) 5, list.get(1));
		assertEquals((Object) 10, list.get(2));
		assertEquals(3, list.size());
		
		assertTrue(list.add(20));
		assertEquals((Object) 1, list.get(0));
		assertEquals((Object) 5, list.get(1));
		assertEquals((Object) 10, list.get(2));
		assertEquals((Object) 20, list.get(3));
		assertEquals(4, list.size());
		
		assertTrue(list.add(1));
		assertEquals((Object) 1, list.get(0));
		assertEquals((Object) 1, list.get(1));
		assertEquals((Object) 5, list.get(2));
		assertEquals((Object) 10, list.get(3));
		assertEquals((Object) 20, list.get(4));
		assertEquals(5, list.size());
		
		assertTrue(list.add(7));
		assertEquals((Object) 1, list.get(0));
		assertEquals((Object) 1, list.get(1));
		assertEquals((Object) 5, list.get(2));
		assertEquals((Object) 7, list.get(3));
		assertEquals((Object) 10, list.get(4));
		assertEquals((Object) 20, list.get(5));
		assertEquals(6, list.size());
	}
}
