/**
 * Some functions on integers.
 */
public class IntegerFunctions {
    // +------------------+------------------------------------------------
    // | Function Objects |
    // +------------------+

    /**
     * Square an integer.
     */
    public static final UnaryFunction<Integer, Integer> square = new UnaryFunction<Integer, Integer>() {
	public Integer apply(Integer arg) {
	    return arg * arg;
	} // apply(Integer)
    }; // new UnaryFunction

    /**
     * Multiply two integers.
     */
    public static final BinaryFunction<Integer, Integer, Integer> multiply = new BinaryFunction<Integer, Integer, Integer>() {
	public Integer apply(Integer left, Integer right) {
	    return left * right;
	} // apply(Integer, Integer)
    }; // new BinaryFunction

    /**
     * Subtract one integer from another.
     */
    public static final BinaryFunction<Integer, Integer, Integer> subtract = new BinaryFunction<Integer, Integer, Integer>() {
	public Integer apply(Integer left, Integer right) {
	    return left - right;
	} // apply(Integer, Integer)
    }; // new BinaryFunction

    /**
     * Convert to a string.
     */
    public static final UnaryFunction<Integer, String> toString = new UnaryFunction<Integer, String>() {
	public String apply(Integer val) {
	    return val.toString();
	} // apply(Integer)
    }; // new UnaryFunction

    // +----------------+--------------------------------------------------
    // | Static Methods |
    // +----------------+

    /**
     * Create a function that adds x to its argument.
     */
    public static final UnaryFunction<Integer, Integer> adder(final int x) {
	return new UnaryFunction<Integer, Integer>() {
	    public Integer apply(Integer arg) {
		return arg + x;
	    } // apply(Integer)
	}; // new UnaryFunction
    } // adder(int)

} // IntegerFunctions
