package edu.oakland.OUSoft.BST;

public class cseBST<E extends Comparable<E>> {
	
	private Node<E> root;
	
	public boolean contains(E o) {
		return containsRecurse(root, o);
	}
	
	private boolean containsRecurse(Node<E> node, E e) {
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
	}
	
	public boolean remove(E e) {
	}
	
	public int size() {
	}
	
	public boolean isEmpty() {
	}
	
	public void clear() {
		root = new Node<>(null);
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
