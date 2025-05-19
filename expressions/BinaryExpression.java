package expressions;

import java.util.Set;

/**
 * Represents a binary boolean expression
 * @version 0.1
 */
class BinaryExpression implements Expression {

    private Expression a;
    private Expression b;
    private BinaryOperator op;

    /* (non-javadoc)
     * An enumeration of all possible binary operator for this
     * kind of expressions
     */
    enum BinaryOperator {
        AND,
        OR,
    }

    /**
     * Constructs a new binary expression
     * @param a the left sub-expression
     * @param b the right sub-expression
     * @param op the operator for this binary expression
     */
    BinaryExpression(Expression a, Expression b, BinaryOperator op) {
        this.a = a;
        this.b = b;
        this.op = op;
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
