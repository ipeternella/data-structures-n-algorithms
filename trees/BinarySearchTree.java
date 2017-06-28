import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class consists of an implementation of a Binary Search Tree data structure which
 * uses a helper nested class Node to represent the nodes of the tree. Each node 
 * contains a search Key and a value. This structure is also an implementation of an
 * Ordered Symbol Table with key, value pairs.
 *
 * This class uses the parametric K type for keys and V type for values.
 * This class has a reference to the root of the tree.
 *
 * [!] Make sure that Queue.java and its dependency SinglyLinkedList.java is in the same directory as this file [!]
 * (The queue is just used for a printing algorithm).
 *
 * Based on Algorithms (4th ed.) by Robert Sedgewick and Kevin Wayne.
 *
 * @author Igor G. Peternella
 * @date 06-20-2017
 */

public class BinarySearchTree<K extends Comparable<K>, V> {
    
    private Node root;       // Node reference to the root of the tree

    private class Node {
	K     key;           // key of the Node
	V     value;         // value of Node
	Node  left;          // reference to the left subtree
	Node  right;         // reference to the right subtree
	int   size;          // number of nodes below this node (inclusive)

	// Constructor of a Node	
	public Node(K key, V value, int size, Node left, Node right) {
	    this.key = key;
	    this.value = value;
	    this.size = size;
	    this.left = left;
	    this.right = right;
	}
    }

    /**
     * Default constructor. Intializes an empty BST whose root is null.
     */
    
    public BinarySearchTree() {
	root = null;
    }

    /**
     * Returns the size (number of nodes including root) of the BST.
     *
     * @return size of the root
     */
    
    public int size() {
	return size(root);
    }

    /*
     * Helper method that returns the size (number of nodes including root) of a subtree.
     * If the root points to null than the size returned is zero.
     * 
     * param x is the root of the subtree
     * return size of a subtree 
     */
    
    private int size(Node x) {
	if (x == null) { return 0; }
	
	return x.size;
    }
    
    /**
     * Returns true if the BST is empty.
     *
     * @return true if the structure is empty and false otherwise.
     */
    
    public boolean isEmpty() {
	return size() == 0;
    }

    /**
     * Inserts a node with its key and value on a BST.
     * Complexity: O(log(N)) compares on average.
     *
     * @param key is a Comparable key used for ordering the BST.
     * @value is the value held by the key.
     */
    
    public void put(K key, V value) {
	root = put(key, value, root);
    }

    /*
     * Helper recursive method to insert a new node on a BST.
     * It moves down on a tree (left subtree if the key to be added
     * is bigger than the current node's key or to the right subtree if the insertion
     * key is bigger than the current node's key. If the insertion key is the same as
     * current node's key we update this node's value with the value parameter (Search hit).
     * This allows no duplicates on the BST.
     *
     * If a null link is found then there's no value to be updated on the BST (Search miss).
     * This null link is the insertion point and a new Node is created and returned
     * to the previous recursion stages on the call stack. Each returned node is used
     * to overwrite it's parent Node left or right reference.
     *
     * @param key is a Comparable key used for ordered insertion of the BST.
     * @value is the value held by the key.
     * @return a node reference to change it's parent node left or right reference.
     */
    
    private Node put(K key, V value, Node x) {
	// base recursion case: null link is the insertion point: search miss
	if (x == null) return new Node(key, value, 1, null, null);
	
	// compare the value of key and x.key of the current node
	// key and x.key are Comparable types
	int cmp = key.compareTo(x.key);

	// if the insertion key < node's key then we go to the left subtree
        if      (cmp < 0) x.left = put(key, value, x.left);
	
	// if the insertion key > node's key then we go to the right subtree
	else if (cmp > 0) x.right = put(key, value, x.right);

	// insertion key == node's key so it updates node's value: search hit
	// cmp == 0
	else x.value = value; 

	// updates x.size based on left and right subtree sizes + 1 (new node)
	x.size = size(x.left) + size(x.right) + 1;
	
	// returns the current node to overwrite
	// it's parent left or right link
	return x;
    }

    /**
     * Searches for a node's value on the BST based on a given key.
     * Complexity: O(log(N)) compares on average.
     *
     * @param key is a Comparable key used searching on the BST.
     * @return value associated with given key on a search hit or null on a search miss.
     */

    public V get(K key) {
	return get(key, root).value;
    }

