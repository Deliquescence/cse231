package edu.oakland.OUSoft.BST;

import java.util.*;

public class cseBST<E extends Comparable<E>> implements Collection<E> {
	
	private Node<E> root;
	
	public boolean contains(E o) {
		return containsRecurse(root, o);
	}
	
	private boolean containsRecurse(Node<E> node, E e) {
		if (node == null || e == null) {
			return false;
		}
		if (node.element.equals(e)) {
			return true;
		} else {
			if (e.compareTo(node.element) <= 0) {
				return containsRecurse(node.left, e);
			} else {
				return containsRecurse(node.right, e);
			}
		}
	}
	
	public boolean add(E e) {
		root = addRecurse(root, e);
		return true;
	}
	
	private Node<E> addRecurse(Node<E> node, E e) {
		if (node == null) {
			return new Node<>(e);
		} else if (e.compareTo(node.element) <= 0) {
			node.left = addRecurse(node.left, e);
		} else {
			node.right = addRecurse(node.right, e);
		}
		return node;
	}
	
	/**
	 * Remove an object from the tree.
	 *
	 * @param o the object to remove
	 */
	public boolean remove(Object o) {
		boolean dirty = contains(o);
		root = removeRecurse(root, (E) o);
		return dirty;
	}
	
	private Node<E> removeRecurse(Node<E> node, E e) {
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.element) < 0) {
			node.left = removeRecurse(node.left, e);
		} else if (e.compareTo(node.element) > 0) {
			node.right = removeRecurse(node.right, e);
		} else {
			node = removeNode(node);
		}
		return node;
	}
	
	private Node<E> removeNode(Node<E> node) {
		if (node.left == null) {
			return node.right;
		} else if (node.right == null) {
			return node.left;
		} else {
			Node<E> predecessor = getPredecessor(node.left);
			node.element = predecessor.element;
			node.left = removeRecurse(node.left, node.element);
			return node;
		}
	}
	
	private Node<E> getPredecessor(Node<E> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public int size() {
		return sizeRecurse(root);
	}
	
	private int sizeRecurse(Node<E> node) {
		if (node == null) {
			return 0;
		}
		return sizeRecurse(node.left) + sizeRecurse(node.right) + 1;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Returns <tt>true</tt> if this collection contains the specified element.
	 * More formally, returns <tt>true</tt> if and only if this collection
	 * contains at least one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o element whose presence in this collection is to be tested
	 * @return <tt>true</tt> if this collection contains the specified
	 * element
	 * @throws ClassCastException   if the type of the specified element
	 *                              is incompatible with this collection
	 *                              (<a href="#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified element is null and this
	 *                              collection does not permit null elements
	 *                              (<a href="#optional-restrictions">optional</a>)
	 */
	@Override
	public boolean contains(Object o) {
		return contains((E) o);
	}
	
	/**
	 * Returns an iterator over the elements in this collection.  There are no
	 * guarantees concerning the order in which the elements are returned
	 * (unless this collection is an instance of some class that provides a
	 * guarantee).
	 *
	 * @return an <tt>Iterator</tt> over the elements in this collection
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Stack<Node<E>> stack = new Stack<>();
			
			{
				Node<E> node = root;
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
			
			@Override
			public boolean hasNext() {
				return !stack.isEmpty();
			}
			
			@Override
			public E next() {
				Node<E> node = stack.pop();
				E element = node.element; //Get element for the current node to return later
				if (node.right != null) { //Right subtree next
					node = node.right;
					while (node != null) { //Go down left tree
						stack.push(node);
						node = node.left;
					}
				}
				return element;
			}
		};
	}
	
	/**
	 * Returns an array containing all of the elements in this collection.
	 * If this collection makes any guarantees as to what order its elements
	 * are returned by its iterator, this method must return the elements in
	 * the same order.
	 * <p>
	 * <p>The returned array will be "safe" in that no references to it are
	 * maintained by this collection.  (In other words, this method must
	 * allocate a new array even if this collection is backed by an array).
	 * The caller is thus free to modify the returned array.
	 * <p>
	 * <p>This method acts as bridge between array-based and collection-based
	 * APIs.
	 *
	 * @return an array containing all of the elements in this collection
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		int i = 0;
		for (E e : this) {
			array[i++] = e;
		}
		return array;
	}
	
	/**
	 * Returns an array containing all of the elements in this collection;
	 * the runtime type of the returned array is that of the specified array.
	 * If the collection fits in the specified array, it is returned therein.
	 * Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this collection.
	 * <p>
	 * <p>If this collection fits in the specified array with room to spare
	 * (i.e., the array has more elements than this collection), the element
	 * in the array immediately following the end of the collection is set to
	 * <tt>null</tt>.  (This is useful in determining the length of this
	 * collection <i>only</i> if the caller knows that this collection does
	 * not contain any <tt>null</tt> elements.)
	 * <p>
	 * <p>If this collection makes any guarantees as to what order its elements
	 * are returned by its iterator, this method must return the elements in
	 * the same order.
	 * <p>
	 * <p>Like the {@link #toArray()} method, this method acts as bridge between
	 * array-based and collection-based APIs.  Further, this method allows
	 * precise control over the runtime type of the output array, and may,
	 * under certain circumstances, be used to save allocation costs.
	 * <p>
	 * <p>Suppose <tt>x</tt> is a collection known to contain only strings.
	 * The following code can be used to dump the collection into a newly
	 * allocated array of <tt>String</tt>:
	 * <p>
	 * <pre>
	 *     String[] y = x.toArray(new String[0]);</pre>
	 * <p>
	 * Note that <tt>toArray(new Object[0])</tt> is identical in function to
	 * <tt>toArray()</tt>.
	 *
	 * @param a the array into which the elements of this collection are to be
	 *          stored, if it is big enough; otherwise, a new array of the same
	 *          runtime type is allocated for this purpose.
	 * @return an array containing all of the elements in this collection
	 * @throws ArrayStoreException  if the runtime type of the specified array
	 *                              is not a supertype of the runtime type of every element in
	 *                              this collection
	 * @throws NullPointerException if the specified array is null
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		//Stolen from ArrayList's implementation
		Object[] elementData = this.toArray();
		if (a.length < this.size()) {
			return (T[]) Arrays.copyOf(elementData, this.size(), a.getClass());
		}
		System.arraycopy(elementData, 0, a, 0, this.size());
		if (a.length > this.size()) {
			a[this.size()] = null;
		}
		return a;
	}
	
	/**
	 * Returns <tt>true</tt> if this collection contains all of the elements
	 * in the specified collection.
	 *
	 * @param c collection to be checked for containment in this collection
	 * @return <tt>true</tt> if this collection contains all of the elements
	 * in the specified collection
	 * @throws ClassCastException   if the types of one or more elements
	 *                              in the specified collection are incompatible with this
	 *                              collection
	 *                              (<a href="#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified collection contains one
	 *                              or more null elements and this collection does not permit null
	 *                              elements
	 *                              (<a href="#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null.
	 * @see #contains(Object)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException();
		}
		
		for (Object item : c) {
			if (!this.contains(item)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Adds all of the elements in the specified collection to this collection
	 * (optional operation).  The behavior of this operation is undefined if
	 * the specified collection is modified while the operation is in progress.
	 * (This implies that the behavior of this call is undefined if the
	 * specified collection is this collection, and this collection is
	 * nonempty.)
	 *
	 * @param c collection containing elements to be added to this collection
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	 *                                       is not supported by this collection
	 * @throws ClassCastException            if the class of an element of the specified
	 *                                       collection prevents it from being added to this collection
	 * @throws NullPointerException          if the specified collection contains a
	 *                                       null element and this collection does not permit null elements,
	 *                                       or if the specified collection is null
	 * @throws IllegalArgumentException      if some property of an element of the
	 *                                       specified collection prevents it from being added to this
	 *                                       collection
	 * @throws IllegalStateException         if not all the elements can be added at
	 *                                       this time due to insertion restrictions
	 * @see #add(Object)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (c == null) {
			throw new NullPointerException();
		}
		
		for (Object aC : c) {
			this.add((E) aC);
		}
		return true;
	}
	
	/**
	 * Removes all of this collection's elements that are also contained in the
	 * specified collection (optional operation).  After this call returns,
	 * this collection will contain no elements in common with the specified
	 * collection.
	 *
	 * @param c collection containing elements to be removed from this collection
	 * @return <tt>true</tt> if this collection changed as a result of the
	 * call
	 * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
	 *                                       is not supported by this collection
	 * @throws ClassCastException            if the types of one or more elements
	 *                                       in this collection are incompatible with the specified
	 *                                       collection
	 *                                       (<a href="#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if this collection contains one or more
	 *                                       null elements and the specified collection does not support
	 *                                       null elements
	 *                                       (<a href="#optional-restrictions">optional</a>),
	 *                                       or if the specified collection is null
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException();
		}
		boolean dirty = false;
		
		for (Object item : c) {
			if (this.remove(item)) {
				dirty = true;
			}
		}
		return dirty;
	}
	
	/**
	 * Retains only the elements in this collection that are contained in the
	 * specified collection (optional operation).  In other words, removes from
	 * this collection all of its elements that are not contained in the
	 * specified collection.
	 *
	 * @param c collection containing elements to be retained in this collection
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
	 *                                       is not supported by this collection
	 * @throws ClassCastException            if the types of one or more elements
	 *                                       in this collection are incompatible with the specified
	 *                                       collection
	 *                                       (<a href="#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if this collection contains one or more
	 *                                       null elements and the specified collection does not permit null
	 *                                       elements
	 *                                       (<a href="#optional-restrictions">optional</a>),
	 *                                       or if the specified collection is null
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException();
		}
		boolean dirty = false;
		
		for (E element : this) {
			if (!c.contains(element)) {
				if (this.remove(element)) {
					dirty = true;
				}
			}
		}
		
		return dirty;
	}
	
	public void clear() {
		root = null;
	}
	
	private class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		
		public Node(E e) {
			this.element = e;
		}
	}
	
}
