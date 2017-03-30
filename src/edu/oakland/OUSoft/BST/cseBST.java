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
	
	public void remove(E e) {
		root = removeRecurse(root, e);
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
