import expressions.Expression;
import expressions.Interpretation;
import solver.SatSolver;

/**
 * Class to test SatSolver and related expressions.
 */
public class Main {
    private static final Expression PLACEHOLDER = Expression.createConstant(false);
    
    public static void main(String[] args) {
        final Main main = new Main();

        main.run(main.getExpression1());
        main.run(main.getExpression2());
        main.run(main.getExpression3());
        main.run(main.getExpression4());
        main.run(main.getExpression5());
        main.run(main.getExpression6());
        main.run(main.getExpression7());
    }

    public void run(Expression expr) {
        System.out.println("Expression: " + expr);
        System.out.println("Variables: " + String.join(", ", expr.variables()));

        System.out.println("All possible interpretations:");
        for(Interpretation interp : SatSolver.getAllInterpretations(expr)) {
            System.out.println(interp);
        }

        System.out.println("The expression is" + (SatSolver.isTautology(expr) ? "" : " NOT") + " a tautology");
        System.out.println("The expression is" + (SatSolver.isContradiction(expr) ? "" : " NOT") + " a contradiction");

        System.out.println("All interpretations that satisfies the expression:");
        for(Interpretation interp : SatSolver.allSatisfiableInterpretations(expr)) {
            System.out.println(interp);
        }

        System.out.println("All interpretations that do not satisfy the expression:");
        for(Interpretation interp : SatSolver.allUnsatisfiableInterpretations(expr)) {
            System.out.println(interp);
        }

        System.out.println("");
    }

    public Expression getExpression1() {
        Expression p = Expression.createVariableExpression("p");

        return PLACEHOLDER.or(p, PLACEHOLDER.not(p));
    }

    public Expression getExpression2() {
        Expression p = Expression.createVariableExpression("p");
        Expression q = Expression.createVariableExpression("q");

        return PLACEHOLDER.and(p, q);
    }

    public Expression getExpression3() {
        Expression q = Expression.createVariableExpression("q");

        return PLACEHOLDER.implies(Expression.createConstant(false), q);
    }

    public Expression getExpression4() {
        Expression p = Expression.createVariableExpression("p");

        return PLACEHOLDER.implies(p, p.not(p));
    }

    public Expression getExpression5() {
        Expression p = Expression.createVariableExpression("p");

        return PLACEHOLDER.and(p, p.not(p));
    }

    public Expression getExpression6() {
        Expression p = Expression.createVariableExpression("p");
        Expression q = Expression.createVariableExpression("q");
        Expression r = Expression.createVariableExpression("r");

        return PLACEHOLDER.xor(p, PLACEHOLDER.implies(q, r));
    }

    public Expression getExpression7() {
        Expression p = Expression.createVariableExpression("p");
        Expression q = Expression.createVariableExpression("q");

        return PLACEHOLDER.iff(PLACEHOLDER.not(PLACEHOLDER.or(p, q)), PLACEHOLDER.and(PLACEHOLDER.not(p), PLACEHOLDER.not(q)));
    }
}
