import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class consists of an implementation of a priority queue data structure
 * which is built on the top of a dynamic array structure which is interpreted
 * as a Binary Heap Tree.
 *
 * T is a bounded parametric type that implements the Comparable interface to allow
 * the "priority" comparisons on the priority queue. 
 *
 * [!] Make sure that DynamicArray.java is in the same directory as this file [!]
 *
 * @author Igor G. Peternella
 * @date 06-10-2017
 */

public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {
        
    private DynamicArray<T> pq; // pq is a reference to a DynamicArray which is interpreted as a Heap
    private int heapSize;       // heapSize is the DynamicArray (Heap) size

    /** 
     * The algorithms for this data structure requires a one-index-based DynamicArray and not zero-index-based one
     * because of the interpretation of this array as a Binary Heap that starts at heapIndex = 1. Hence, the 
     * indexes used by these algorithms are called virtual indexes (heapIndexes) in contrast to the real indexes 
     * for arrays that start at index 0 such as Java's.
     *
     * Given the fact that:        virtual index == real index - 1  
     * Methods isLess() and swap() have been implemented to subtract 1 from the virtual indexes passed
     * to these methods to map to the real elements of the DynamicArray which is zero-index-based.
     *
     * Builds an empty priority queue based on a DynamicArray implementation.
     */
    
    public PriorityQueue() {
	pq = new DynamicArray<T>();
	heapSize = 0;
    }
    
    /**
     * Returns true if the priority queue is empty (size is zero).
     *
     * @return true if the stack is empty and false otherwise.
     */

    public boolean isEmpty() {
	return heapSize == 0;
    }

    /**
     * Returns the number of elements of the priority queue.
     *
     * @return the size (number of elements) of the priority queue.
     */
    
    public int size() {
	return heapSize;
    }

    
    /**
     * Inserts an item at the proper position in the queue based on its priority.
     * Complexity: O(log(N)). The usage of a Binary Heap allows this implementation
     * to have this complexity. With ordinary linked lists or arrays we have O(N) which
     * is awful for M inserts = O(M*N).
     *
     * @param item is the item to be inserted.
     */

    public void insert(T item) {
	// appends an item at the end of the DynamicArray (as Last node of the heap)
	pq.append(item);
	
	// increases heapSize
	++heapSize;
	
	// swims up to reheapify from bottom up
	swim(heapSize);	
    }
    
    /**
     * Removes and returns the item with highest priority on the priority queue.
     * Complexity: O(log(N)). The usage of a Binary Heap allows this implementation
     * to have this complexity. With ordinary linked lists or arrays we have O(N) which
     * is awful for M delMax = O(M*N).
     *
     * @return item with the highest priority on the queue.
     * @throws java.util.NoSuchElementException if this method is invoked on a empty priority queue.
     */
    
    public T delMax() {
	if (isEmpty()) {
	    throw new java.util.NoSuchElementException("Empty priority queue.");
	}
	// picks the item at the top of the heap which is the biggest item (highest priority)
	T item = pq.getAt(0);

	// swaps the top node (biggest item) with the last node
	swap(1, heapSize);

	// biggest node is at the last position but violates heap order so we decrease heapSize
	--heapSize;

	// removes item from the DynamicArray (Heap)
	pq.pop();

	// sinks down to reheapify from top to bottom
	sink(1);

	return item;
    }
    
    /**
     * Returns the biggest item (highest priority) on the priority queue but does not remove it.
     *
     * @return item with the highest priority on the queue.
     * @throws java.util.NoSuchElementException if this method is invoked on a empty priority queue.
     */
    
    public T max() {
	if (isEmpty()) {
	    throw new java.util.NoSuchElementException("Empty priority queue.");
	}
	
	return pq.getAt(0);
    }
    
    /*
     * Goes down in a heap structure represented by an array and swaps children elements
     * that are bigger than their parent to ensure heap order i.e. this method heapifies
     * an array from top to bottom.
     * 
     * arr is a reference to a Comparable array that represents a heap.
     * heapIndex is a virtual index used for one-based arrays (required by heap algorithm).
     * heapSize is the array size (size of the heap).
     */    
    
    private void sink(int heapIndex) {
	// begins going down the heap if the starting index (parent) has
	// at least a leftChild node so 2 * heapLevel < heapSize evals to true!
	
	// loops until a parent node with no children is found
	// so 2 * heapIndex > heapSize
	while (2 * heapIndex <= heapSize) {
	    // heapIndex is a virtual index and so are leftChild and others	    
	    int parent = heapIndex;                    // parent node
	    int leftChild = 2 * heapIndex;             // first child node
	    
	    // assumes leftChild is the biggest 
	    int biggestChild = leftChild;

	    // checks if the leftChild has a rightChild sibling
	    if (leftChild < heapSize) {
		int rightChild = 2 * heapIndex + 1;        // second child node

		// picks the biggest Sibling (right or left) to compare with parent
		if (isLess(leftChild, rightChild)) {
		    biggestChild = rightChild;
		}
	    }
	    
	    // if biggestChild is smaller than parent
	    // than it follows the heap order so break
	    if (isLess(biggestChild, parent)) {
		break;
	    } else {
		// biggestChild is bigger than parent
		// which violates heap order so swap
		swap(biggestChild, parent);
	    }
	    	    
	    // advances to biggest child
	    // which will become the next parent
	    // to move down on the heap for then next iteration
	    heapIndex = biggestChild;
	}
    }

    /*
     * Goes up in a heap structure represented by an array and swaps a child and its parent until
     * a bigger parent is found i.e. this method heapifies an array from top to bottom.
     * 
     * heapIndex is a virtual index used for one-based arrays (required by heap algorithm).
     * heapSize is the array size (size of the heap).
     */    
    
    private void swim(int heapIndex) {
	// heapifies from bottom-up until the first node
	// which has no parent (heapIndex = 1)
	while (heapIndex > 1) {
	    int child = heapIndex;
	    int parent = heapIndex/2;

	    // if child is bigger than its parent node
	    // swap
	    if (isLess(parent, child)) {
		swap(parent, child);
	    } else {
		// if a a bigger parent is found
		// then the heap order is ensured
		break;
	    }

	    // moves up on the heap for the next iteration
	    heapIndex = parent;
	}
    }

    /*
     * Swaps two elements of the DynamicArray. It uses DynamicArray's method
     * replaceAt(index) to make the swap.
     * 
     * ix1 is the index of the first element to be swapped on the DynamicArray.
     * ix2 is the index of the second element to be swapped on the DynamicArray.
     */    
    
    private void swap(int ix1, int ix2) {
	// corrects virtual indexes of the heap to
	// map to real indexes of 0 based java arrays (which DynamicArray is based)
	--ix1;
	--ix2;
	
        T temp = pq.getAt(ix1);

	// swaps items at the real indexes of the DynamicArray
	pq.replaceAt(ix1, pq.getAt(ix2));
	pq.replaceAt(ix2, temp);
    }
    
    /*
     * Compares two Comparables objects of the DynamicArray. It uses DynamicArray's method
     * getAt(index) to get items.
     * 
     * ix1 is the index of the first object to be compared.
     * ix2 is the index of the second object to be compared.
     *
     * returns true if the item at index ix1 is less than the item at the index ix2. Returns
     * false otherwise.
     */
    
    private boolean isLess(int ix1, int ix2) {
	// corrects virtual indexes of the heap to
	// map to real indexes of 0 based java arrays (which DynamicArray is based)
	--ix1;
	--ix2;

	// gets the items at the real index from the Dynamic Array
	Comparable one = pq.getAt(ix1);
	Comparable another = pq.getAt(ix2);
	
	return one.compareTo(another) < 0; // -1 when less
    }

    public Iterator<T> iterator() {
	return pq.iterator();
    }
           
    // unit testing
    public static void main(String[] args) {
	PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	pq.insert(2);
	pq.insert(1);
	pq.insert(5);
	pq.insert(3);
	pq.insert(20);

	// for (int i : pq) {
	//     System.out.println(i);
	// }

	System.out.println(pq.delMax());

	pq.insert(7);
	
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());

	System.out.println("size: " + pq.size());

	pq.insert(3);
	pq.insert(3);
	pq.insert(25);

	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	System.out.println(pq.delMax());
	
	System.out.println("size: " + pq.size());
    }
}
