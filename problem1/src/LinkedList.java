import java.io.PrintWriter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple linked lists.
 */
public class LinkedList<T> implements Iterable<T> {
    // +-------+-----------------------------------------------------------
    // | Notes |
    // +-------+

    /*
     * These are simple, singly-linked lists. Each node has a pointer to the
     * next node. For fun, convenience, or confusion, we a dummy node at the
     * front of the list that also serves as the end of the list.
     */

    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The front/back of the list.
     */
    NodeLL<T> front;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new, empty, list.
     */
    public LinkedList() {
	this.clear();
    } // LinkedList

    // +-----------+-------------------------------------------------------
    // | Observers |
    // +-----------+

    /**
     * Get a cursor right before the first element of the list.
     */
    public Cursor front() {
	return new CursorLL<T>(front);
    } // front()

    /**
     * Get the value immediately following the cursor.
     */
    public T get(Cursor c) throws Exception {
	@SuppressWarnings("unchecked")
	CursorLL<T> cll = (CursorLL<T>) c;
	if (cll.current.next == front) {
	    throw new NoSuchElementException();
	} // if (cll.current.next == front)
	return cll.current.next.value;
    } // get(Cursor)

    /**
     * Advance the cursor over one element.
     * 
     * @pre The cursor must have been created by front and not be at the end of
     *      the list.
     * @throws Exception
     *             If the cursor is at the end of the list.
     */
    public void advance(Cursor c) throws Exception {
	@SuppressWarnings("unchecked")
	CursorLL<T> cll = (CursorLL<T>) c;
	if (cll.current.next == front) {
	    throw new NoSuchElementException();
	} // if (cll.current.next == front)
	cll.current = cll.current.next;
    } // advance

    /**
     * Determine if the cursor is at the end of the list.
     */
    public boolean atEnd(Cursor c) {
	@SuppressWarnings("unchecked")
	CursorLL<T> cll = (CursorLL<T>) c;
	return cll.current.next == front;
    } // atEnd(Cursor c)

    // +----------+--------------------------------------------------------
    // | Mutators |
    // +----------+

    /**
     * Add a new element immediately after the cursor.
     */
    public void add(Cursor c, T val) {
	@SuppressWarnings("unchecked")
	CursorLL<T> cll = (CursorLL<T>) c;
	cll.current.next = new NodeLL<T>(val, cll.current.next);
    } // add(Cursor, T)

    /**
     * Remove all elements from the list.
     * 
     * @post The list has no elements.
     * @post All iterators are invalid.
     */
    public void clear() {
	this.front = new NodeLL<T>(null);
	this.front.next = front;
    } // clear()

    // +-----------+-------------------------------------------------------
    // | Iterators |
    // +-----------+

    public Iterator<T> iterator() {
	return new IteratorLL<T>(this);
    } // iterator

} // LinkedList<T>

// +---------------+---------------------------------------------------
// | Inner Classes |
// +---------------+

/**
 * NodeLLs in the list.
 */
class NodeLL<T> {
    /**
     * The value in the node.
     */
    T value;

    /**
     * The next element in the list.
     */
    NodeLL<T> next;

    /**
     * Construct a new node with no successor.
     */
    public NodeLL(T val) {
	this(val, null);
    } // NodeLL(T)

    /**
     * Construct a new node with a specified successor.
     */
    public NodeLL(T value, NodeLL<T> next) {
	this.value = value;
	this.next = next;
    } // NodeLL(T, NodeLL<T>)
} // class NodeLL<T>

/**
 * Cursors for the linked list.
 */
class CursorLL<T> implements Cursor {
    /**
     * The node that *precedes* the node of interest.
     */
    NodeLL<T> current;

    /**
     * Create a new cursor.
     */
    public CursorLL(NodeLL<T> current) {
	this.current = current;
    } // CursorLL(NodeLL<T>)
} // class CursorLL<T>

/**
 * Iterators for linked lists.
 */
class IteratorLL<T> implements Iterator<T> {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * We may need to access some of the internals of the linked list class.
     */
    LinkedList<T> list;

    /**
     * For reasons that may eventually become obvious, we have a cursor that's
     * two elements back from the next element we iterate.
     */
    NodeLL<T> here;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    public IteratorLL(LinkedList<T> list) {
	this.here = new NodeLL<T>(null, list.front);
	this.list = list;
    } // IteratorLL

    // +---------+---------------------------------------------------------
    // | Methods |
    // +---------+

    public T next() {
	if (!this.hasNext()) {
	    throw new NoSuchElementException();
	} // if we've reached the end
	this.here = this.here.next;
	return this.here.next.value;
    } // next

    public boolean hasNext() {
	return (this.here.next.next != this.list.front);
    } // hasNext

    public void remove() throws UnsupportedOperationException {
	throw new UnsupportedOperationException();
    } // remove
} // class IteratorLL<T>
