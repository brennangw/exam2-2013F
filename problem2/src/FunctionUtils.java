/**
 * Some utilities for working with functions that are not yet implemented.
 */
public class FunctionUtils {
    /**
     * Given unary functions f and g, build the unary function f o g.  That
     * is, build the function (lambda (x) (f (g x))).
     * ...
     */
    public static <...> UnaryFunction<...> compose(final UnaryFunction<...> f,
            final UnaryFunction<...> g) {
        return new UnaryFunction<...>() {
            public ... apply(... arg) throws Exception {
                ...;
            } // apply
        }; // new UnaryFunction
    } // compose(UnaryFunction, UnaryFunction)
    
    /**
     * Given unary function f and binary function g, build the binary function 
     * f o g.  That is, build the function (lambda (x y) (f (g x y))).
     * ...
     */
    public static <...> BinaryFunction<...> compose(final UnaryFunction<...> f,
            final BinaryFunction<...> g) {
        return new BinaryFunction<...>() {
            public ... apply(... left, ... right) throws Exception {
                ...;
            } // apply
        }; // new BinaryFunction
    } // compose(Function)
    
    /**
     * Given a binary function, f, and two unary functions, g and h, build the
     * function (lambda (x,y) (f (g x) (h y)))
     * ...
     */
    public static <...> BinaryFunction<...> compose(final BinaryFunction<...> f,
            final UnaryFunction<...> g, final UnaryFunction<...> h) {
        return new BinaryFunction<...>() {
            public ... apply(... left, ... right) throws Exception {
                ...;
            } // apply
        }; // new BinaryFunction
    } // compose(Function)
    
    /**
     * Build a new function by filling in the left argument of f.
     */
    public static <...> UnaryFunction<...> leftSection(
            final BinaryFunction<...> f, final ... left) {
        return new UnaryFunction<...>() {
            public ... apply(... arg) throws Exception {
                ...;
            } // apply
        }; // new UnaryFunction
    } // leftSection
    
    /**
     * Build a new function by filling in the right argument of f.
     */
    public static <...> UnaryFunction<...> rightSection(
            final BinaryFunction<...> f, final ... right) {
        return new UnaryFunction<...>() {
            public ... apply(... arg) throws Exception {
                ...;
            } // apply
        }; // new UnaryFunction
    } // rightSection
 
    /**
     * Map a unary function over an array.
     *
     * @post
     *   For each i, 0 <= i < vals.length
     *     results[i] = fun.apply(vals[i])
     */
    public static <...> ...[] map(UnaryFunction<...> fun, ...[] vals) {
         ...[] results = (...[]) new Object[vals.length];
         ...;
         return result;
    } // map

    /**
     * Map a binary function over two arrays.
     *
     * @pre
     *   left.length == right.length
     * @post
     *   For each i, 0 <= i < vals.length
     *     results[i] = fun.apply(left[i], right[i])
     */
    public static <...> ...[] map(BinaryFunction<....> fun, ...[] left,
            ...[] right) {
        ....;
    } // map
} // class FunctionUtils
