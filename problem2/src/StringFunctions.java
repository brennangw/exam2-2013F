/**
 * Some silly string function objects.
 */
public class StringFunctions {
    // +------------------+------------------------------------------------
    // | Function Objects |
    // +------------------+

    /**
     * Concatenate two strings
     */
    public static final BinaryFunction<String, String, String> concat = new BinaryFunction<String, String, String>() {
	public String apply(String left, String right) {
	    return left + right;
	} // apply(String, String)
    }; // new BinaryFunction

} // StringFunctions
