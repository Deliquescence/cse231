package edu.oakland.OUSoft.linkedList;


public class cseSortedArrayLinkedList<E extends Comparable<E>> extends cseArrayLinkedList<E> {
	
	public cseSortedArrayLinkedList(int maxSize) {
		super(maxSize);
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
		return super.add(e);
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
		throw new UnsupportedOperationException("Cannot set in a sorted list");
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
		super.add(index, element);
	}
}
