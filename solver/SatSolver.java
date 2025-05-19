package solver;

import java.util.List;
import java.util.Set;

import expressions.Expression;
import expressions.Interpretation;

/**
 * A class representing a sat solver.
 * @version 0.1
 */
public class SatSolver {

    /**
     * Checks if an expression is a tautology, i.e.: it is true under all interpretations
     * @param expression the expression to check
     * @return {@code true} iff {@code no interpretation i : not expression.evaluate(i)}
     */
    public static boolean isTautology(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * Checks if an expression is a contradiction, i.e.: it is false under all interpretations
     * @param expression the expression to check
     * @return {@code true} iff {@code all interpretation i : not expression.evaluate(i)}
     */
    public static boolean isContradiction(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * Checks if an expression is satisfiable, i.e.: it is true under at least one interpretation
     * @param expression the expression to check
     * @return {@code true} iff {@code exists interpretation i : expression.evaluate(i)}
     */
    public static boolean isSatisfiable(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * Returns all possible interpretations under which an expression is satisfiable
     * @param expression the expression to check
     * @return {@code l : all i in l : expression.evaluate(i)}
     */
    public static List<Interpretation> allSatisfiableInterpretations(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * Returns all possible interpretations under which an expression is unsatisfiable
     * @param expression the expression to check
     * @return {@code l : all i in l : not expression.evaluate(i)}
     */
    public static List<Interpretation> allUnsatisfiableInterpretations(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * @param expression the expression to check
     * @return all variable names in an expression
     */
    public static Set<String> getAllVariables(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }

    /**
     * Returns all possible interpretations for a particular expression
     * @param expression the expression to use
     * @return all possible interpretations for {@code expression}
     */
    public static Set<Interpretation> getAllInterpretations(Expression expression) {
        throw new UnsupportedOperationException("To be implemented");
    }
    
}
