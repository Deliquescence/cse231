package edu.oakland.OUSoft.linkedList;

import java.util.*;

public class cseArrayLinkedList<E> implements List<E> {
	
	private Node<E>[] data;
	
	private int dataIndex;
	private int emptyIndex;
	
	private final int maxElements;
	
	/**
	 * Create a new cseArrayLinkedList
	 *
	 * @param maxElements the maximum number of elements this list can hold
	 */
	public cseArrayLinkedList(int maxElements) {
		this.maxElements = maxElements;
		data = (Node<E>[]) new Node[maxElements];
		
		for (int i = 0; i < maxElements - 1; i++) {
			data[i] = new Node<E>();
			data[i].nextIndex = i + 1;
		}
		data[maxElements - 1] = new Node<E>();
		data[maxElements - 1].nextIndex = -1;
		
		emptyIndex = 0;
		dataIndex = -1;
	}
	
	
	/**
	 * Returns the number of elements in this list.  If this list contains
	 * more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		int count = 0;
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}
	
	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return dataIndex == -1;
	}
	
	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 * More formally, returns <tt>true</tt> if and only if this list contains
	 * at least one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 * @throws ClassCastException   if the type of the specified element
	 *                              is incompatible with this list
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified element is null and this
	 *                              list does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public boolean contains(Object o) {
		for (E e : this) {
			if (e.equals(o)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 *
	 * @return an iterator over the elements in this list in proper sequence
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {
			Node<E> current = (dataIndex == -1) ? null : data[dataIndex];
			
			@Override
			public boolean hasNext() {
				return !(current == null);
			}
			
			@Override
			public E next() {
				E previous = current.element;
				if (current.nextIndex == -1) {
					current = null;
				} else {
					current = data[current.nextIndex];
				}
				
				return previous;
			}
		};
		
		return iterator;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 * <p>
	 * <p>The returned array will be "safe" in that no references to it are
	 * maintained by this list.  (In other words, this method must
	 * allocate a new array even if this list is backed by an array).
	 * The caller is thus free to modify the returned array.
	 * <p>
	 * <p>This method acts as bridge between array-based and collection-based
	 * APIs.
	 *
	 * @return an array containing all of the elements in this list in proper
	 * sequence
	 * @see Arrays#asList(Object[])
	 */
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Returns an array containing all of the elements in this list in
	 * proper sequence (from first to last element); the runtime type of
	 * the returned array is that of the specified array.  If the list fits
	 * in the specified array, it is returned therein.  Otherwise, a new
	 * array is allocated with the runtime type of the specified array and
	 * the size of this list.
	 * <p>
	 * <p>If the list fits in the specified array with room to spare (i.e.,
	 * the array has more elements than the list), the element in the array
	 * immediately following the end of the list is set to <tt>null</tt>.
	 * (This is useful in determining the length of the list <i>only</i> if
	 * the caller knows that the list does not contain any null elements.)
	 * <p>
	 * <p>Like the {@link #toArray()} method, this method acts as bridge between
	 * array-based and collection-based APIs.  Further, this method allows
	 * precise control over the runtime type of the output array, and may,
	 * under certain circumstances, be used to save allocation costs.
	 * <p>
	 * <p>Suppose <tt>x</tt> is a list known to contain only strings.
	 * The following code can be used to dump the list into a newly
	 * allocated array of <tt>String</tt>:
	 * <p>
	 * <pre>{@code
	 *     String[] y = x.toArray(new String[0]);
	 * }</pre>
	 * <p>
	 * Note that <tt>toArray(new Object[0])</tt> is identical in function to
	 * <tt>toArray()</tt>.
	 *
	 * @param a the array into which the elements of this list are to
	 *          be stored, if it is big enough; otherwise, a new array of the
	 *          same runtime type is allocated for this purpose.
	 * @return an array containing the elements of this list
	 * @throws ArrayStoreException  if the runtime type of the specified array
	 *                              is not a supertype of the runtime type of every element in
	 *                              this list
	 * @throws NullPointerException if the specified array is null
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Appends the specified element to the end of this list (optional
	 * operation).
	 * <p>
	 * <p>Lists that support this operation may place limitations on what
	 * elements may be added to this list.  In particular, some
	 * lists will refuse to add null elements, and others will impose
	 * restrictions on the type of elements that may be added.  List
	 * classes should clearly specify in their documentation any restrictions
	 * on what elements may be added.
	 *
	 * @param e element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 * @throws UnsupportedOperationException if the <tt>add</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this list
	 * @throws NullPointerException          if the specified element is null and this
	 *                                       list does not permit null elements
	 * @throws IllegalArgumentException      if some property of this element
	 *                                       prevents it from being added to this list
	 */
	@Override
	public boolean add(E e) {
		if (dataIndex == -1) {
			dataIndex = emptyIndex;
			emptyIndex = data[emptyIndex].nextIndex;
			
			data[dataIndex].nextIndex = -1;
			data[dataIndex].element = e;
			return true;
		} else {
			Node<E> node = data[dataIndex];
			while (node.nextIndex != -1) {
				node = data[node.nextIndex];
			}
			node.nextIndex = emptyIndex;
			emptyIndex = (emptyIndex == -1) ? -1 : data[emptyIndex].nextIndex;
			
			data[node.nextIndex].element = e;
			data[node.nextIndex].nextIndex = -1;
			return true;
		}
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list,
	 * if it is present (optional operation).  If this list does not contain
	 * the element, it is unchanged.  More formally, removes the element with
	 * the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
	 * (if such an element exists).  Returns <tt>true</tt> if this list
	 * contained the specified element (or equivalently, if this list changed
	 * as a result of the call).
	 *
	 * @param o element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 * @throws ClassCastException            if the type of the specified element
	 *                                       is incompatible with this list
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if the specified element is null and this
	 *                                       list does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws UnsupportedOperationException if the <tt>remove</tt> operation
	 *                                       is not supported by this list
	 */
	@Override
	public boolean remove(Object o) {
		if (o == null || dataIndex == -1) {
			return false;
		}
		
		if (data[dataIndex].element.equals(o)) { //First element case
			int oldEmptyIndex = emptyIndex;
			emptyIndex = dataIndex; //Set empty index to new free spot
			dataIndex = data[dataIndex].nextIndex; //Update data index to what the removed pointed to
			data[emptyIndex].nextIndex = oldEmptyIndex; //Update the new empty spot so it points to the old chain
			return true;
		}
		
		Node<E> previous = data[dataIndex];
		boolean found = false;
		
		while (previous.nextIndex != -1) {
			if (data[previous.nextIndex].element.equals(o)) {
				found = true;
				break;
			}
			previous = data[previous.nextIndex];
		}
		
		if (!found) {
			return false;
		}
		int oldEmptyIndex = emptyIndex;
		emptyIndex = previous.nextIndex; //Set empty index to new free spot
		previous.nextIndex = data[previous.nextIndex].nextIndex; //Update node previous to the removed to point to new next node
		data[emptyIndex].nextIndex = oldEmptyIndex; //Update the new empty spot so it points to the old chain
		return true;
	}
	
	/**
	 * Returns <tt>true</tt> if this list contains all of the elements of the
	 * specified collection.
	 *
	 * @param c collection to be checked for containment in this list
	 * @return <tt>true</tt> if this list contains all of the elements of the
	 * specified collection
	 * @throws ClassCastException   if the types of one or more elements
	 *                              in the specified collection are incompatible with this
	 *                              list
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified collection contains one
	 *                              or more null elements and this list does not permit null
	 *                              elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
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
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator (optional operation).  The behavior of this
	 * operation is undefined if the specified collection is modified while
	 * the operation is in progress.  (Note that this will occur if the
	 * specified collection is this list, and it's nonempty.)
	 *
	 * @param c collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of an element of the specified
	 *                                       collection prevents it from being added to this list
	 * @throws NullPointerException          if the specified collection contains one
	 *                                       or more null elements and this list does not permit null
	 *                                       elements, or if the specified collection is null
	 * @throws IllegalArgumentException      if some property of an element of the
	 *                                       specified collection prevents it from being added to this list
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
	 * Inserts all of the elements in the specified collection into this
	 * list at the specified position (optional operation).  Shifts the
	 * element currently at that position (if any) and any subsequent
	 * elements to the right (increases their indices).  The new elements
	 * will appear in this list in the order that they are returned by the
	 * specified collection's iterator.  The behavior of this operation is
	 * undefined if the specified collection is modified while the
	 * operation is in progress.  (Note that this will occur if the specified
	 * collection is this list, and it's nonempty.)
	 *
	 * @param index index at which to insert the first element from the
	 *              specified collection
	 * @param c     collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of an element of the specified
	 *                                       collection prevents it from being added to this list
	 * @throws NullPointerException          if the specified collection contains one
	 *                                       or more null elements and this list does not permit null
	 *                                       elements, or if the specified collection is null
	 * @throws IllegalArgumentException      if some property of an element of the
	 *                                       specified collection prevents it from being added to this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified collection (optional operation).
	 *
	 * @param c collection containing elements to be removed from this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of an element of this list
	 *                                       is incompatible with the specified collection
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if this list contains a null element and the
	 *                                       specified collection does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
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
	 * Retains only the elements in this list that are contained in the
	 * specified collection (optional operation).  In other words, removes
	 * from this list all of its elements that are not contained in the
	 * specified collection.
	 *
	 * @param c collection containing elements to be retained in this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of an element of this list
	 *                                       is incompatible with the specified collection
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException          if this list contains a null element and the
	 *                                       specified collection does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
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
	
	/**
	 * Removes all of the elements from this list (optional operation).
	 * The list will be empty after this call returns.
	 *
	 * @throws UnsupportedOperationException if the <tt>clear</tt> operation
	 *                                       is not supported by this list
	 */
	@Override
	public void clear() {
		for (int i = 0; i < maxElements - 1; i++) {
			data[i] = new Node<E>();
			data[i].nextIndex = i + 1;
		}
		data[maxElements - 1] = new Node<E>();
		data[maxElements - 1].nextIndex = -1;
		
		emptyIndex = 0;
		dataIndex = -1;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> node = data[dataIndex];
		for (int i = 0; i < index; i++) {
			node = data[node.nextIndex];
		}
		return node.element;
	}
	
	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element (optional operation).
	 *
	 * @param index   index of the element to replace
	 * @param element element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException if the <tt>set</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this list
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this list does not permit null elements
	 * @throws IllegalArgumentException      if some property of the specified
	 *                                       element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> node = data[dataIndex];
		for (int i = 0; i < index; i++) {
			node = data[node.nextIndex];
		}
		E old = node.element;
		node.element = element;
		return old;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation).  Shifts the element currently at that position
	 * (if any) and any subsequent elements to the right (adds one to their
	 * indices).
	 *
	 * @param index   index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws UnsupportedOperationException if the <tt>add</tt> operation
	 *                                       is not supported by this list
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this list
	 * @throws NullPointerException          if the specified element is null and
	 *                                       this list does not permit null elements
	 * @throws IllegalArgumentException      if some property of the specified
	 *                                       element prevents it from being added to this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> node = data[dataIndex];
		for (int i = 0; i < index - 1; i++) {
			node = data[node.nextIndex];
		}
		Node<E> newEelement = data[emptyIndex];
		int newIndex = emptyIndex;
		emptyIndex = data[emptyIndex].nextIndex;
		
		newEelement.element = element;
		newEelement.nextIndex = node.nextIndex;
		node.nextIndex = newIndex;
	}
	
