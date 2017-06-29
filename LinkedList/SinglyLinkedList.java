package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class consists of an implementation of a singly linked list data structure which
 * uses a helper nested class Node to represent the nodes of the list.
 * This class has a reference to the head and tail Nodes as private instance variables.
 * It also implements the Iterable interface.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 */

public class SinglyLinkedList<T> implements Iterable<T>{

    private Node head;   // reference to the head (beginning) of the list
    private Node tail;   // reference to the tail (end) of the list
    private int size;    // primitive to hold the size (number of nodes)

    // nested class to represent singly linked nodes
    private class Node {
        T item;      // item that the node holds
        Node next;   // reference to the next node
        
        // constructor takes an item and a reference to the next Node
        // as arguments to build a new Node on the heap
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
    
    /**
     * Default constructor. Intializes an empty singly linked list and sets
     * the head and tail references to null.
     */
    
    public SinglyLinkedList() {
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
            // creates a new Node that points to the old head Node (old first)
            // and then sets the head reference to point this new Node (new head)
            head = new Node(item, head);
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
        if (isEmpty()) {
            // if the list is empty we call a special helper method
            // that sets both head and tail references to the same object
            createFirstNode(item);
        } else {
            // creates a new Node whose next reference is null
            Node newNode = new Node(item, null);

            // old tail Node points to new Node
            tail.next = newNode;
            // sets tail reference to the new Node
            tail = newNode;
            ++size;
        }
    }

    /**
     * Inserts a new Node at a given index of the linked list (changes tail).
     * Complexity: O(N)
     *
     * @param item is the item to be inserted.
     * @param ix is the index to insert the new Node.
     * @throw java.util.NoSuchElementException if ix is invalid.
     */
    
    public void insertAt(T item, int ix) {
        // tests ix
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
            // insert the new Node between these Nodes.
            Node previousNode = getNodeAt(ix - 1);
            Node postNode = previousNode.next;

            // changes previous Node reference to the new Node
            // which in turn points to the postNode reference
            previousNode.next = new Node(item, postNode);
            ++size;
        }
    }

    // helper method to create first Node when list is empty
    private void createFirstNode(T item) {
        tail = new Node(item, null);
        head = tail;
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
     * Complexity: O(N). This is not a Doubly linked list, 
     * traversing is required to change tail reference.
     *
     * @return item of the last Node.
     */
    
    public T pop() {
        return removeAt(size - 1);
    }

    /**
     * Removes and returns the item of a Node specified by its index.
     * Complexity: O(N) - this is not a Doubly linked list, 
     * traversing is required for cases where ix != 0.
     *
     * @param ix is the index of the desired Node.
     * @return item of the Node specified by the given index.
     * @throw java.util.NoSuchElementException if ix is invalid or list is empty.
     */
    
    public T removeAt(int ix) {
        if (!isValidIndex(ix)) {
            throw new NoSuchElementException("Invalid node index.");
        }

        if (size == 0) {
            throw new NoSuchElementException("Empty list.");
        }
        
        if (ix == 0) {
            // creates a reference to the old head
            Node oldHead = head;
            // gets the old head's item
            T item = oldHead.item;
            // advances head reference to the next node (2nd on the list)
            head = oldHead.next;

            // removes reference of the old head to the new first Node
            oldHead.next = null;
            --size;     

            return item;
        } else if (ix == size - 1) {
            // traverses to one Node before the tail Node (ix - 2)
            // which will become the new tail Node
            Node newTail = getNodeAt(size - 2);
            // get the oldTail's item
            T item = tail.item;

            // sets newTail's next reference to null since now its the last Node
            newTail.next = null;
            // updates tail reference variable to the new tail (last) Node
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
            // gets the item of the desired node to be removed
            T item = currentNode.item;

            // makes the removed Node's next reference point to null
            currentNode.next = null;
            // makes previousNode's next reference point to the post Node (skip the removed Node)
            previousNode.next = postNode;
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

    // Iterable interface implementation for the SinglyLinkedList data structure.
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }
    
    // Nested private class to create Iterator Objects for the SinglyLinkedList data structure.
    // Iterator interface implementation.
    private class SinglyLinkedListIterator implements Iterator<T> {
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

    // unit testing
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
