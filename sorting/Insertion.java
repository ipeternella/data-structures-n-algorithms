/**
 * This is a class that implements the elementary Insertion sort.
 * All methods are based on a Comparable type to ensure that objects
 * used by these methods have the compareTo() method.
 * The methods of this class are static so no instantiation is required
 * which makes the class more suited for practical use.
 * Based on the book Algorithms by Robert Sedgewick and Kevin Wayne.
 *
 * @author Igor G. Peternella
 * @date 06-01-2017
 */

public class Insertion {

    /**
     * Method that sorts a Comparable array using Insertion sort.
     * This sorting has average time complexity O(N^2) of exchanges.
     * This algorithm is sensitive to input. In the best case scenario
     * there are no exchanges. It is and inplace method and requires 
     * no extra space.
     *
     * @params arr is a Comparable array.
     */
    
    public static void sort(Comparable[] arr) {
	int size = arr.length;

	// i begins at index 1 and moves FORWARDS to the end
	for (int i = 1; i < size; ++i) {
	    // j moves BACKWARDS to the beginning starts at i - 1
	    for (int j = i; j > 0; --j) {
		// if a[i] < a[j] then swap
		if (isLess(arr[j], arr[j - 1])) {
		    swap(arr, j, j - 1);
		}
	    }
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
	System.out.println("Swapping: " + arr[ix1] + " <---> " + arr[ix2]);
	
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
	Integer[] arr = {3, 10, -1, 0, 5, 4, 15, 0, 7, 7};
	//String[] arr = {"zzz", "ccc", "ddd", "eee", "aaa", "iii"};
	
	show(arr);
	System.out.println("Insertion sorting...");
	Insertion.sort(arr);
	show(arr);
    }
}
