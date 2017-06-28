/**
 * This class consists of an implementation of the Merge Sort algorithm.
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
 *              Merge.sort(arrayReference)
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Igor G. Peternella
 * @date 06-05-2017
 */

public class Merge {

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
     * Method that sorts a Comparable array using Merge sort.
     *
     * arr is a reference to a Comparable array.
     * lo is the lowest index of arr
     * hi is the highest index of arr
     */
    
    private static void sort(Comparable[] arr, int lo, int hi) {
		// base case to stop recursion (abstract array has only one element)
		if (hi <= lo) { return; }

		// computes mid index based on lo and hi indexes
		int mid = lo + (hi - lo)/2;
	
		// sort method creates two abstract in place arrays recursively
		// i.e. we can think of the arr reference as a reference to an array
		// composed of two subarrays:
		// left subarray [lo .. mid] index
		// right subarray [mid + 1 .. hi] index
	
		// abstract left array
		sort(arr, lo, mid);	
		// abstract right array
		sort(arr, mid + 1, hi);

		// merge an array composed of two abstract in place subarrays
		merge(arr, lo, mid, hi);
    }

    /*
     * Merges two abstract ordered subarrays into an ordered one.
     * This is an inplace method that changes arr reference with the
     * help of a copy auxiliar array
     * 
     * arr is a reference to a Comparable array.
     * lo is the lowest index of arr
     * mid is the mid index of arr
     * hi is the highest index of arr
     */
    
    private static void merge(Comparable[] arr, int lo, int mid, int hi) {
		Comparable[] aux = new Comparable[arr.length];
		int i = lo;           // beginning of the left inplace subarray
		int j = mid + 1;      // beginning of the right inplace subarray
		int k = lo;           // lower index of the abstract array

		// creates aux array as a copy
		for (int w = lo; w <= hi; ++w) {
			aux[w] = arr[w];
		}
	
		// when the left array or right array have no more elements
		// to merge (exhausted) this loop breaks
		while (i <= mid && j <= hi) {
			if (isLess(aux[i], aux[j])) {
				// aux[i] < aux[j]
				// we pick minimum value (i.e. aux[i]) of the left subarray of aux
				arr[k++] = aux[i++];
			} else {
				// aux[i] > aux[j]
				// we pick minimum value (i.e. aux[j]) of the right subarray of aux
				arr[k++] = aux[j++];
			}
		}

		// fill with the remaining elements of the left subarray
		while (i <= mid) {
			arr[k++] = aux[i++];
		}
	
		// fill with the remaining elements of the right subarray
		while (j <= hi) {
			arr[k++] = aux[j++];
		}
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
		System.out.println("Swapping: " + arr[ix1] + " <---> " + arr[ix2]);
	
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
		Merge.sort(arr);
	
		System.out.println("Array AFTER sorting:");
		show(arr);

		System.out.println();
    }
}
