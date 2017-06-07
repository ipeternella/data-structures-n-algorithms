/**
 *
 * This class represents a Queue data structure.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {

    private SinglyLinkedList<T> singlyLinkedList;    
    private int size;

    public Queue() {
	singlyLinkedList = new SinglyLinkedList<T>();
	size = 0;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public void enqueue(T item) {
	singlyLinkedList.rightInsert(item);
	++size;
    }

    public T dequeue() {
	if (size == 0) {
	    throw new NoSuchElementException("Empty queue.");
	}
	
	--size;
	return singlyLinkedList.removeAt(0);
    }

    public T getAt(int ix) {
	if (!isValidIndex(ix)) {
	    throw new NoSuchElementException("Invalid queue index.");
	}
	
	return singlyLinkedList.getItemAt(ix);
    }

    public T peek() {
	if (size == 0) {
	    throw new NoSuchElementException("Empty queue.");
	}
	
	return singlyLinkedList.getItemAt(0);
    }
    
    private boolean isValidIndex(int ix) {
	if (ix < 0 || ix >= size) {
	    return false;
	} else {
	    return true;
	}
    }
    
    public Iterator<T> iterator() {
	// returns the iterator from the SinglyLinkedList class!
	return singlyLinkedList.iterator();
    }    

    public static void main(String[] args) {
	Queue<Integer> q = new Queue<Integer>();

	q.enqueue(1);
	q.enqueue(2);
	q.enqueue(3);

	q.dequeue();
	q.dequeue();
	q.dequeue();

	q.enqueue(-5);
	q.enqueue(10);

	for (int i : q) {
	    System.out.println(i);
	}
    }
}
