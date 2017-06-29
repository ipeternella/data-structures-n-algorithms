/**
 * This class consists of an implementation of a doubly linked list data structure which
 * uses a helper nested class Node to represent the nodes of the list.
 * This class has a reference to the head and tail Nodes as private instance variables.
 * It also implements the Iterable interface.
 *
 * @author Igor G. Peternella
 * @date 06-01-2017
 */

package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

    private Node head;    // reference to the head (beginning) of the list
    private Node tail;    // reference to the tail (end) of the list
    private int size;     // primitive to hold the size (number of nodes)
    
    // nested class to represent doubly linked nodes
    private class Node {
        T item;           // item that the Node holds
        Node next;        // reference to the next Node
        Node previous;    // reference to the previous Node

        // constructor takes an item and a reference to the previous AND next Node
        // as arguments to build a new Node on the heap
        public Node(T item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    /**
     * Default constructor. Intializes an empty doubly linked list and sets
     * the head and tail references to null.
     */
    
    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Returns the number of elements of the linked list.
     *
     * @return the size (number of elements) of the linked list.
     */

    public int size() {
        return size;
    }

    /**
     * Returns true if the linked list is empty (size is zero).
     *
     * @return true if the structure is empty and false otherwise.
     */

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts a new Node at the beginning of the linked list (changes head reference).
     * Complexity: O(1). No traversing required.
     *
     * @param item is the item to be inserted.
     */

    public void leftInsert(T item) {
        if (isEmpty()) {
            // if the list is empty we call a special helper method
            // that sets both head and tail references to the same object
            createFirstNode(item);
        } else {
            // creates a newNode to be inserted whose previous
            // reference is null (will become the first Node of the list)
            // and its next reference to the old head
            Node newNode = new Node(item, null, head);
            // sets old head's previous reference to the newNode
            head.previous = newNode;
            // changes head reference to the new Node
            head = newNode;
            ++size;
        }
    }

    /**
     * Inserts a new Node at the end of the linked list (changes tail reference).
     * Complexity: O(1). No traversing required.
     *
     * @param item is the item to be inserted.
     */

    public void rightInsert(T item) {
        // if the list is empty we call a special helper method
        // that sets both head and tail references to the same object   
        if (isEmpty()) {
            createFirstNode(item);
        } else {
            // creates a new Node to be inserted whose previous reference
            // points to the old tail and next references points to null (will become the last Node)
            Node newNode = new Node(item, tail, null);
            
            // sets old tail reference to point to the new Node
            tail.next = newNode;
            // changes tail reference to point to the new Node
            tail = newNode;
            ++size;
        }
    }

    /**
     * Inserts a new Node at a given index of the linked list.
     * Complexity: O(N)
     *
     * @param item is the item to be inserted.
     * @param ix is the index to insert the new Node.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */
        
    public void insertAt(T item, int ix) {
        if(!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid node number.");
        }
    
        if (ix == 0) {
            // if ix == 0, then it's just a leftInsert
            leftInsert(item);
        } else if (ix == size - 1) {
            // if ix == size - 1, then it's just a rightInsert
            rightInsert(item);     
        } else {
            // traverses list to get previous and post Node references to
            // insert the new Node between these two.
            Node previousNode = getNodeAt(ix - 1);
            Node postNode = getNodeAt(ix);

            // creates a newNode between previousNode and postNode
            Node newNode = new Node(item, previousNode, postNode);

            // adjusts previousNode's next reference to point to the newNode
            // and postNode's previous reference to point to the newNode
            previousNode.next = newNode;
            postNode.previous = newNode;
            ++size;
        }
    }

    // helper method to create first Node when list is empty
    private void createFirstNode(T item) {
        head = new Node(item, null, null);
        tail = head;
        ++size;
    }

    /**
     * Gets the item of a Node specified by its index.
     * Complexity: O(N)
     *
     * @param ix is the index of the desired Node.
     * @return item of the specified Node.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */
    
    public T getItemAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid node number.");
        }
        
        Node node = getNodeAt(ix);
        
        return node.item;
    }

    /**
     * Gets a reference of a Node object specified by its index.
     * Complexity: O(N)
     *
     * @param ix is the index of the desired Node.
     * @return reference to the specified Node.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */

    public Node getNodeAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid node number.");
        }

        Node node = head;

        for (int i = ix; i > 0; --i) {
            node = node.next;
        }

        return node;
    }
    
    /**
     * Removes and returns the item of the last node (tail).
     * Complexity: O(1). Since this is a Doubly linked list,
     * traversing to the Node before the last one is not necessary.
     *
     * @return item of the last Node.
     */

    public T pop() {
        return removeAt(size - 1);
    }

    /**
     * Removes and returns the item of a Node specified by its index.
     * Complexity: O(N) for ix != 0 and ix != size - 1
     * O(1) for head and tail operations (ix = 0 or ix = size -1).
     *
     * @param ix is the index of the desired Node.
     * @return item of the Node specified by the given index.
     * @throw java.util.NoSuchElementException if ix is invalid or list is empty.
     */

    public T removeAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid node number.");
        }

        if (size == 0) {
            throw new NoSuchElementException("Empty linked list.");
        }
        
        if (ix == 0) {
            // creates newNode reference that points to the second Node of the list
            Node newHead = head.next;
            // saves last head's item
            T item = head.item;

            // makes newNode's previous reference null (will become the first Node)
            newHead.previous = null;
            // removes old head's reference to the second Node (newHead)
            head.next = null;
            // sets head reference to the newHead;
            head = newHead;
            --size;

            return item;
        } else if (ix == size - 1) {
            // creates a new reference to the Node before the tail Node (newTail)
            Node newTail = tail.previous;
            // save tail's item
            T item = tail.item;

            // sets newTail's next reference to null (will become the last Node)
            newTail.next = null;
            // sets old Tail's reference to point to null (gbc)
            tail.previous = null;
            // sets tail reference var to point to the newTail Node
            tail = newTail;
            --size;

            return item;
        } else {
            // gets a reference to the previous Node (ix - 1) -> O(N)
            // gets a reference to the post Node (ix + 1) -> O(N)
            // gets a reference to the desired Node to be removed (ix) -> O(N)          
            Node previousNode = getNodeAt(ix - 1);
            Node currentNode = getNodeAt(ix);
            Node postNode = getNodeAt(ix + 1);
            // gets the item of the desired Node to be removed
            T item = currentNode.item;

            // adjusts previousNode's next reference to point to postNode
            previousNode.next = postNode;
            // adjusts postNode's previous reference to point to previousNode
            postNode.previous = previousNode;

            // eliminates removed Node's references to previousNode and postNode
            currentNode.next = null;
            currentNode.previous = null;
            --size;

            return item;
        }
    }

    // helper method that returns true if an index is invalid.
    private boolean isValidIndex(int ix) {
        if (ix < 0 || ix >= size) {
            return false;
        } else {
            return true;
        }
    }
    
    // Iterable interface implementation for DoublyLinkedList data structure.
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }
    
    // Nested private class to create Iterator Objects for the DoublyLinkedList data structure.
    // Iterator interface implementation.
    private class DoublyLinkedListIterator implements Iterator<T> {
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

    // unit testing
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
