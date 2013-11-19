/**
 * A simple model of unary functions as objects.
 */
public interface UnaryFunction<Input,Output> {
    /**
     * Apply the function to a value.
     */
    public Output apply(Input arg) throws Exception;
} // interface UnaryFunction<Input,Output>
