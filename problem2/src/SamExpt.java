import java.io.PrintWriter;

import java.util.Arrays;

/**
 * Silly experiments with function objects.
 */
public class SamExpt {
    // +------+------------------------------------------------------------
    // | Main |
    // +------+

    public static void main(String[] args) throws Exception {
	// Create a device for output
	PrintWriter pen = new PrintWriter(System.out, true);

	// The empty list
	demonstrateUnaryMap(pen, new Integer[0]);

	// A singleton array
	demonstrateUnaryMap(pen, new Integer[] { 5 });

	// A few element array
	demonstrateUnaryMap(pen, new Integer[] { -7, -2, 3, 5, 11 });

	// Binary map on the empty array
	demonstrateBinaryMap(pen, new Integer[] {}, new Integer[] {});

	// Binary map on a singleton array
	demonstrateBinaryMap(pen, new Integer[] { 1 }, new Integer[] { 2 });

	// Binary map on a few element array
	demonstrateBinaryMap(pen, new Integer[] { 1, 2, 3, 4, 5 },
		new Integer[] { 60, 70, 80, 90, 100 });

    } // main(String[])

    // +---------+---------------------------------------------------------
    // | Helpers |
    // +---------+

    /**
     * Apply a variety of unary functions to an array of values.
     */
    public static void demonstrateUnaryMap(PrintWriter pen, Integer[] values)
	    throws Exception {
	demonstrateUnaryMap(pen, "square", IntegerFunctions.square, values);
	demonstrateUnaryMap(pen, "adder(2)", IntegerFunctions.adder(2), values);
	demonstrateUnaryMap(pen, "adder(5)", IntegerFunctions.adder(5), values);
	demonstrateUnaryMap(pen, "leftSection(-,1)",
		FunctionUtils.leftSection(IntegerFunctions.subtract, 1), values);
	demonstrateUnaryMap(pen, "rightSection(-,1)",
		FunctionUtils.rightSection(IntegerFunctions.subtract, 1),
		values);
	demonstrateUnaryMap(pen, "compose(square,square)",
		FunctionUtils.compose(IntegerFunctions.square,
			IntegerFunctions.square), values);
	demonstrateUnaryMap(pen, "toString", IntegerFunctions.toString, values);
	demonstrateUnaryMap(pen, "compose(toString,square)",
		FunctionUtils.compose(IntegerFunctions.toString,
			IntegerFunctions.square), values);
	demonstrateUnaryMap(
		pen,
		"compose(leftSection(concat,'Squared:'), (compose(toString,square))",
		FunctionUtils.compose(FunctionUtils.leftSection(
			StringFunctions.concat, "Squared:"), FunctionUtils
			.compose(IntegerFunctions.toString,
				IntegerFunctions.square)), values);
	pen.println();
    } // demonstrateUnaryMap(PrintWriter, I[])

    /**
     * Apply a variety of binary functions to two array so integer values.
     */
    public static void demonstrateBinaryMap(PrintWriter pen, Integer[] left,
	    Integer[] right) throws Exception {
	demonstrateBinaryMap(pen, "multiply", IntegerFunctions.multiply, left,
		right);
	demonstrateBinaryMap(pen, "compose(concat,toString,toString)",
		FunctionUtils.compose(StringFunctions.concat,
			IntegerFunctions.toString, IntegerFunctions.toString),
		left, right);
	pen.println();
    } // demonstrateBinaryMap

    /**
     * Apply unary functions to an array of values, printing out the results.
     */
    public static <I, O> void demonstrateUnaryMap(PrintWriter pen, String name,
	    UnaryFunction<I, O> fun, I[] values) throws Exception {
	O[] results = FunctionUtils.map(fun, values);
	pen.println("map(" + name + "," + Arrays.toString(values) + ") = "
		+ Arrays.toString(results));
    } // demonstrateUnaryMap(PrintWRiter, UnaryFunction<I,O>, I[])

    /**
     * Apply binary functions to arrays of values, giving the results.
     */
    public static <L, R, O> void demonstrateBinaryMap(PrintWriter pen,
	    String name, BinaryFunction<L, R, O> fun, L[] left, R[] right)
	    throws Exception {
	O[] results = FunctionUtils.map(fun, left, right);
	pen.println("map(" + name + "," + Arrays.toString(left) + ","
		+ Arrays.toString(right) + ") = " + Arrays.toString(results));
    } // demonstrateBinaryMap(PrintWRiter, BinaryFunction<I,O>, I[])

} // class SamExpts
