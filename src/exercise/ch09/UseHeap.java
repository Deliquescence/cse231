package exercise.ch09;

public class UseHeap
{
	public static void main(String[] args)
	{
		PriQueueInterface<String> h = new Heap<String>(10);
		h.enqueue("J");
		h.enqueue("A");
		h.enqueue("M");
		h.enqueue("B");
		h.enqueue("L");
		h.enqueue("E");
		
		System.out.println(h);
		
		System.out.println(h.dequeue() + "\n");
		System.out.println(h);
		
		System.out.println(h.dequeue() + "\n");
		System.out.println(h);
		
		System.out.println(h.dequeue() + "\n");
		System.out.println(h);
		
		System.out.println(h.dequeue() + "\n");
		System.out.println(h);
		
		System.out.println(h.dequeue() + "\n");
		System.out.println(h);
	}
}