package edu.oakland.OUSoft.linkedList;

/**
 * A class that implements this can be used as a node in a {@link cseLinkedList}.
 *
 * @param <E> Must be the same as the implementing class
 */
public interface LLNode<E> {
	
	/**
	 * Get the link to the next object
	 *
	 * @return The next object in the list
	 */
	public E getLink();
	
	/**
	 * Set the link to the next object
	 *
	 * @param Link The next object in the list
	 */
	public void setLink(E Link);
}
