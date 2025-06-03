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
        if (!repOk()) {
            throw new IllegalStateException("Postcondition violated: representation invariant not maintained after construction");
        }
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

    /**
     * Checks if the representation invariant is respected
     * @return {@code true} if the representation invariant is respected,and it will always be true
     */
    public boolean repOk(){
        return true;
    }
}