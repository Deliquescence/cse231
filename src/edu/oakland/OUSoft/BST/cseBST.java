package edu.oakland.OUSoft.BST;

import java.util.Iterator;

public class cseBST<E extends Comparable<E>> implements Iterable<E> {
	
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
	
	public boolean remove(E e) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public int size() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public boolean isEmpty() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public void clear() {
		root = new Node<>(null);
	}
	
	/**
	 * Returns an iterator over elements using Inorder method
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node<E> node = root;
			
			@Override
			public boolean hasNext() {
				return false;
			}
			
			@Override
			public E next() {
				return null;
			}
		};
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
