package edu.oakland.OUSoft.BST;

import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

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
		return sizeRecurse(root);
	}
	
	private int sizeRecurse(Node<E> node) {
		if (node == null) {
			return 0;
		} else {
			return sizeRecurse(node.left) + sizeRecurse(node.right) + 1;
		}
	}
	
	public boolean isEmpty() {
		return root == null;
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
		Stack<E> stack = new Stack<E>();
		nodeToStack(stack, root);
		
		return stack.iterator();
	}
	
	private void nodeToStack(Stack<E> stack, Node<E> node) {
		if (node == null) {
			return;
		}
		
		nodeToStack(stack, node.left);
		stack.add(node.element);
		nodeToStack(stack, node.right);
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
