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
        if (var == null) {
            throw new IllegalArgumentException("var is null");
        }
        if (var.isEmpty()) {
            throw new IllegalArgumentException("var is empty");
        }
        if (!checkFormat(var)) {
            throw new IllegalArgumentException("var has an invalid format or value");
        }
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
        if (var == null) {
            return false;
        }
        if (var.isEmpty()) {
            return false;
        }
        if (!Character.isLetter(var.charAt(0))) {
            return false;
        }
        for (int i = 1; i < var.length(); i++) {
            char c = var.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    

    @Override
    public boolean evaluate(Interpretation interpretation) {
        if (interpretation == null) {
            throw new IllegalArgumentException("interpretation cannot be null");
        }
        if (!interpretation.exists(this.var)) {
            throw new IllegalArgumentException("variable " + this.var + " does not exist in interpretation");
        }
        return interpretation.valueOf(this.var);
    }

    @Override
    public Set<String> variables() {
        return Set.of(this.var);
    }
    
    @Override
    public String toString() {
        return var;
    }
}
