package solver;

import expressions.Expression;
import expressions.Interpretation;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A class representing a sat solver.
 * @version 0.1
 */
public class SatSolver {

    /**
     * Checks if an expression is a tautology, i.e.: it is true under all interpretations
     * @param expression the expression to check
     * @return {@code true} iff {@code no interpretation i : not expression.evaluate(i)}
     * @throws IllegalArgumentException if {@code expression} is {@code null}
     */
    public static boolean isTautology(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }

        return getAllInterpretations(expression)
            .stream()
            .parallel()
            .allMatch(interp -> expression.evaluate(interp));
    }

    /**
     * Checks if an expression is a contradiction, i.e.: it is false under all interpretations
     * @param expression the expression to check
     * @return {@code true} iff {@code all interpretation i : not expression.evaluate(i)}
     */
    public static boolean isContradiction(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }

        return getAllInterpretations(expression)
            .stream()
            .parallel()
            .allMatch(interp -> !expression.evaluate(interp));
    }

    /**
     * Checks if an expression is satisfiable, i.e.: it is true under at least one interpretation
     * @param expression the expression to check
     * @return {@code true} iff {@code exists interpretation i : expression.evaluate(i)}
     */
    public static boolean isSatisfiable(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }

        return getAllInterpretations(expression)
            .stream()
            .parallel()
            .anyMatch(interp -> expression.evaluate(interp));
    }

    /**
     * Returns all possible interpretations under which an expression is satisfiable
     * @param expression the expression to check
     * @return {@code l : all i in l : expression.evaluate(i)}
     */
    public static List<Interpretation> allSatisfiableInterpretations(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }

        return getAllInterpretations(expression)
            .stream()
            .parallel()
            .filter(interp -> expression.evaluate(interp))
            .collect(Collectors.toList());
    }

    /**
     * Returns all possible interpretations under which an expression is unsatisfiable
     * @param expression the expression to check
     * @return {@code l : all i in l : not expression.evaluate(i)}
     */
    public static List<Interpretation> allUnsatisfiableInterpretations(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }

        return getAllInterpretations(expression)
            .stream()
            .parallel()
            .filter(interp -> !expression.evaluate(interp))
            .collect(Collectors.toList());
    }

    /**
     * @param expression the expression to check
     * @return all variable names in an expression
     */
    public static Set<String> getAllVariables(Expression expression) {
        
        return expression.variables();
    }

    /**
     * Returns all possible interpretations for a particular expression
     * @param expression the expression to use
     * @return all possible interpretations for {@code expression}
     */
    public static Set<Interpretation> getAllInterpretations(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        int n = expression.variables().size();
        int totalPossibilities = 1 << n;
        Set<String> variables = expression.variables();
        Set<Interpretation> result = new HashSet<>();

        for (int pos = 0; pos < totalPossibilities; pos++) {
            Interpretation interp = new Interpretation();
            Iterator<String> it = variables.iterator();
            for (int i = 0; i < n; i++) {
                boolean TFvalue = ((pos >> i) & 1) == 1;
                String var = it.next();
                interp.add(var, TFvalue);
            }
            result.add(interp);
        }
        return result;
    }

}
