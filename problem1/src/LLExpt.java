import java.io.PrintWriter;
import java.util.Arrays;

/**
 * A quick experiment with linked lists.
 */
public class LLExpt {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The primary list we use.
     */
    static LinkedList<String> list;

    /**
     * Our handy-dandy output utility.
     */
    static PrintWriter pen;

    // +------+------------------------------------------------------------
    // | Main |
    // +------+

    public static void main(String[] args) throws Exception {
	list = new LinkedList<String>();
	pen = new PrintWriter(System.out, true);

	verboseExpt();
    } // main(String[])

    // +-------------+-----------------------------------------------------
    // | Experiments |
    // +-------------+

    /**
     * A verbose experiment. (Yay reading.)
     */
    public static void verboseExpt() throws Exception {
	Cursor cursor = list.front();

	report("Empty list", "");

	list.add(cursor, "a");
	report("Prepend a", "a");

	list.add(cursor, "b");
	report("Prepend b ", "ba");

	list.advance(cursor);
	list.add(cursor, "c");
	report("Advance and add c", "bca");

	list.advance(cursor);
	list.advance(cursor);
	list.add(cursor, "d");
	report("Advance, advance, and add d", "bcad");

	list.add(cursor, "e");
	report("Add e", "bcaed");

	cursor = list.front();
	list.add(cursor, "f");
	report("Prepend f", "fbcaed");

	while (!list.atEnd(cursor)) {
	    list.advance(cursor);
	} // while
	list.add(cursor, "g");
	report("Append g", "fbcaedg");
    } // verboseExpt

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Print the list.
     */
    public static void report(String prefix, String expected) {
	// Extract the expected elements
	String[] elements = expected.split("");
	elements = Arrays.copyOfRange(elements, 1, elements.length);

	if (LLUtils.checkList(list, elements)) {
	    pen.print(prefix + " (OK): ");
	} else {
	    pen.print(prefix + " (NO): ");
	} // if

	for (String str : list) {
	    pen.print(str);
	    pen.print(" ");
	} // for
	pen.println();
    } // report(String)

} // class LLExpt
