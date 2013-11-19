/**
 * A simple model of binary functions as objects.
 */
public interface BinaryFunction<LeftInput,RightInput,Output> {
    /**
     * Apply the function to a value.
     */
    public Output apply(LeftInput left, RightInput right) throws Exception;
} // interface BinaryFunction<LeftInput,RightInput,Output>
