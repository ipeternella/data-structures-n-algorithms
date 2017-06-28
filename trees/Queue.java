import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class consists of an implementation of a queue data structure (follows FIFO policy) 
 * which is built on the top of a singly linked list data structure.
 * This class implements the Iterable interface which uses SinglyLinkedList class
 * iterator.
 *
 * [!] Make sure that SinglyLinkedList.java is in the same directory as this file [!]
 *
 * @author Igor G. Peternella
 * @date 06-02-2017
 */

public class Queue<T> implements Iterable<T> {

    private SinglyLinkedList<T> singlyLinkedList;  // reference to a singly linked list
    private int size;                              // size of the queue

    /**
     * Default constructor. Intializes an empty queue and sets
     * singlyLinkedList reference var to an empty singly linked list.
     */
    
    public Queue() {
		singlyLinkedList = new SinglyLinkedList<T>();
		size = 0;
    }

    /**
     * Returns true if the queue is empty (size is zero).
     *
     * @return true if the queue is empty and false otherwise.
     */

    public boolean isEmpty() {
		return size == 0;
    }
    
    /**
     * Returns the number of elements of the queue.
     *
     * @return the size (number of elements) of the queue.
     */

    public int size() {
		return size;
    }

    /**
     * Inserts an item at the beginning of the queue.
     * Complexity: O(1).
     *
     * @param item is the item to be inserted.
     */
    
    public void enqueue(T item) {
		// inserts the item at the end of a singly linked list
		// to follow FIFO policy
		// which is O(1) operation
		singlyLinkedList.rightInsert(item);
		++size;
    }

    /**
     * Removes the first item of the queue.
     * Complexity: O(1).
     *
     * @return the first item of queue which follows the FIFO policy.
     * @throws java.util.NoSuchElementException if the queue is empty.
     */

    public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty queue.");
		}
	
		--size;

		// returns the first item of the singly linked list
		// which is O(1) operation
		return singlyLinkedList.removeAt(0);
    }

    /**
     * Returns the first item of the queue but does not remove it.
     * Complexity: O(1).
     *
     * @return the first item of the queue.
     * @throw java.util.NoSuchElementException if the queue is empty.
     */

    public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty queue.");
		}
	
		return singlyLinkedList.getItemAt(0);
    }

    /**
     * Returns the item at a given index position of the queue.
     * Complexity: O(N).
     *
     * @param ix is the index of the desired element.
     * @return the item of the queue specified by the given index.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */

    public T getAt(int ix) {
		if (!isValidIndex(ix)) {
			throw new NoSuchElementException("Invalid queue index.");
		}

		// uses singly linked list method which is O(N)
		return singlyLinkedList.getItemAt(ix);
    }

    // helper method that returns true if an index is invalid
    private boolean isValidIndex(int ix) {
		if (ix < 0 || ix >= size) {
			return false;
		} else {
			return true;
		}
    }

    // Iterable interface implementation for the queue data structure
    public Iterator<T> iterator() {
		// returns the iterator from the SinglyLinkedList class
		// which follows the Iterator<T> interface
		return singlyLinkedList.iterator();
    }    

    // unit testing
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
