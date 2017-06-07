/**
 *
 * This class represents a Stack data structure.
 *
 * @author Igor G. Peternella
 * @date 05-30-2017
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T>{

    SinglyLinkedList<T> singlyLinkedList;
    int size;
    
    public Stack() {
	singlyLinkedList = new SinglyLinkedList<T>();
	size = 0;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public void push(T item) {
	singlyLinkedList.leftInsert(item);
	++size;
    }

    public T pop() {
	if (size == 0) {
	    throw new NoSuchElementException("Empty Stack.");
	}
	
	--size;

	return singlyLinkedList.removeAt(0);
    }

    public T peek() {
	return singlyLinkedList.getItemAt(0);
    }

    public T getAt(int ix) {
	if (!isValidIndex(ix)) {
	    throw new NoSuchElementException("Invalid stack index.");
	}
	
	return singlyLinkedList.getItemAt(ix);
    }

    private boolean isValidIndex(int ix) {
	if (ix < 0 || ix >= size) {
	    return false;
	} else {
	    return true;
	}
    }
    
    public Iterator<T> iterator() {
	return singlyLinkedList.iterator();
    }
    
    public static void main(String[] args) {
	Stack<Integer> s = new Stack<Integer>();

	s.push(1);
	s.push(2);
	s.push(3);
	s.pop();
	s.pop();
	s.pop();

	s.push(-5);
	s.push(-10);
	
	for (int i : s) {
	    System.out.println(i);
	}

	System.out.println(s.getAt(1));
    }
}
