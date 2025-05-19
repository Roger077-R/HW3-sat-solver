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
        //TODO: complete implementation with necessary precondition checks
        throw new UnsupportedOperationException("Unimplemented method 'evaluate'");
    }

    @Override
    public Set<String> variables() {
        //TODO: complete implementation with necessary precondition checks
        throw new UnsupportedOperationException("Unimplemented method 'getAllVariables'");
    }
    
}
