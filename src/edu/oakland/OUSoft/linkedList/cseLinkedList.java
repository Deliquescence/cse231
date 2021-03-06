package edu.oakland.OUSoft.linkedList;

import java.io.Serializable;
import java.util.*;

public class cseLinkedList<E extends LLNode> implements List<E>, Serializable {
	
	private E indexZero;
	
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
		return this.indexZero == null;
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
			E current = cseLinkedList.this.indexZero;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}
			
			@Override
			public E next() {
				E previous = current;
				current = (E) current.getLink();
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
		Object[] array = new Object[this.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = this.get(i);
		}
		return array;
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
	 * Appends the specified element to the end of this list.
	 * No null or duplicate elements are allowed.
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
		if (e == null) {
			throw new NullPointerException("Cannot add a null element");
		}
		if (this.contains(e)) {
			throw new IllegalArgumentException("Cannot add a duplicate element");
		}
		e.setLink(null);
		if (this.indexZero == null) {
			this.indexZero = e;
			return true;
		}
		E element = this.indexZero;
		while (element.getLink() != null) {
			element = (E) element.getLink();
		}
		element.setLink(e);
		return true;
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
		if (o == null) {
			return false;
		}
		
		E previous = null;
		for (E e : this) {
			if (e.equals(o)) {
				if (this.indexOf(e) == 0) {
					this.indexZero = (E) this.indexZero.getLink();
					return true;
				}
				previous.setLink(e.getLink());
				return true;
			}
			previous = e;
		}
		return false;
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
		if (c == null || c.contains(null)) {
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
		if (c == null || c.contains(null)) {
			throw new NullPointerException();
		}
		
		Iterator it = c.iterator();
		while (it.hasNext()) {
			this.add((E) it.next());
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
		if (c == null || c.contains(null)) {
			throw new NullPointerException();
		}
		
		Iterator it = c.iterator();
		while (it.hasNext()) {
			this.add(index++, (E) it.next());
		}
		return true;
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
		if (c == null || c.contains(null)) {
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
		if (c == null || c.contains(null)) {
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
		this.indexZero = null;
	}
	
	/**
	 * Compares the specified object with this list for equality.  Returns
	 * <tt>true</tt> if and only if the specified object is also a list, both
	 * lists have the same size, and all corresponding pairs of elements in
	 * the two lists are <i>equal</i>.  (Two elements <tt>e1</tt> and
	 * <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null :
	 * e1.equals(e2))</tt>.)  In other words, two lists are defined to be
	 * equal if they contain the same elements in the same order.  This
	 * definition ensures that the equals method works properly across
	 * different implementations of the <tt>List</tt> interface.
	 *
	 * @param o the object to be compared for equality with this list
	 * @return <tt>true</tt> if the specified object is equal to this list
	 */
	@Override
	public boolean equals(Object o) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	/**
	 * Returns the hash code value for this list.  The hash code of a list
	 * is defined to be the result of the following calculation:
	 * <pre>{@code
	 *     int hashCode = 1;
	 *     for (E e : list)
	 *         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
	 * }</pre>
	 * This ensures that <tt>list1.equals(list2)</tt> implies that
	 * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
	 * <tt>list1</tt> and <tt>list2</tt>, as required by the general
	 * contract of {@link Object#hashCode}.
	 *
	 * @return the hash code value for this list
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("Not implemented yet.");
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
		
		E element = this.indexZero;
		for (int i = 0; i < index; i++) {
			element = (E) element.getLink();
		}
		return element;
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
		if (this.contains(element)) {
			throw new IllegalArgumentException("Cannot add a duplicate element");
		}
		
		E elem = this.indexZero;
		for (int i = 0; i < index - 1; i++) {
			elem = (E) elem.getLink();
		}
		
		E old = (E) elem.getLink();
		element.setLink(old.getLink());
		elem.setLink(element);
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
		if (this.contains(element)) {
			throw new IllegalArgumentException("Cannot add a duplicate element");
		}
		
		E elem = this.indexZero;
		for (int i = 0; i < index - 1; i++) {
			elem = (E) elem.getLink();
		}
		
		element.setLink(elem.getLink());
		elem.setLink(element);
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
		
		if (index == 0) {
			E old = this.indexZero;
			this.indexZero = (E) this.indexZero.getLink();
			return old;
		}
		
		E elem = this.indexZero;
		for (int i = 0; i < index - 1; i++) {
			elem = (E) elem.getLink();
		}
		
		E old = (E) elem.getLink();
		elem.setLink(old.getLink());
		return old;
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
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).equals(o)) {
				return i;
			}
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
		return indexOf(o); //Because there are no duplicate elements
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
}
