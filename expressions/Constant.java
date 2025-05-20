package expressions;

import java.util.Set;

/**
 * Represents a boolean value, i.e.: true, or false
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
    public boolean evaluate(Interpretation interpretation) {
        if (interpretation == null) {
            throw new IllegalArgumentException("interpretation is null");
        }
        //We don't need to check if the interpretation is null because the constant is a boolean value.Because it's a constant.
        return this.value;
    }

    @Override
    public Set<String> variables() {
        //TODO: complete implementation with necessary precondition checks
        return Set.of();
    }
    
}
