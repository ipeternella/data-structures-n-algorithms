/**
 * This class consists of an implementation of the Heap Sort algorithm.
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
 *              Heap.sort(arrayReference)
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Igor G. Peternella
 * @date 06-05-2017
 */
package sorting;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Heap {

    /** 
     * Sorts a Comparable array using Heap sort. This method uses the array
     * received as an argument to transform it into a representation of a
     * heap data structure.
     *
     * This algorithm requires one-index-based arrays and not zero-index-based arrays
     * such as Java's arrays. Hence, the indexes used by the algorithm are called virtual
     * indexes (for arrays that start at index 1) in contrast to the real indexes 
     * (for arrays that start at index 0).
     *
     * Given the fact that:        virtual index == real index - 1  
     * Methods isLess() and swap() have been implemented to subtract 1 from the
     * virtual indexes passed to these methods.
     *
     * Complexity: O(N*log(N)) swaps.
     *
     * @param arr is a reference to a Comparable array.
     */
	
    public static void sort(Comparable[] arr) {		
        int heapSize = arr.length;

        // builds a heap from a Comparable array elements
        // starts at half of the array to use sink() on each subheap
        // heapIndex is a virtual index of an abstract array that starts
        // at index one with the same elements of arr
        for (int heapIndex = heapSize/2; heapIndex >= 1; --heapIndex) {
            sink(arr, heapIndex, heapSize);
        }	

        // applies heap sort on the heap array
        while (heapSize > 1) {
            // swaps biggest element (first) with the last one so
            // last element goes to its right position at the end (sorted)
            swap(arr, 1, heapSize);
            // last element is sorted but it violates now the heap order
            // so we reduce the heap size to ignore it in the next iterations
            --heapSize;
            // sink from the beginning of the heap till the end to reheapify
            // the structure
            sink(arr, 1, heapSize);
        }
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
    
    private static void sink(Comparable[] arr, int heapIndex, int heapSize) {
        // begins going down the heap if the startLevel node (parent) has
        // at least a leftChild node so 2 * heapLevel < heapSize evals to true
        // sinks down until heapLevel is bigger than the heapSize
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
                if (isLess(arr, leftChild, rightChild)) {
                    biggestChild = rightChild;
                }
            }
	    
            // if biggestChild is smaller than parent
            // than it follows the heap order so break
            if (isLess(arr, biggestChild, parent)) {
                break;
            } else {
                // biggestChild is bigger than parent
                // which violates heap order so swap
                swap(arr, biggestChild, parent);
            }
	    	    
            // advances to biggest child
            // which will become the next parent
            // to move down on the heap for then next iteration
            heapIndex = biggestChild;
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
        // corrects virtual indexes of heap sort to
        // map to real indexes of 0 based java arrays
        --ix1;
        --ix2;
	
        Comparable temp = arr[ix1];
	
        arr[ix1] = arr[ix2];
        arr[ix2] = temp;
    }
    
    /*
     * Compares two Comparables objects of a Comparable array.
     * 
     * ix1 is the index of the first object to be compared.
     * ix2 is the index of the second object to be compared.
     * returns true if arr[ix1] is less than arr[ix2]. Returns
     * false otherwise.
     */
    
    private static boolean isLess(Comparable[] arr, int ix1, int ix2) {
        // corrects virtual indexes of heap sort to
        // map to real indexes of 0 based java arrays
        --ix1;
        --ix2;
	
        return arr[ix1].compareTo(arr[ix2]) < 0; // -1 when less
    }

    /**
     * Convenience method that checks if a Comparable array is sorted.
     *
     * @param arr is a reference to a Comparable array.
     * @return true if the array is sorted. Returns false otherwise.
     */
    
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            if (!isLess(arr, i, i + 1)) {
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
        Heap.sort(arr);
	
        System.out.println("Array AFTER sorting:");
        show(arr);

        System.out.println();
    }
}



