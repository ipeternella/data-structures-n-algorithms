/**
 *
 * This class represents a Doubly Linked List data structure.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
	size = 0;
	head = null;
	tail = null;
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public void leftInsert(T item) {
	if (isEmpty()) {
	    createFirstNode(item);
	} else {
	    Node newNode = new Node(item, null, head);
	    head.previous = newNode;
	    head = newNode;
	    ++size;
	}
    }

    public void rightInsert(T item) {
    	if (isEmpty()) {
    	    createFirstNode(item);
    	} else {
	    Node newNode = new Node(item, tail, null);
	    tail.next = newNode;
	    tail = newNode;
	    ++size;
    	}
    }
        
    public void insertAt(T item, int ix) {
    	if(!isValidIndex(ix)) {
    	    throw new NoSuchElementException("Invalid node number.");
    	}

    	// if ix == 0, then it's just a leftInsert
    	// if ix == size - 1, then it's just a rightInsert
    	if (ix == 0) {
    	    leftInsert(item);
    	} else if (ix == size - 1) {
    	    rightInsert(item);	   
    	} else {
	    Node previousNode = getNodeAt(ix - 1);
	    Node postNode = getNodeAt(ix);
	    Node newNode = new Node(item, previousNode, postNode);

	    previousNode.next = newNode;
	    postNode.previous = newNode;
	    ++size;
    	}
    }

    private void createFirstNode(T item) {
	head = new Node(item, null, null);
	tail = head;
	++size;
    }

    public T getItemAt(int ix) {
    	Node node = getNodeAt(ix);
	
    	return node.item;
    } 

    public Node getNodeAt(int ix) {
    	if (!isValidIndex(ix)) {
    	    throw new NoSuchElementException("Invalid node number.");
    	}

    	Node node = head;
    	int counter = ix;

    	for (int i = ix; i > 0; --i) {
    	    node = node.next;
    	}

    	return node;
    }

    public void pop() {
    	removeAt(size - 1);
    }

    public T removeAt(int ix) {
    	if (!isValidIndex(ix)) {
    	    throw new NoSuchElementException("Invalid node number.");
    	}

	if (size == 0) {
	    throw new NoSuchElementException("Empty linked list.");
	}
	
    	if (ix == 0) {
	    Node newHead = head.next;
	    T item = head.item;
	    
	    newHead.previous = null;
	    head.next = null;
	    head = newHead;
	    --size;

	    return item;
    	} else if (ix == size - 1) {
	    Node newTail = tail.previous;
	    T item = tail.item;
	    
	    newTail.next = null;
	    tail.previous = null;
	    tail = newTail;
	    --size;

	    return item;
    	} else {
	    Node previousNode = getNodeAt(ix - 1);
	    Node currentNode = getNodeAt(ix);
	    Node postNode = getNodeAt(ix + 1);
	    T item = currentNode.item;

	    previousNode.next = postNode;
	    postNode.previous = previousNode;
	    currentNode.next = null;
	    currentNode.previous = null;
	    --size;

	    return item;
    	}
    }

    private boolean isValidIndex(int ix) {
	if (ix < 0 || ix >= size) {
	    return false;
	} else {
	    return true;
	}
    }

    public Iterator<T> iterator() {
	return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator {
	Node currentNode;

	public DoublyLinkedListIterator() {
	    currentNode = head;
	}

	public boolean hasNext() {
	    return currentNode != null;
	}

	public T next() {
	    T item = currentNode.item;
	    currentNode = currentNode.next;

	    return item;
	}

	public void remove() {
	    // unsupported
	}
    }
        
    private class Node {
	T item;
	Node next;
	Node previous;

	public Node(T item, Node previous, Node next) {
	    this.item = item;
	    this.previous = previous;
	    this.next = next;
	}
    }
    
    public static void main(String[] args) {
	DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();

	dll.leftInsert(1);
	dll.leftInsert(2);
	dll.leftInsert(3);
	dll.rightInsert(-5);
	dll.insertAt(-77, 2);

	dll.removeAt(2);
	dll.rightInsert(10);
	
	for (int i : dll) {
	    System.out.println(i);
	}
    }
}
