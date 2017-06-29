import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class consists of an implementation of the dynamic array data structure which
 * uses a common array as the main data structure for the algorithms.
 * It contains a resize method that reallocates all elements of the current array to 
 * a bigger or smaller one as necessary during run-time. 
 * This class implements the Iterable interface.
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 */

public class DynamicArray<T> implements Iterable<T>{

    private int INITIAL_SIZE = 1; // initial size of the main array
    private T[] arr;              // the main array of this data structure
    private int size;             // the size of the collection

    /**
     * Default constructor. Intializes an empty DynamicArray object with empty size and the
     * main array of this data structure as a new Object[] type which is cast into T[] type. 
     */
    
    public DynamicArray() {
		size = 0;
		arr = (T[]) new Object[INITIAL_SIZE];
    }

    /**
     * Returns true if the DynamicArray is empty (size is zero).
     *
     * @return true if the structure is empty and false otherwise.
     */
    
    public boolean isEmpty() {
		return size == 0;
    }

    /**
     * Returns the number of elements of the DynamicArray.
     *
     * @returns the size of the DynamicArray.
     */

    public int size() {
		return size;
    }

    /**
     * Appends an item to the end of the DynamicArray.
     * Complexity: O(1) amortized.
     *
     * @param item is the item to be appended.
     */

    public void append(T item) {
		arr[size] = item;
		++size;

		// resizes to a new array if necessary
		if (size == arr.length) {
			resize(2 * size);
		}
    }

    /**
     * Overloaded method that appends an item to a valid index of the 
     * DynamicArray and shifts elements to the right.
     * Complexity: O(N).
     *
     * @param item is the item to be added.
     * @param is a valid index to insert the item.
     * @throws java.lang.ArrayIndexOutOfBoundsException if an invalid ix is passed.
     */
    
    public void append(T item, int ix) {
		// isValidIndex is a helper method
		if (!isValidIndex(ix)) {
			throw new java.lang.ArrayIndexOutOfBoundsException("Invalid Index.");
		} 	

		// shifts elements to the right after the insertion
		for (int i = size - 1; i >= ix; --i) {
			arr[i + 1] = arr[i];
		}

		// inserts item @ ix position
		arr[ix] = item;
		++size;

		// resizes to a new array if necessary
		if (size == arr.length) {
			resize(2 * size);
		}
    }
    
    /**
     * Gets the item at a given index of the DynamicArray.
     * Complexity: O(1).
     *
     * @param is a valid index to insert the item.
     * @returns item at the given index.
     * @throws java.lang.ArrayIndexOutOfBoundsException if an invalid ix is passed.
     */

    public T getAt(int ix) {
		if (isValidIndex(ix)) {
			return arr[ix];
		} else {
			throw new java.lang.ArrayIndexOutOfBoundsException("Invalid Index.");
		}
    }

    /**
     * Removes and returns the item at the end of the DynamicArray.
     * Complexity: O(1) amortized.
     *
     * @returns item at the end of the DynamicArray.
     * @throws java.util.NoSuchElementException if this method is invoked on a empty DynamicArray.
     */
   
    public T pop() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty Dynamic Array.");
		}
	
		T item = arr[size - 1];
		arr[size - 1] = null; // avoids loitering is removed
		--size;

		// reduces arr length by half if0 25% occupation is reached
		if (size < (float) arr.length/4) {
			resize(arr.length/2);
		}
	
		return item;
    }
    
    /**
     * Removes and returns the item at a given index of the DynamicArray.
     * Complexity: O(N).
     *
     * @param ix is a valid index.
     * @returns item at the index position of the DynamicArray.
     * @throws java.util.NoSuchElementException if this method is invoked on a empty DynamicArray.
     * @throws java.lang.ArrayIndexOutOfBoundsException if an invalid ix is passed. 
     */
    
    public T pop(int ix) {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty Dynamic Array.");
		}	
	
		if (!isValidIndex(ix)) {
			throw new java.lang.ArrayIndexOutOfBoundsException("Invalid Index.");
		}
	
		T item = arr[ix];

		// shifts elements to the left
		for (int i = ix; i < size - 1; ++i) {
			arr[i] = arr[i + 1];	    
		}
	
		arr[size - 1] = null; // after shifting to left last element is set to null (empty space)
		--size;

		// reduces arr length by half if 25% occupation is reached
		if (size < (float) arr.length/4) {
			resize(arr.length/2);
		}
	
		return item;
    }

    // resize helper method that copies every element of the current array into a new array
    // with a bigger of small capacity given by the argument newCapacity.    
    private void resize(int newCapacity) {
		T[] newArr = (T[]) new Object[newCapacity];

		// copy arr array into newArr array
		for (int i = 0; i < size; ++i) {
			newArr[i] = arr[i];
		}

		// change reference
		arr = newArr;
    }

    /**
     * Replaces an item at the given index position.
     * Complexity: O(1).
     *
     * @param ix is a valid index.
     * @param item is the item to replace the previous value located at the given index.
     * @throws java.util.NoSuchElementException if this method is invoked on a empty DynamicArray.
     * @throws java.lang.ArrayIndexOutOfBoundsException if an invalid ix is passed.
     */

    public void replaceAt(int ix, T item) {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException("Empty Dynamic Array.");
		}	
	
		if (!isValidIndex(ix)) {
			throw new java.lang.ArrayIndexOutOfBoundsException("Invalid Index.");
		}

		arr[ix] = item;
    }

    // helper method that returns true if an index is invalid.
    private boolean isValidIndex(int ix) {
		if (ix < 0 || ix >= size) {
			return false;
		} else {
			return true;
		}
    }

    // Iterable interface implementation for the DynamicArray data structure.
    public Iterator<T> iterator() {
		return new DynamicArrayIterator();
    }

    // Nested private class to create Iterator Objects for the DynamicArray data structure.
    // Iterator interface implementation.
    private class DynamicArrayIterator implements Iterator<T> {
		int iteratorIx, collectionSize;

		public DynamicArrayIterator() {
			iteratorIx = 0;
			collectionSize = size;
		}

		public boolean hasNext(){
			return iteratorIx < collectionSize;
		}

		public T next() {
			T item = arr[iteratorIx];
			++iteratorIx;
	    
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException("Unsafe operation not implemented.");
		}
    }
    
    // unit testing
    public static void main(String[] args) {
		DynamicArray<Integer> a = new DynamicArray<Integer>();

		for (int i = 0; i < 10; ++i) {
			a.append(i);
		}

		a.append(-5, 5);
		a.pop(5);
		a.pop();
		a.append(15);
	
		for (int i : a) {
			System.out.println(i);
		}
    }
}    
