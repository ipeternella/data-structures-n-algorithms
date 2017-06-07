/**
 *
 * This class represents a Deque data structure.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {
    
    DoublyLinkedList<T> doublyLinkedList;
    int size;

    public Deque() {
	doublyLinkedList = new DoublyLinkedList<T>();
	size = 0;	
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public void leftInsert(T item) {
	doublyLinkedList.leftInsert(item); // O(1)
	++size;
    }    

    public void rightInsert(T item) {
	doublyLinkedList.rightInsert(item); // O(1)
	++size;
    }

    public T removeLeft() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Empty Deque.");
	}
	
	--size;
	return doublyLinkedList.removeAt(0); // O(1) OK!
    }

    public T removeRight() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Empty Deque.");
	}

	T item = doublyLinkedList.removeAt(size - 1); // O(1) OK!
	--size;
    
	return item;
    }

    public T peekLeft() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Empty Deque.");
	}
	
	return doublyLinkedList.getItemAt(0);
    }

    public T peekRight() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Empty Deque.");
	}
	
	return doublyLinkedList.getItemAt(size - 1);
    }
    
    public Iterator<T> iterator() {
	return doublyLinkedList.iterator();
    }
    
    // unit tests
    public static void main(String[] arg) {
	Deque<Integer> d = new Deque<Integer>();
	
	d.leftInsert(1);
	d.leftInsert(2);
	d.rightInsert(-5);
	d.removeRight();
	d.rightInsert(100);
	d.removeRight();
	d.removeLeft();
	
	for (int i : d) {
	    System.out.println(i);
	}

	System.out.println(d.peekRight());
    }
}
