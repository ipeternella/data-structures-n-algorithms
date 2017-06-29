/**
 * This class consists of an implementation of a stack data structure (follows LIFO policy) 
 * which is built on the top of a singly linked list data structure.
 * This class implements the Iterable interface which uses SinglyLinkedList class.
 * iterator.
 *
 * [!] Make sure that SinglyLinkedList.java is in the same directory as this file [!]
 *
 * @author Igor G. Peternella
 * @date 06-03-2017
 */

package StackQueueDeque;

import LinkedList.SinglyLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T>{

    private SinglyLinkedList<T> singlyLinkedList;     // reference to a singly linked list
    private int size;                                 // size of the stack

    /**
     * Default constructor. Intializes an empty stack and sets
     * singlyLinkedList reference var to an empty singly linked list.
     */
    
    public Stack() {
        singlyLinkedList = new SinglyLinkedList<T>();
        size = 0;
    }

    /**
     * Returns true if the stack is empty (size is zero).
     *
     * @return true if the stack is empty and false otherwise.
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements of the stack.
     *
     * @return the size (number of elements) of the stack.
     */
    
    public int size() {
        return size;
    }

    /**
     * Inserts an item on the top of the stack.
     * Complexity: O(1).
     *
     * @param item is the item to be inserted.
     */
    
    public void push(T item) {
        // inserts the item at the beginning of the singly linked list
        // to follow LIFO policy
        // O(1) operation
        singlyLinkedList.leftInsert(item);
        ++size;
    }

    /**
     * Removes the last added element of the stack (top item).
     * Complexity: O(1).
     *
     * @return the last added element to the stack which follows LIFO policy.
     * @throws java.util.NoSuchElementException if the stack is empty.
     */
    
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty stack.");
        }
	
        --size;

        // removes the last LEFT inserted item (top of the stack)
        // which is a O(1) operation
        return singlyLinkedList.removeAt(0);
    }

    /**
     * Returns the last added item to the stack (top item) but does not remove it.
     * Complexity: O(1).
     *
     * @return the top item of the stack.
     * @throw java.util.NoSuchElementException if the stack is empty.
     */
    
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty stack.");
        }
	
        return singlyLinkedList.getItemAt(0);
    }

    /**
     * Returns the item at a given index position of the stack.
     * Complexity: O(N).
     *
     * @param ix is the index of the desired element.
     * @return the item of the stack specified by the given index.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */
    
    public T getAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid stack index.");
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

    // Iterable interface implementation for the stack data structure
    public Iterator<T> iterator() {
        // returns the iterator from the SinglyLinkedList class
        // which follows the Iterator<T> interface
        return singlyLinkedList.iterator();
    }

    // unit testing
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.pop();
        s.pop();
        s.pop();

        s.push(-5);
        s.push(-10);
	
        for (int i : s) {
            System.out.println(i);
        }

        System.out.println(s.getAt(1));
    }
}
