// A doubly-linked list initialized with two dummy nodes (one front node, one back node and all
// insertions occur between nodes).
//

public class DoublyLinkedList<E> {
	
	class Node{
		
		public E data;
		public Node next;
		public Node prev;
		
		public Node(E data) {
			this(data, null, null);
		}
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
	}
	
	private Node front;
	private Node back;	
	private int size;
	
	public DoublyLinkedList() {
		front = new Node(null);
		back = new Node(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public E get(int index) {
		checkIndex(index);
		return nodeAt(index).data;
	}

	public String toString() {
		if (size == 0) {
			return "[]";
		} else {
			String result = "[" + front.next.data;
			Node current = front.next.next;
			while (current != back) {
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}
	
	public int indexOf(E value) {
		int index = 0;
		Node current = front.next;
		while (current != back) {
			if (current.data.equals(value)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(E value) {
		return indexOf(value) >= 0;
	}
	
	public void add(int index, E value) {
		checkIndex(index);
		Node current = nodeAt(index - 1);
		Node newNode = new Node(value, current.next, current);
		current.next = newNode;
		newNode.next.prev = newNode;
		size++;
	}
	
	public void add(E value) {
		add(size, value);
	}
	
	public void remove(int index) {
		checkIndex(index);
		Node current = nodeAt(index - 1);
		current.next = current.next.next;
		current.next.prev = current;
		size--;
	}
	
	public void set(int index, E value) {
		checkIndex(index);
		Node current = nodeAt(index);
		current.data = value;
	}
	
	private Node nodeAt(int index) {
		Node current;
		// Node is in first half
		if (index < size / 2) {
			current = front;
			for (int i = 0; i < index + 1; i++) {
				current = current.next;
			}
		// Node is in second half
		} else {
			current = back;
			for (int i = size; i >= index + 1; i--) {
				current = current.prev;
			}
		}
		return current;
	}
	
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
