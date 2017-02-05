package exercise.ch03;

import exercise.ch03.stacks.ArrayStack;

public class number30<T> {
	
	ArrayStack<T> myStack = new ArrayStack<>();
	
	public static void main(String args[]){
		number30<String> num30 = new number30<>();
		num30.myStack.push("0");
		num30.myStack.push("1");
		num30.myStack.push("2");
		num30.myStack.push("3");
		num30.myStack.push("4");
		
		num30.partD();
	}
	
	/**
	 * Set secondElement to the second element from the top of my Stack , leaving myStack without its original top two elements.
	 */
	public T partA() {
		myStack.pop();
		T secondElement = myStack.top();
		myStack.pop();
		
		return secondElement;
	}
	
	/**
	 * Set bottom equal to the bottom element in myStack , leaving myStack empty.
	 */
	public T partB() {
		T bottom = null;
		
		while(!myStack.isEmpty()){
			bottom = myStack.top();
			myStack.pop();
		}
		
		return bottom;
	}
	
	/**
	 * Set bottom equal to the bottom element in myStack , leaving myStack unchanged.
	 */
	public T partC() {
		T bottom = getBottom(myStack);
		
		return bottom;
	}
	
	/**
	 * Print out the contents of myStack , leaving my Stack unchanged.
	 */
	public void partD() {
		System.out.println("Your stack, top to bottom: ");
		printStack(myStack);
	}
	
	
	public T getBottom(ArrayStack<T> stack){
		
		T top = stack.top();
		stack.pop();
		if(stack.isEmpty()){ //"top" is the bottom element
			stack.push(top);
			return top;
		} else {
			T bottomRecurse = getBottom(stack);
			
			//Replace everything
			stack.push(top);
			
			return bottomRecurse;
		}
	}
	
	public T printStack(ArrayStack<T> stack){
		
		T top = stack.top();
		System.out.println(top);
		stack.pop();
		if(stack.isEmpty()){ //"top" is the bottom element
			stack.push(top);
			return top;
		} else {
			T bottomRecurse = printStack(stack);
			
			//Replace everything
			stack.push(top);
			
			return bottomRecurse;
		}
	}
}
