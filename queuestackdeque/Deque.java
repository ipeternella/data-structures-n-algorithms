/**
 * This class consists of an implementation of a deque data structure
 * which is built on the top of a doubly linked list data structure to support
 * efficient operations at both the beginning and the end of the deque structure.
 * This class implements the Iterable interface which uses DoublyLinkedList class
 * iterator.
 *
 * @author Igor G. Peternella
 * @date 06-03-2017
 */

package queuestackdeque;

import linkedlist.DoublyLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {
    
    private DoublyLinkedList<T> doublyLinkedList; // reference to a singly linked list
    private int size;                             // size of the deque

    /**
     * Default constructor. Intializes an empty deque and sets
     * singlyLinkedList reference var to an empty singly linked list.
     */
    
    public Deque() {
        doublyLinkedList = new DoublyLinkedList<T>();
        size = 0;	
    }

    /**
     * Returns true if the deque is empty (size is zero).
     *
     * @return true if the deque is empty and false otherwise.
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements of the deque.
     *
     * @return the size (number of elements) of the deque.
     */
    
    public int size() {
        return size;
    }

    /**
     * Inserts an item at the beginning of the deque.
     * Complexity: O(1).
     *
     * @param item is the item to be inserted.
     */
    
    public void leftInsert(T item) {
        // O(1) operation for a doubly linked list
        doublyLinkedList.leftInsert(item);
        ++size;
    }
    
    /**
     * Inserts an item at the end of the deque.
     * Complexity: O(1).
     *
     * @param item is the item to be inserted.
     */

    public void rightInsert(T item) {
        // O(1) operation for a doubly linked list
        doublyLinkedList.rightInsert(item); 
        ++size;
    }

    /**
     * Removes the first item of the deque.
     * Complexity: O(1).
     *
     * @return the first item of deque.
     * @throws java.util.NoSuchElementException if the deque is empty.
     */

    public T removeLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty deque.");
        }
	
        --size;
	
        // removing at the beginning is a O(1) operation
        // for a doubly linked list
        return doublyLinkedList.removeAt(0); 
    }

    /**
     * Removes the last item of the deque.
     * Complexity: O(1).
     *
     * @return the last item of deque.
     * @throws java.util.NoSuchElementException if the deque is empty.
     */

    public T removeRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty deque.");
        }

        // removing at the end is also a O(1) operation
        // for a doubly linked list (not for a singly though)
        T item = doublyLinkedList.removeAt(size - 1); 
        --size;

        // returns the item
        return item;
    }

    /**
     * Returns the first item of the deque but does not remove it.
     * Complexity: O(1).
     *
     * @return the first item of the deque.
     * @throw java.util.NoSuchElementException if the deque is empty.
     */
    
    public T peekLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty deque.");
        }

        // O(1) operation for a doubly linked list
        return doublyLinkedList.getItemAt(0);
    }

    /**
     * Returns the last item of the deque but does not remove it.
     * Complexity: O(1).
     *
     * @return the last item of the deque.
     * @throw java.util.NoSuchElementException if the deque is empty.
     */
    
    public T peekRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Deque.");
        }

        // O(1) operation for a doubly linked list
        return doublyLinkedList.getItemAt(size - 1);
    }

    /**
     * Returns the item at a given index position of the deque.
     * Complexity: O(N).
     *
     * @param ix is the index of the desired element.
     * @return the item of the deque specified by the given index.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */

    public T getAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid deque index.");
        }

        // uses doubly linked list method which is O(N)
        return doublyLinkedList.getItemAt(ix);
    }

    // helper method that returns true if an index is invalid
    private boolean isValidIndex(int ix) {
        if (ix < 0 || ix >= size) {
            return false;
        } else {
            return true;
        }
    }
    
    // Iterable interface implementation for the deque data structure
    public Iterator<T> iterator() {
        // returns the iterator from the DoublyLinkedList class
        // which follows the Iterator<T> interface
        return doublyLinkedList.iterator();
    }
    
    // unit testing
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

        System.out.println(d.peekLeft());
        System.out.println(d.peekRight());
        System.out.println(d.size());
    }
}
