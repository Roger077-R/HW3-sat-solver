package expressions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Represents a binary boolean expression
 * 
 * Class invariants:
 * <ul>
 *  <li> a and b are not null</li>
 *  <li> op is not null</li>
 * </ul>
 * 
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
        if (!repOk()) {
            throw new IllegalStateException("Postcondition violated: representation invariant not maintained after construction");
        }
    }
    
    @Override
    /**
     * Evaluates this binary expression under a particular interpretation
     * @param interpretation the interpretation to use
     * @return the result of applying the binary operator to the evaluation of both sub-expressions
     * @throws IllegalArgumentException if {@code interpretation} is {@code null}
     * @throws IllegalArgumentException if either sub-expression is {@code null}
     * @throws IllegalArgumentException if the operator is {@code null}
     * @throws IllegalArgumentException if both sub-expressions have no variables
     */
    public boolean evaluate(Interpretation interpretation) {
        if(interpretation == null){
            throw new IllegalArgumentException("interpretation cannot be null");
        }
        if(this.a == null || this.b == null){
            throw new IllegalArgumentException("a and b cannot be null");
        }
        if(this.op == null){
            throw new IllegalArgumentException("op canot be  null");
        }
        if(this.a.variables().isEmpty() && this.b.variables().isEmpty()){
            throw new IllegalArgumentException("a and b have no variables");
        }
        switch (this.op) {
            case AND:
                return this.a.evaluate(interpretation) && this.b.evaluate(interpretation);
            case OR:
                return this.a.evaluate(interpretation) || this.b.evaluate(interpretation);
            default:
                throw new IllegalArgumentException("Invalid op, expected AND or OR");
        }
    }

    @Override
    /**
     * @return the union of all variables in both sub-expressions
     */
    public Set<String> variables() {
        return List.of(a.variables(), b.variables())
            .stream()
            .flatMap(s -> s.stream())
            .collect(Collectors.toSet());
    }
    
    @Override
    /**
     * @return a string representation of this binary expression in the format "left op right"
     */
    public String toString() {
        return a.toString() + " " + op.toString().toLowerCase() + " " + b.toString();
    }

    /**
     * Checks if the representation invariant is respected
     * @return {@code true} if the representation invariant is respected, {@code false} otherwise
     */
    public boolean repOk(){
        return this.a != null && this.b != null && this.op != null;
    }
}
