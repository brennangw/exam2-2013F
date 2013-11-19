/**
 * Some simple utilities for playing with linked lists.
 */
public class LLUtils {
    /**
     * Determine if a list contains the elements of an array, in a particular
     * order.
     */
    public static <T> boolean checkList(LinkedList<T> list, T[] array) {
	int i = 0;
	for (T val : list) {
	    if (!val.equals(array[i++])) {
		return false;
	    } // if
	} // for
	  // If we've run out of elements in the list, we should be at
	  // the end of the array.
	return (i == array.length);
    } // checkList(LinkedList<T>, T[])
} // class LLUtils
