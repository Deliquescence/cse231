package edu.oakland.OUSoft.linkedList;

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
