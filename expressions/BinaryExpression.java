package expressions;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        if(interpretation == null){
            throw new IllegalArgumentException("interpretation is null");
        }
        if(this.a == null || this.b == null){
            throw new IllegalArgumentException("a or b is null");
        }
        if(this.op == null){
            throw new IllegalArgumentException("op is null");
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
                throw new IllegalArgumentException("op is not valid");
        }
    }

    @Override
    public Set<String> variables() {
        Stream<String> stream1 = this.a.variables().stream();
        Stream<String> stream2 = this.b.variables().stream();
        return Stream.concat(stream1, stream2).collect(Collectors.toSet());
    }
    
    @Override
    public String toString() {
        return a.toString() + " " + op.toString().toLowerCase() + " " + b.toString();
    }
}
