// A basic singly-linked list

// Traversal: O(n)
// Access: O(n)
// Insert/remove at beginning: O(1)
// Insert/remove at middle: O(n)
// Insert/remove at end: O(n)
// Search: O(n)

/*
 * A typical linked list does not keep track of node positions, so accessing elements requires
 * a traversal from the beginning of the list. This makes iterators useful for problems where
 * you need to continuously add or remove elements from a linked list.
 * 
 */

public class SinglyLinkedList<E> {
	
	class Node {
		public E data;
		public Node next;
		
		public Node(E data) {
			this(data, null);
		}

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	// A "global" field that points to the head of the list
	public Node front;
	
	public SinglyLinkedList() {
		front = null;
	}
	
	// Returns the current size of the list
	public int size() {
		int size = 0;
		Node current = front;
		while (current != null) {
			size++;
			current = current.next;
		}
		return size;
	}
	
	// Returns the value stored in a node at the given index
	public E get(int index) {
		return nodeAt(index).data;
	}
	
	// Returns the index of the node containing the given value
	public int indexOf(E value) {
		int index = 0;
		Node current = front;
		while (current != null) {
			if (current.data == value) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}
	
	// Returns a string representation of the linked list
	public String toString() {
		if (front == null) {
			return "[]";
		} else {
			String returned = "[" + front.data;
			Node current = front.next;
			while (current != null) {
				returned += ", " + current.data;
				current = current.next;
			}
			returned += "]";
			return returned;
		}
	}
	
	// Append a value to the end of the list
	public void add(E value) {
		if (front == null) {
			front = new Node(value);
		} else {
			Node current = front;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Node(value);
		}
	}
	
	// Adds a value at the given index
	public void add(int index, E value) {
		if (index == 0) {
			front = new Node(value);
		} else {
			Node current = nodeAt(index-1);
			current.next = new Node(value, current.next);
		}
	}
	
	// Removes a node at the given index
	public void remove(int index) {
		if (index == 0) {
			front = front.next;
		} else {
			Node current = nodeAt(index-1);
			current.next = current.next.next;
		}
	}
	
	// Reverses the links of the list
	public void reverse() {
		Node prev = null;
		Node next = null;
		Node current = front;
		while (current != null) {
			next = current.next; // "Protects" the node after current from the garbage collector
			current.next = prev; // Reverses current's link
			prev = current; // Makes prev point to whatever current is currently pointing to
			current = next; // Makes current point to whatever next is currently pointing to
		}
		front = prev; // Makes the global front variable point to the end of the list
	}
	
	// Returns node at given index; reduces redundancy in code
	private Node nodeAt(int index) {
		Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}
	
	public static void main(String[] args) {
		SinglyLinkedList<String> myList = new SinglyLinkedList<String>();
		myList.add("Alfa");
		myList.add("Bravo");
		myList.add("Charlie");
		System.out.println(myList);
		System.out.println(myList.size());
		System.out.println(myList.get(1));
		System.out.println(myList.indexOf("Charlie"));
		myList.reverse();
		System.out.println(myList);
		myList.remove(0);
		myList.remove(0);
		myList.reverse();
		System.out.println(myList);
		myList.remove(0);
		System.out.println(myList);
	}
}