	/**
	 * Removes the element at the specified position in this list (optional
	 * operation).  Shifts any subsequent elements to the left (subtracts one
	 * from their indices).  Returns the element that was removed from the
	 * list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException if the <tt>remove</tt> operation
	 *                                       is not supported by this list
	 * @throws IndexOutOfBoundsException     if the index is out of range
	 *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E e = this.get(index);
		this.remove(e);
		return e;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 * @throws ClassCastException   if the type of the specified element
	 *                              is incompatible with this list
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified element is null and this
	 *                              list does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public int indexOf(Object o) {
		int i = -1;
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().equals(o)) {
				return ++i;
			}
			i++;
		}
		return -1;
	}
	
	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the last occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 * @throws ClassCastException   if the type of the specified element
	 *                              is incompatible with this list
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified element is null and this
	 *                              list does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public int lastIndexOf(Object o) {
		int i = -1;
		int found = -1;
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			if (it.next().equals(o)) {
				found = ++i;
			} else {
				i++;
			}
		}
		return found;
	}
	
	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence).
	 *
	 * @return a list iterator over the elements in this list (in proper
	 * sequence)
	 */
	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 * The specified index indicates the first element that would be
	 * returned by an initial call to {@link ListIterator#next next}.
	 * An initial call to {@link ListIterator#previous previous} would
	 * return the element with the specified index minus one.
	 *
	 * @param index index of the first element to be returned from the
	 *              list iterator (by a call to {@link ListIterator#next next})
	 * @return a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *                                   ({@code index < 0 || index > size()})
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Returns a view of the portion of this list between the specified
	 * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
	 * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
	 * empty.)  The returned list is backed by this list, so non-structural
	 * changes in the returned list are reflected in this list, and vice-versa.
	 * The returned list supports all of the optional list operations supported
	 * by this list.<p>
	 * <p>
	 * This method eliminates the need for explicit range operations (of
	 * the sort that commonly exist for arrays).  Any operation that expects
	 * a list can be used as a range operation by passing a subList view
	 * instead of a whole list.  For example, the following idiom
	 * removes a range of elements from a list:
	 * <pre>{@code
	 *      list.subList(from, to).clear();
	 * }</pre>
	 * Similar idioms may be constructed for <tt>indexOf</tt> and
	 * <tt>lastIndexOf</tt>, and all of the algorithms in the
	 * <tt>Collections</tt> class can be applied to a subList.<p>
	 * <p>
	 * The semantics of the list returned by this method become undefined if
	 * the backing list (i.e., this list) is <i>structurally modified</i> in
	 * any way other than via the returned list.  (Structural modifications are
	 * those that change the size of this list, or otherwise perturb it in such
	 * a fashion that iterations in progress may yield incorrect results.)
	 *
	 * @param fromIndex low endpoint (inclusive) of the subList
	 * @param toIndex   high endpoint (exclusive) of the subList
	 * @return a view of the specified range within this list
	 * @throws IndexOutOfBoundsException for an illegal endpoint index value
	 *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
	 *                                   fromIndex &gt; toIndex</tt>)
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	protected class Node<E> {
		E element;
		int nextIndex;
	}
}
