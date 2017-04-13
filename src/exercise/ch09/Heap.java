//----------------------------------------------------------------------------
// Heap.java               by Dale/Joyce/Weems                       Chapter 9
//
// Defines all constructs for a heap.
// The dequeue method returns the largest element in the heap.
//----------------------------------------------------------------------------

package exercise.ch09;

import java.util.*;

public class Heap<T extends Comparable<T>> implements PriQueueInterface<T> {
	private ArrayList<T> elements;  // priority queue elements
	private int lastIndex;          // index of last element in priority queue
	private int maxIndex;           // index of last position in ArrayList
	
	public Heap(int maxSize) {
		elements = new ArrayList<T>(maxSize);
		lastIndex = -1;
		maxIndex = maxSize - 1;
	}
	
	// Returns true if this priority queue is empty; otherwise, returns false.
	public boolean isEmpty()
	{
		return (lastIndex == -1);
	}
	
	// Returns true if this priority queue is full; otherwise, returns false.
	public boolean isFull()
	{
		return (lastIndex == maxIndex);
	}
	
	// Current lastIndex position is empty.
	// Inserts element into the tree and ensures shape and order properties.
	private void reheapUp(T element)
	{
		int hole = lastIndex;
		while ((hole > 0)    // hole is not root and element > hole's parent
		       && (element.compareTo(elements.get((hole - 1) / 2)) > 0)) {
			// move hole's parent down and then move hole up
			elements.set(hole, elements.get((hole - 1) / 2));
			hole = (hole - 1) / 2;
		}
		elements.set(hole, element);  // place element into final hole
	}
	
	// Throws PriQOverflowException if this priority queue is full;
	// otherwise, adds element to this priority queue.
	public void enqueue(T element) throws PriQOverflowException
	{
		if (lastIndex == maxIndex) {
			throw new PriQOverflowException("Priority queue is full");
		} else {
			lastIndex++;
			elements.add(lastIndex, element);
			reheapUp(element);
		}
	}
	
	// If either child of hole is larger than element, return the index
	// of the larger child; otherwise, return the index of hole.
	private int newHole(int hole, T element)
	{
		int left = (hole * 2) + 1;
		int right = (hole * 2) + 2;
		
		if (left > lastIndex)
		// hole has no children
		{
			return hole;
		} else if (left == lastIndex)
		// hole has left child only
		{
			if (element.compareTo(elements.get(left)) < 0)
			// element < left child
			{
				return left;
			} else
			// element >= left child
			{
				return hole;
			}
		} else
			// hole has two children
			if (elements.get(left).compareTo(elements.get(right)) < 0)
			// left child < right child
			{
				if (elements.get(right).compareTo(element) <= 0)
				// right child <= element
				{
					return hole;
				} else
				// element < right child
				{
					return right;
				}
			} else
				// left child >= right child
				if (elements.get(left).compareTo(element) <= 0)
				// left child <= element
				{
					return hole;
				} else
				// element < left child
				{
					return left;
				}
	}
	
	// Current root position is "empty";
	// Inserts element into the tree and ensures shape and order properties.
	private void reheapDown(T element)
	{
		int hole = 0;      // current index of hole
		int newhole;       // index where hole should move to
		
		newhole = newHole(hole, element);   // find next hole
		while (newhole != hole) {
			elements.set(hole, elements.get(newhole));  // move element up
			hole = newhole;                            // move hole down
			newhole = newHole(hole, element);          // find next hole
		}
		elements.set(hole, element);           // fill in the final hole
	}
	
	// Throws PriQUnderflowException if this priority queue is empty;
	// otherwise, removes element with highest priority from this
	// priority queue and returns it.
	public T dequeue() throws PriQUnderflowException
	{
		T hold;      // element to be dequeued and returned
		T toMove;    // element to move down heap
		
		if (lastIndex == -1) {
			throw new PriQUnderflowException("Priority queue is empty");
		} else {
			hold = elements.get(0);              // remember element to be returned
			toMove = elements.remove(lastIndex); // element to reheap down
			lastIndex--;                         // decrease priority queue size
			if (lastIndex != -1) {
				reheapDown(toMove);               // restore heap properties
			}
			return hold;                         // return largest element
		}
	}
	
	// Returns a string of all the heap elements.
	public String toString()
	{
		String theHeap = new String("the heap is:\n");
		for (int index = 0; index <= lastIndex; index++) {
			theHeap = theHeap + index + ". " + elements.get(index) + "\n";
		}
		return theHeap;
	}
}