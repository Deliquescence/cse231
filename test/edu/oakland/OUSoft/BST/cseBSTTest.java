package edu.oakland.OUSoft.BST;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

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
		tree.remove(1);
		assertEquals(1, tree.size());
		tree.remove(2);
		assertEquals(0, tree.size());
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
		assertFalse(tree.remove("an object"));
		
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
		
		assertTrue(tree.remove(33));
		assertFalse(tree.contains(33));
		assertEquals(1, tree.size());
		
		assertTrue(tree.remove(22));
		assertFalse(tree.contains(22));
		assertEquals(0, tree.size());
		
		//Test duplicate elements
		tree.add(22);
		tree.add(11);
		tree.add(44);
		tree.add(11);
		tree.add(22);
		assertEquals(5, tree.size());
		
		assertTrue(tree.remove(22));
		assertTrue(tree.contains(22));
		assertEquals(4, tree.size());
		
		assertTrue(tree.remove(44));
		assertFalse(tree.contains(44));
		assertEquals(3, tree.size());
		
		assertTrue(tree.remove(11));
		assertTrue(tree.contains(11));
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
	
	@Test
	public void iterator() throws Exception {
		tree.add(2);
		tree.add(3);
		tree.add(1);
		tree.add(4);
		Iterator<Integer> iterator = tree.iterator();
		
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected first element", (Object) 1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected second element", (Object) 2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected third element", (Object) 3, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("Iterator didn't give expected second element", (Object) 4, iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void toArray() throws Exception {
		tree.add(1);
		tree.add(2);
		tree.add(3);
		
		Object[] array = tree.toArray();
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		assertEquals(3, array[2]);
	}
	
	@Test
	public void toArray1() throws Exception {
		tree.add(11);
		tree.add(22);
		
		Integer[] array = tree.toArray(new Integer[0]);
		assertEquals((Object) 11, array[0]);
		assertEquals((Object) 22, array[1]);
		
		Integer[] array2 = new Integer[2];
		tree.toArray(array2);
		assertEquals((Object) 11, array2[0]);
		assertEquals((Object) 22, array2[1]);
		
		Integer[] array3 = new Integer[3];
		array3[2] = 33;
		tree.toArray(array3);
		assertEquals((Object) 11, array3[0]);
		assertEquals((Object) 22, array3[1]);
		assertNull(array3[2]);
	}
	
	@Test
	public void containsAll() throws Exception {
		tree.add(11);
		tree.add(22);
		tree.add(33);
		assertEquals(3, tree.size());
		
		Collection<Integer> testCollection1 = new ArrayList<>();
		Collection<Integer> testCollection2 = new ArrayList<>();
		Collection<Integer> testCollection3 = new ArrayList<>();
		
		testCollection1.add(22);
		
		testCollection2.add(11);
		testCollection2.add(22);
		testCollection2.add(33);
		
		assertTrue(tree.containsAll(testCollection1));
		assertTrue(tree.containsAll(testCollection2));
		assertTrue(tree.containsAll(testCollection3));
		
		tree.remove(33);
		assertFalse(tree.containsAll(testCollection2));
	}
	
	@Test
	public void addAll() throws Exception {
		Collection<Integer> testCollection1 = new ArrayList<>();
		
		testCollection1.add(11);
		testCollection1.add(22);
		testCollection1.add(33);
		
		assertTrue(tree.addAll(testCollection1));
		assertTrue(tree.containsAll(testCollection1));
	}
	
	@Test
	public void removeAll() throws Exception {
		Collection<Integer> testCollection1 = new ArrayList<>();
		
		testCollection1.add(1);
		testCollection1.add(2);
		testCollection1.add(3);
		testCollection1.add(2);
		testCollection1.add(2);
		
		assertFalse("Should not have changed", tree.removeAll(testCollection1));
		
		assertTrue(tree.addAll(testCollection1));
		assertTrue(tree.containsAll(testCollection1));
		
		testCollection1.add(4); //Not removed because not in tree, but removeAll should still return true
		assertTrue(tree.removeAll(testCollection1));
		assertFalse(tree.contains(1));
		assertFalse(tree.contains(2));
		assertFalse(tree.contains(3));
	}
	
	@Test
	public void retainAll() throws Exception {
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		
		Collection<Integer> testCollection1 = new ArrayList<>();
		testCollection1.add(1);
		testCollection1.add(2);
		testCollection1.add(3);
		testCollection1.add(4);
		
		assertFalse(tree.retainAll(testCollection1));
		
		testCollection1.remove(2);
		testCollection1.remove(4);
		
		assertTrue(tree.retainAll(testCollection1));
		assertTrue(tree.contains(1));
		assertTrue(tree.contains(3));
		assertEquals(2, tree.size());
	}
}
