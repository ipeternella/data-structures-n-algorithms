/**
 *
 * This class implements a dynamic array data structure. It implements a resize method
 * that copies the current array into a bigger/smaller one as necessary. This data
 * structure is iterable (i.e. it implements the Iterable interface).
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 * 
 */

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T>{

    private int INITIAL_SIZE = 1; // initial size of the array
    private T[] arr;              // the main array of this data structure
    private int size;             // the size of the collection

    public DynamicArray() {
	size = 0;
	arr = (T[]) new Object[INITIAL_SIZE];
    }
    
    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public void append(T item) {
	arr[size] = item;
	++size;

	// resizes to a new array if necessary
	if (size == arr.length) {
	    resize(2 * size);
	}
    }

    public void append(T item, int ix) {
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

    public T getAt(int ix) {
	if (isValidIndex(ix)) {
	    return arr[ix];
	} else {
	    throw new java.lang.ArrayIndexOutOfBoundsException("Invalid Index.");
	}
    }
   
    public T pop() {
	T item = arr[size - 1];
	arr[size - 1] = null; // item is removed
	--size;


	// reduces arr length by half if 25% occupation is reached
	if (size < (float) arr.length/4) {
	    resize(arr.length/2);
	}
	
	return item;
    }

    public T pop(int ix) {
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

        
    private void resize(int newCapacity) {
	T[] newArr = (T[]) new Object[newCapacity];

	// copy arr array into newArr array
	for (int i = 0; i < size; ++i) {
	    newArr[i] = arr[i];
	}

	// change reference
	arr = newArr;
    }

    private boolean isValidIndex(int ix) {
	if (ix < 0 || ix >= size) {
	    return false;
	} else {
	    return true;
	}
    }

    public Iterator<T> iterator() {
	return new DynamicArrayIterator();
    }

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
    
    // unit tests
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
