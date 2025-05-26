package expressions;

import java.util.Set;

/**
 * Represents a negated boolean expression
 * 
 * Class invariants:
 * <ul>
 *  <li> expression is not {@code null}</li>
 * </ul>
 * 
 * @version 0.1
 */
class Negation implements Expression {

    private Expression expression;

    /**
     * Constructs a new negated expression
     * @param expression the expression to negate
     * @throws IllegalArgumentException if {@code expression} is {@code null}
     */
    Negation(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression cannot be null");
        }
        this.expression = expression;
    }

    @Override
    /**
     * Evaluates this negated expression under a particular interpretation
     * @param interpretation the interpretation to use
     * @return the logical negation of the evaluation of the sub-expression
     * @throws IllegalArgumentException if {@code interpretation} is {@code null}
     */
    public boolean evaluate(Interpretation interpretation) {
        if(interpretation == null){
            throw new IllegalArgumentException("interpretation cannot be null");
        }
        return !this.expression.evaluate(interpretation);
    }

    @Override
    /**
     * @return the set of variables in the negated expression
     */
    public Set<String> variables() {
        return this.expression.variables();
    }

    @Override
    /**
     * @return a string representation of this negation in the format "(not expr)"
     */
    public String toString() {
        return "(not " + expression.toString() + ")";
    }
}
