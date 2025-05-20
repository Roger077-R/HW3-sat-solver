package solver;

import expressions.Expression;
import expressions.Interpretation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        List<String> variables = new ArrayList<>(expression.variables());
        return checkAllCasesForIsTautology(expression, variables, 0, new Interpretation());
    }

    /**
     * 
     * @param expression
     * @param variables
     * @param index
     * @param interpretation
     * @return {@code true} iff {@code expression.evaluate(interpretation)}
     * @throws IllegalArgumentException if {@code expression} is {@code null}
     * @throws IllegalArgumentException if {@code variables} is {@code null}
     * @throws IllegalArgumentException if {@code index} is out of bounds
     * @throws IllegalArgumentException if {@code interpretation} is {@code null}
     */
    private static boolean checkAllCasesForIsTautology(Expression expression, List<String> variables, int index, Interpretation interpretation) {
        if(expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        if(variables == null) {
            throw new IllegalArgumentException("variables is null");
        }
        if(index < 0 || index > variables.size()) {
            throw new IllegalArgumentException("index is out of bounds");
        }
        if(interpretation == null) {
            throw new IllegalArgumentException("interpretation is null");
        }
        
        if (index == variables.size()) {
            return expression.evaluate(interpretation);
        }
        String var = variables.get(index);
        interpretation.add(var, true);
        if (checkAllCasesForIsTautology(expression, variables, index + 1, interpretation)){
            return false;
        }
        interpretation.add(var, false);
        if (!checkAllCasesForIsTautology(expression, variables, index + 1, interpretation)) {
            return false;
        }
        return true;
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
        List<String> variables = new ArrayList<>(expression.variables());
        return !checkAllCasesForIsContradiction(expression, variables, 0, new Interpretation());
    }

    private static boolean checkAllCasesForIsContradiction(Expression expression, List<String> variables, int index, Interpretation interpretation) {
        if(expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        if(variables == null) {
            throw new IllegalArgumentException("variables is null");
        }
        if(index < 0 || index > variables.size()) {
            throw new IllegalArgumentException("index is out of bounds");
        }
        if(interpretation == null) {
            throw new IllegalArgumentException("interpretation is null");
        }
        
        if (index == variables.size()) {
            return expression.evaluate(interpretation);
        }
        String var = variables.get(index);
        interpretation.add(var, true);
        if (checkAllCasesForIsContradiction(expression, variables, index + 1, interpretation)){
            return true;
        }
        interpretation.add(var, false);
        if (checkAllCasesForIsContradiction(expression, variables, index + 1, interpretation)) {
            return true;
        }
        return false;
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
