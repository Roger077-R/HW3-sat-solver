package expressions;

import java.util.Set;

/**
 * Represents a negated boolean expression
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
            throw new IllegalArgumentException("expression is null");
        }
        this.expression = expression;
    }

    @Override
    public boolean evaluate(Interpretation interpretation) {
        if(interpretation == null){
            throw new IllegalArgumentException("interpretation is null");
        }
        return !this.expression.evaluate(interpretation);
    }

    @Override
    public Set<String> variables() {
        //TODO: complete implementation with necessary precondition checks
        return this.expression.variables();
    }

}
