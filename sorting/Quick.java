/**
 * This class consists of an implementation of the Quick Sort algorithm.
 * All methods use the Comparable data type to ensure that objects used by the
 * methods of this class have implemented the Comparable interface i.e. this class can
 * be used to sort Integer, Double, String, etc. class types.
 * 
 * The sort() method of this class uses a Comparable[] array type because the O(1) 
 * indexing of the arrays. The sort() method is implemented in ascending order.
 *
 * The methods of this class are static so no instantiation is required
 * which makes the class more suitable for practical use, e.g., to sort a Comparable
 * array one could use the sort() method: 
 *
 *              Quick.sort(arrayReference)
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Igor G. Peternella
 * @date 06-05-2017
 */

public class Quick {

    /**
     * Wrapper method to call the overloaded private sort method. Makes
     * this class easier to use by taking just an array reference as an argument.
     * Complexity: O(N*log(N)) compares (isLess() operations)
     *
     * @param arr is a Comparable array.
     */
    
    public static void sort(Comparable[] arr) {
	sort(arr, 0, arr.length - 1);
    }
    
    /* 
     * Method that sorts a Comparable array using Quick sort.
     *
     * arr is a reference to a Comparable array.
     * lo is the lowest index of arr
     * hi is the highest index of arr
     */
    
    private static void sort(Comparable[] arr, int lo, int hi) {
	if (lo >= hi) { return; }
	
	// returns the partition index
	int p = partition(arr, lo, hi);
	// repeat for lower left array
	sort(arr, lo, p - 1);
	// repeat for lower right array
	sort(arr, p + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        Comparable pivot = arr[lo];
	int i = lo + 1;
	int j = hi;

	while (true) {
	    // keeps moving up until a number
	    // bigger than pivot is found (wrong position)
	    while (i <= hi && isLess(arr[i], pivot)) {		
		++i;
	    }

	    // keeps moving down until a number
	    // small than pivot is found (wrong position)
	    while (j >= 0 && isLess(pivot, arr[j])) {
		--j;
	    }

	    if (i >= j) { break; }
	    // swaps reversed numbers i.e. numbers smaller
	    // than the pivot stays at the left subarray
	    // and numbers higher stays at the right subarray
	    swap(arr, i, j);
	    //show(arr);
	}

	// j has the proper position for the pivot where
	// all numbers at the pivot's left are smaller and all numbers
	// at its right are higher than itself
	swap(arr, lo, j);

	return j;
    }
    
    /*
     * Swaps two elements of a Comparable array.
     * 
     * arr is a reference to a Comparable array.
     * ix1 is the index of the first element to be swapped on the array.
     * ix2 is the index of the second element to be swapped on the array.
     */    
    
    private static void swap(Comparable[] arr, int ix1, int ix2) {
	Comparable temp = arr[ix1];
	
	arr[ix1] = arr[ix2];
	arr[ix2] = temp;
    }
    
    /*
     * Compares two Comparables objects.
     * 
     * one is one of the objects to be compared.
     * another is the other object to be compared.
     * returns true if the one object is less than the another object. Returns
     * false otherwise.
     */
    
    private static boolean isLess(Comparable one, Comparable another) {
	return one.compareTo(another) < 0; // -1 when less
    }

    /**
     * Convenience method that checks if a Comparable array is sorted.
     *
     * @param arr is a reference to a Comparable array.
     * @return true if the array is sorted. Returns false otherwise.
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
     * Convenience method that prints to standard output all elements
     * of a Comparable array in an organized way, e.g., [1, 2, 3].
     * 
     * @param arr is a reference to a Comparable array.
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

    // unit testing
    public static void main(String[] args) {
	Integer[] arr = {3, 10, -1, 0, 5, 4, 15, 0, 7, 7};
	//String[] arr = {"zzz", "ccc", "ddd", "eee", "aaa", "iii"};

	System.out.println("\nArray BEFORE sorting:");
	show(arr);
	
	System.out.println("\nSorting the array...\n");
	Quick.sort(arr);
	
	System.out.println("Array AFTER sorting:");
	show(arr);

	System.out.println();
    }
}