    /*
     * Helper recursive method to search for a node's value on a BST.
     * It moves down on a tree (left subtree if the search key is smaller than
     * the current node's key or to the right subtree if the search key is
     * bigger than the current node's key. Should the search key be the same as the
     * current node's key we return this node's value (Search hit).
     *
     * If a null link is found then there's no value for that given key 
     * on the BST. It returns null in this case. (Search miss).
     *
     * @param key is a Comparable key used for searching on the BST.
     * @return Node reference which holds the given key value (search hit) or null.
     */

    private Node get(K key, Node x) {
	// search miss: no node was found for the given key
	if (x == null) return null;
	
	// compares the value of key and x.key of the current node
	// key and x.key are Comparable types
	int cmp = key.compareTo(x.key);

	// if the search key is < node's key than we go to the left subtree
	if      (cmp < 0) return get(key, x.left);
	
	// if the search key is > node's key than we go to the right subtree
	else if (cmp > 0) return get(key, x.right);

	// search key == node's key so it returns node's value: search hit
	// cmp == 0
	else return x;
    }
    
    /**
     * Returns the minimum key of the BST. 
     *
     * @return minimum key of the BST.
     */

    public K min() {
	return min(root).key;
    }

    /*
     * Moves down to left subtrees until the last node (with min value) is found.
     */

    private Node min(Node x) {
	if (x.left == null) return x;
	else return min(x.left);
    }

    /**
     * Returns the maximum key of the BST. 
     *
     * @return maximum key of the BST.
     */

    public K max() {
	return max(root).key;
    }

    /*
     * Moves down to right subtrees until the last node (with max value) is found.
     */

    private Node max(Node x) {
	if (x.right == null) return x;
	else return max(x.right);
    }

    /**
     * Returns the floor of a given key on the BST. Floor is the largest key on the
     * BST but smaller than the given key or equal.
     *
     * @param key is the key whose floor is desired.
     * @return the floor key.
     */
    
    public K floor(K key) {
	Node retNode = floor(key, root);

	// empty tree nothing to test
	if (retNode == null) return null;
	
	return retNode.key;
    }

    /*
     * Helper recursive function to return the floor of a given key on the BST.
     *
     * param key is the key whose floor is desired
     * param x is the current root of the subtree
     * return the node with the floor key of the desired floorKey
     */

    private Node floor(K key, Node x) {
    	if (x == null) return null;
    	// compares 
    	int cmp = key.compareTo(x.key);

	// node's key is bigger than search key
    	// look at the left subtree for smaller key
    	if      (cmp < 0)  { return floor(key, x.left); }
    	// return this node if the same key has been found (floor is key which is less or EQUAL)
    	else if (cmp == 0) { return x; }
    	// node's key is smaller than search key = could be floor
	// look at the right subtree for bigger key
    	else {
    	    // local Node binding
    	    Node local = floor(key, x.right);

    	    // if local is null then the x reference
    	    // holds the floor key. Hence, return x.
    	    if (local == null) { return x; }

    	    // if local is not null return its value
    	    else               { return local; }
    	}
    }

    /*
     * Convenience method that prints a tree. Prints each complete level KEYS.
     * Uses a queue as a helper data structure.
     */
    
    private void printTree() {
	Queue<Node> q = new Queue<Node>();

	q.enqueue(root);

	while (!q.isEmpty()) {
	    Node actualNode = q.dequeue();

	    // prints key of the node
	    System.out.println(actualNode.key);

	    if (actualNode.left != null) 
		q.enqueue(actualNode.left);

	    if (actualNode.right != null)
		q.enqueue(actualNode.right);
	}
    }
    
    // unit testing
    public static void main(String[] args) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();

	// random data to insert, size = 10
	bst.put("S", 0);
	bst.put("E", 1);
	bst.put("A", 2);
	bst.put("R", 3);
	bst.put("C", 4);
	bst.put("H", 5);
	bst.put("E", 6); // updates Node with key = E
	bst.put("X", 7);
	bst.put("M", 9);
	bst.put("P", 10);
	bst.put("L", 11);

	//bst.printTree();

	// get a value
	// System.out.println(bst.get("L"));

	// get a updated value
	// System.out.println(bst.get("E"));

	// size test
	// System.out.println(bst.size());

	// min max test
	// System.out.println(bst.min());
	// System.out.println(bst.max());

	System.out.println(bst.floor("G"));
    }
}
