package expressions;

import java.util.Set;

/**
 * Represents a boolean variable.
 * The variable must follow the format {@code <letter>(<letter-or-number>)*},
 * i.e.: a letter followed by zero or more letters or numbers
 * @version 0.1
 */
class Variable implements Expression {

    private String var;

    /**
     * Constructs a new variable expression
     * @param var the variable associated with this expression
     * @throws IllegalArgumentException if {@code var} doesn't follow the following conditions
     * <ul>
     * <li>is not {@code null}</li>
     * <li>is not empty</li>
     * <li>follows the format {@code <letter>(<letter-or-number>)*}, i.e.: a letter followed by zero or more letters or numbers</li>
     * </ul>
     */
    Variable(String var) {
        //TODO: implement preconditions with exceptions
        this.var = var;
    }

    /* (non-javadoc)
     * @param var the variable to check
     * @return {@code true} iff {@code var} complies with all these conditions:
     * <ul>
     * <li>is not {@code null}</li>
     * <li>is not empty</li>
     * <li>follows the format {@code <letter>(<letter-or-number>)*}, i.e.: a letter followed by zero or more letters or numbers</li>
     * </ul>
     */
    static boolean checkFormat(String var) {
        throw new UnsupportedOperationException("Not implemented yet");
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
