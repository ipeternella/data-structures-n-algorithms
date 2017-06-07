/**
 *
 * This class represents a Singly Linked List data structure.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T>{

    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
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
	    head = new Node(item, head); 
	    ++size;
	}
    }

    public void rightInsert(T item) {
	if (isEmpty()) {
	    createFirstNode(item);
	} else {
	    Node newNode = new Node(item, null);
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
	    Node postNode = previousNode.next;
	    
	    previousNode.next = new Node(item, postNode);
	    ++size;
	}
    }

    private void createFirstNode(T item) {
	tail = new Node(item, null);
	head = tail;
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

    public T pop() {
	return removeAt(size - 1);
    }

    public T removeAt(int ix) {
	if (!isValidIndex(ix)) {
	    throw new NoSuchElementException("Invalid node number.");
	}

	if (size == 0) {
	    throw new RuntimeException("Empty list.");
	}
	
	if (ix == 0) {
	    Node oldHead = head;
	    T item = oldHead.item;
	    head = oldHead.next;

	    // removes first Node
	    oldHead.next = null;
	    --size;	    

	    return item;
	} else if (ix == size - 1) {
	    Node newTail = getNodeAt(size - 2);
	    T item = tail.item; // oldTail's item
	    
	    newTail.next = null;
	    tail = newTail; // update tail reference
	    --size;

	    return item;
	} else {
	    Node previousNode = getNodeAt(ix - 1);
	    Node currentNode = getNodeAt(ix);
	    Node postNode = getNodeAt(ix + 1);
	    T item = currentNode.item;
	    
	    currentNode.next = null;
	    previousNode.next = postNode;
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
	return new SinglyLinkedListIterator();
    }
    
    private class Node {
	T item;
	Node next;

	public Node(T item, Node next) {
	    this.item = item;
	    this.next = next;
	}
    }

    private class SinglyLinkedListIterator implements Iterator {
	Node currentNode;

	public SinglyLinkedListIterator() {
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
	    // nothing
	}
    }    
    
    public static void main(String[] args) {
	SinglyLinkedList<Integer> sll = new SinglyLinkedList<Integer>();

	sll.leftInsert(1);
	sll.leftInsert(2);
	sll.leftInsert(3);
	sll.rightInsert(4);

	sll.removeAt(1);
	sll.removeAt(1);
	sll.removeAt(1);
	sll.pop();

	sll.rightInsert(5);
	sll.rightInsert(10);
	sll.leftInsert(-2);
	sll.removeAt(sll.size() - 1);

	// for (int i = 0; i < sll.size(); ++i) {
	//     System.out.println(sll.getItemAt(i));
	// }

	for (int i : sll) {
	    System.out.println(i);
	}

    }
}
