import java.util.Iterator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * SamR's tests for linked lists. (More tests may be forthcoming.)
 */
public class SamTest {
    // +---------------+---------------------------------------------------
    // | Primary Tests |
    // +---------------+

    /**
     * Test the stringToList helper below.
     */
    @Test
    public void testStringToList() throws Exception {
	check(stringToList(""), "");
	check(stringToList("a"), "a");
	check(stringToList("alpha"), "alpha");
    } // testStringToList

    /**
     * Test the construction of a simple sequence of lists.
     */
    @Test
    public void testConstruct() throws Exception {
	LinkedList<Character> lst = new LinkedList<Character>();
	Cursor cursor = lst.front();
	check(lst, "");

	lst.add(cursor, 'a');
	check(lst, "a");

	lst.add(cursor, 'b');
	check(lst, "ba");

	lst.advance(cursor);
	lst.add(cursor, 'c');
	check(lst, "bca");

	lst.advance(cursor);
	lst.advance(cursor);
	lst.add(cursor, 'd');
	check(lst, "bcad");

	lst.advance(cursor);
	lst.add(cursor, 'e');
	check(lst, "bcade");

	lst.add(cursor, 'f');
	check(lst, "bcadfe");

	cursor = lst.front();
	lst.add(cursor, 'g');
	check(lst, "gbcadfe");
    } // testConstruct()

    /**
     * Test removal from the front of a list.
     */
    @Test
    public void testRemoveFront() throws Exception {
	String str = "abcde";
	int len = str.length();
	LinkedList<Character> lst = stringToList(str);
	for (int i = 0; i < len; i++) {
	    Iterator<Character> it = lst.iterator();
	    it.next();
	    it.remove();
	    check(lst, str.substring(i+1));
	} // for
    } // testRemoveFront()

    /**
     * Test removal from the end of a list.
     */
    @Test
    public void testRemoveEnd() throws Exception {
	String str = "abcde";
	LinkedList<Character> lst = stringToList(str);
	for (int len = str.length() - 1; len >= 0; len--) {
	    // Iterate to the end of the list.
	    Iterator<Character> it = lst.iterator();
	    while (it.hasNext()) {
		it.next();
	    } // while (it.hasNext())
	      // We're at the end, remove it
	    it.remove();
	    // And check our answer
	    check(lst, str.substring(0, len));
	} // for
    } // testRemoveEnd()

    /**
     * Test removal from various points in the list.
     */
    @Test
    public void testRemoveVarious() throws Exception {
	LinkedList<Character> lst = stringToList("abbacbbabbxccab");
	removeAll(lst, 'a');
	check(lst, "bbcbbbbxccb");
	removeAll(lst, 'b');
	check(lst, "cxcc");
	removeAll(lst, 'c');
	check(lst, "x");
    } // testRemoveVarious

    /**
     * Test a combination of removal and addition.
     */
    @Test
    public void testRemoveAndAdd01() throws Exception {
	LinkedList<Character> lst = stringToList("abcde");
	Iterator<Character> it;
	Cursor cursor;

	// Remove the front and then add a new element at the front
	// and in the middle
	it = lst.iterator();
	it.next();
	it.remove();
	cursor = lst.front();
	lst.add(cursor, 'A');
	check(lst, "Abcde");
	lst.advance(cursor);
	lst.advance(cursor);
	lst.add(cursor, 'X');
	check(lst, "AbXcde");

	// Remove the second element and then add a few new elements.
	it = lst.iterator();
	it.next();
	it.next();
	it.remove();
	check(lst, "AXcde");
	cursor = lst.front();
	lst.add(cursor, 'Y');
	check(lst, "YAXcde");
	lst.advance(cursor);
	lst.advance(cursor);
	lst.add(cursor, 'Z');
	check(lst, "YAZXcde");
	lst.advance(cursor);
	lst.advance(cursor);
	lst.add(cursor, 'W');
	check(lst, "YAZXWcde");

	// Remove the first few elements
	it = lst.iterator();
	it.next();
	it.remove();
	it.next();
	it.remove();
	it.next();
	it.remove();
	it.next();
	it.remove();
	check(lst, "Wcde");

	// Remove the last element and insert at the end
	it = lst.iterator();
	it.next();
	it.next();
	it.next();
	it.next();
	it.remove();
	check(lst, "Wcd");
	cursor = lst.front();
	lst.advance(cursor);
	lst.advance(cursor);
	lst.advance(cursor);
	lst.add(cursor, 'E');
	check(lst, "WcdE");
    } // testRemoveAndAdd

    // +-----------+-------------------------------------------------------
    // | Utilities |
    // +-----------+

    /**
     * Remove all copies of a letter from a list of characters.
     */
    public void removeAll(LinkedList<Character> lst, char remove) {
	Iterator<Character> it = lst.iterator();
	while (it.hasNext()) {
	    Character c = it.next();
	    if (c == remove) {
		it.remove();
	    } // if
	} // while
    } // removeAll(LinkedList<Character>, c)

    /**
     * Given a string, build the list of characters that matches that string.
     */
    public LinkedList<Character> stringToList(String str) throws Exception {
	LinkedList<Character> lst = new LinkedList<Character>();
	Cursor cursor = lst.front();
	int len = str.length();
	for (int i = 0; i < len; i++) {
	    lst.add(cursor, str.charAt(i));
	    lst.advance(cursor);
	} // for
	return lst;
    } // stringToList(String)

    /**
     * Given a linked list of characters, determine if the sequence of
     * characters in the string are the same as expected.
     */
    public void check(LinkedList<Character> lst, String expected) {
	int i = 0;
	int len = expected.length();
	for (Character c : lst) {
	    if (i > len) {
		fail("too many elements in the list");
	    } // if
	    if (c != expected.charAt(i++)) {
		fail("Expected '" + expected.charAt(i - 1) + "' at position "
			+ i + " found '" + c + "'");
	    } // if the characters aren't the same
	} // for
	  // Did we consume all of the characters?
	if (i != len) {
	    fail("Too few elements in list");
	} // if (i != len)
    } // check(LinkedList<Character>, String)

} // SamTest
