package expressions;

import expressions.BinaryExpression.BinaryOperator;
import java.util.Set;

/**
 * Represents a boolean expression.
 * @version 0.1
 */
public interface Expression {

    /**
     * Evaluates this expression under a particular interpretation
     * @param interpretation the interpretation to use
     * @return {@code true} if this expression can be satisfied under {@code interpretation}
     * @throws IllegalArgumentException if {@code interpretation} is {@code null}.
     * @throws IllegalArgumentException if {@code interpretation} does not provide all variables used in this expression.
     */
    public boolean evaluate(Interpretation interpretation);

    /**
     * @return all the variable names in this expression
     */
    public Set<String> variables();

    /**
     * Creates a boolean expression representing a constant boolean value
     * @param value the boolean value for the constant
     * @return an expression representing the constant expression {@code value}
     * @see {@link expressions.Constant}
     */
    public static Expression createConstant(boolean value) {
        return new Constant(value);
    }

    /**
     * Creates a new boolean variable expression
     * @param var the variable this expression represents
     * @return an expression representing a boolean variable.
     * @throws IllegalArgumentExpression if {@code var} is {@code null}
     * @throws IllegalArgumentExpression if {@code var} is empty
     * @throws IllegalArgumentExpression if {@code var} does not follow the format {@code <letter>(<letter-or-number>)*}
     * @see {@link expressions.Variable}
     */
    public static Expression createVariableExpression(String var) {
        if (var == null) {
            throw new IllegalArgumentException("var cannot be null");
        }

        if (!Variable.checkFormat(var)) {
            throw new IllegalArgumentException("var has an invalid format or value");
        }
        return new Variable(var);
    }

    /**
     * Creates a new expression as the negation of a given one
     * @param expr the expression to negate
     * @return an expression representing {@code not expr}
     * @throws IllegalArgumentException if {@code expr} is {@code null}
     */
    public default Expression not(Expression expr) {
        if (expr == null) {
            throw new IllegalArgumentException("expr cannot be null");
        }
        return new Negation(expr);
    }

    /**
     * Creates a new expression as the conjunction of two given expressions
     * @param left the left expression
     * @param right the right expression
     * @return an expression representing {@code left and right}
     * @throws IllegalArgumentException if {@code left} is {@code null}
     * @throws IllegalArgumentException if {@code right} is {@code null}
     */
    public default Expression and(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("left and right cannot be null");
        }
        return new BinaryExpression(left, right, BinaryOperator.AND);
    }

    /**
     * Creates a new expression as the disjunction of two given expressions
     * @param left the left expression
     * @param right the right expression
     * @return an expression representing {@code left or right}
     * @throws IllegalArgumentException if {@code left} is {@code null}
     * @throws IllegalArgumentException if {@code right} is {@code null}
     */
    public default Expression or(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("left and right cannot be null");
        }
        return new BinaryExpression(left, right, BinaryOperator.OR);
    }

    /**
     * Creates a new expression as the implication of two given expressions
     * @param antecedent the antecedent expression
     * @param consequent the consequent expression
     * @return an expression representing {@code antecedent implies consequent}
     * @throws IllegalArgumentException if {@code antecedent} is {@code null}
     * @throws IllegalArgumentException if {@code consequent} is {@code null}
     */
    public default Expression implies(Expression antecedent, Expression consequent) {
        if (antecedent == null || consequent == null) {
            throw new IllegalArgumentException("antecedent and consequent cannot be null");
        }
        return or(not(antecedent),consequent);
    }

    /**
     * Creates a new expression as the equivalence of two given expressions
     * @param left the left expression
     * @param right the right expression
     * @return an expression representing {@code left iff right}
     * @throws IllegalArgumentException if {@code left} is {@code null}
     * @throws IllegalArgumentException if {@code right} is {@code null}
     */
    public default Expression iff(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("left and right cannot be null");
        }
        return and(implies(left, right), implies(right, left));
    }

    /**
     * Creates a new expression as the exclusive or of two given expressions
     * @param left the left expression
     * @param right the right expression
     * @return an expression representing {@code left xor right}
     * @throws IllegalArgumentException if {@code left} is {@code null}
     * @throws IllegalArgumentException if {@code right} is {@code null}
     */
    public default Expression xor(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("left and right cannot be null");
        }

        Expression notRight = and(left, not(right));
        Expression notLeft = and(right, not(left));

        return or(notLeft,notRight);
    }
}