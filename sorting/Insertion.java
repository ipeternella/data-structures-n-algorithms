/**
 * This class consists of an implementation of the elementary Insertion Sort algorithm.
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
 *              Insertion.sort(arrayReference)
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Igor G. Peternella
 * @date 06-05-2017
 */

package sorting;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Insertion {

    /**
     * Method that sorts a Comparable array using Insertion sort.
     * Complexity: O(N^2) compares (isLess() operations)
     *
     * @param arr is a Comparable array.
     */
    
    public static void sort(Comparable[] arr) {
        int size = arr.length;

        // i = 1 moves FORWARDS to i = size - 1
        for (int i = 1; i < size; ++i) {
            // j = i moves BACKWARDS to j = 1
            for (int j = i; j > 0; --j) {
                // if arr[j] < arr[j - 1] then swap
                if (isLess(arr[j], arr[j - 1])) {
                    swap(arr, j, j - 1);
                }
            }
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
        Insertion.sort(arr);
	
        System.out.println("Array AFTER sorting:");
        show(arr);

        System.out.println();
    }
}
