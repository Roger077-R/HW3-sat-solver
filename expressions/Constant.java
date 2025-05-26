package expressions;

import java.util.Set;

/**
 * Represents a boolean value, i.e.: true, or false
 * 
 * Class invariants:
 * <ul>
 *  <li> value is not null</li>
 * </ul>
 * 
 * @version 0.1
 */
class Constant implements Expression {

    private boolean value;

    /**
     * Constructs a new boolean value
     * @param value the internal boolean value this expression represents
     */
    Constant(boolean value) {
        this.value = value;
    }

    @Override
    /**
     * Evaluates this constant expression under a particular interpretation
     * @param interpretation the interpretation to use (must not be null)
     * @return the constant boolean value this expression represents
     * @throws IllegalArgumentException if {@code interpretation} is {@code null}
     */
    public boolean evaluate(Interpretation interpretation) {
        if (interpretation == null) {
            throw new IllegalArgumentException("interpretation cannot be null");
        }
        //We don't need to check if the interpretation is null because the constant is a boolean value.Because it's a constant.
        return this.value;
    }

    @Override
    /**
     * @return an empty set since a constant expression contains no variables
     */
    public Set<String> variables() {
        return Set.of();
    }
    
    @Override
    /**
     * @return "True" if this constant represents true, "False" otherwise
     */
    public String toString() {
        return value ? "True" : "False";
    }
}
