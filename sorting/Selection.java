/**
 * This is a class that implements the elementary Selection sort.
 * All methods are based on a Comparable type to ensure that objects
 * used by these methods have the compareTo() method.
 * The methods of this class are static so no instantiation is required
 * which makes the class more suited for practical use. 
 * Based on the book Algorithms by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Igor G. Peternella
 * @date 06-01-2017
 */

public class Selection {

    /**
     * Method that sorts a Comparable array using Selection sort.
     * This sorting has time complexity O(N^2) of comparisons.
     * It is and inplace method and requires no extra space
     * complexity. Insensitive to input (arr) algorithm.
     *
     * @params arr is a Comparable array.
     */
    
    public static void sort(Comparable[] arr) {
	int size = arr.length;

	for (int i = 0; i < size; ++i) {
	    
	    // minIndex represents a[i] in the beginning
	    int minIndex = i; 
	    for (int j = i + 1; j < size; ++j) {

		// if a[j] is smaller than a[i] we save
		// j as the minIndex for the iterations
		if (isLess(arr[j], arr[minIndex])) {
		    minIndex = j;
		}		
	    }

	    // swap a[i], a[minIndex] of the array arr
	    swap(arr, i, minIndex);
	}
    }

    /**
     * Auxiliary method for swaping to elements of a Comparable array.
     * 
     * @params arr is a reference to a Comparable array; ix1 and ix2 are
     * the indexes of the elements to be swapped on the array.
     */
    
    public static void swap(Comparable[] arr, int ix1, int ix2) {
	Comparable temp = arr[ix1];
	arr[ix1] = arr[ix2];
	arr[ix2] = temp;
    }
    
    /**
     * Auxiliary method to compare two Comparable objects. Returns
     * true if object one is less than object another.
     * 
     * @params one and another are Comparable objects.
     * @returns true if one object is less than another object. Returns
     * false otherwise.
     */
    
    public static boolean isLess(Comparable one, Comparable another) {
	return one.compareTo(another) < 0; // -1 when less
    }

    /**
     * Convenience method that returns true if a Comparable array is sorted.
     *
     * @params arr is a reference to a Comparable array.
     * @returns true if the array is sorted. Returns falseo otherwise.
     */
    
    public static boolean isSorted(Comparable[] arr) {
	for (int i = 0; i < arr.length - 1; ++i) {
	    if (!isLess(arr[i], arr[i + 1])) {
		return false;
	    }
	}

	return true;
    }

    /**
     * Convenience method that shows all elements of a Comparable array.
     * 
     * @params arr is a reference to a Comparable array.
     */
    
    public static void show(Comparable[] arr) {
	System.out.print("[");
	for (int i = 0; i < arr.length; ++i) {
	    if (i != arr.length - 1) {
		System.out.print(arr[i] + ", ");
	    } else {
		System.out.println(arr[i] + "]");
	    }	    	    
	}
    }

    // unit tests
    public static void main(String[] args) {
	Integer[] arr = {3, 10, -1, 0, 5, 4, 15, 7};

	show(arr);
	System.out.println("Selection sorting...");
	Selection.sort(arr);
	show(arr);
    }
}
