package expressions;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents an interpretation of a boolean expression
 * 
 * Class invariants:
 * <ul>
 *  <li> interpretation is not {@code null}</li>
 *  <li> keys in interpretation are not {@code null} and are String</li>
 *  <li> keys in interpretation are not empty</li>
 *  <li> values in interpretation are not {@code null} and are Boolean</li>
 * </ul>
 * 
 */
public class Interpretation implements Cloneable {

    private Map<String, Boolean> interpretation;

    /**
     * Constructs a new empty interpretation.
     */
    public Interpretation() {
        interpretation = new TreeMap<>((String a, String b) -> {return a.compareTo(b);});
        if (!repOk()) {
            throw new IllegalStateException("Failed to initialize interpretation map");
        }
    }

    /**
     * Construct a new interpretation from a list of variable and a bit set of their corresponding boolean values
     * @param variables the variables for this interpretation
     * @param booleanValues the boolean values for each variable in {@code variables} in the corresponding order.
     * @throws IllegalArgumentException if {@code variables} is {@code null}
     * @throws IllegalArgumentException if {@code booleanValues} is {@code null}
     * @throws IllegalArgumentException if {@code variables.size() != booleanValues.size()}
     * @throws IllegalArgumentException if there is a variable in {@code variables} such that:
     * <ul>
     * <li>is {@code null}</li>
     * <li>is empty</li>
     * <li>doesn't follows the format <letter>(<letter-or-number>)*, i.e.: a letter followed by zero or more letters or numbers</li>
     * <li>is repeated</li>
     * </ul>
     * <hr>
     * For every index {@code i} between {@code 0} and {@code variables.size() - 1} variable
     * {@code v} at {@code variables.get(i)} will be mapped to boolean value {@code booleanValues.get(i)}
     */
    public Interpretation(List<String> variables, BitSet booleanValues) {
        if (variables == null) {
            throw new IllegalArgumentException("variables is null");
        }
        if (booleanValues == null) {
            throw new IllegalArgumentException("booleanValues is null");
        }
        if (variables.size() != booleanValues.length()) {
            throw new IllegalArgumentException("variables and booleanValues have different sizes");
        }

        this.interpretation = new TreeMap<>((String a, String b) -> {return a.compareTo(b);});

        if (!repOk()) {
            throw new IllegalStateException("Failed to initialize interpretation map");
        }
    }

    /**
     * Adds a variable with an associated boolean value.
     * If the variable is already in the interpretation, it's value will be modified.
     * @param var the variable
     * @param value the associated boolean value
     * @throws IllegalArgumentException if {@code var} doesn't follow the following conditions
     * <ul>
     * <li>is not {@code null}</li>
     * <li>is not empty</li>
     * <li>follows the format <letter>(<letter-or-number>)*, i.e.: a letter followed by zero or more letters or numbers</li>
     * </ul>
     */
    public void add(String var, boolean value) {
        if (var == null) {
            throw new IllegalArgumentException("var is null");
        }
        if (var.isEmpty()) {
            throw new IllegalArgumentException("var is empty");
        }
        if (!Variable.checkFormat(var)) {
            throw new IllegalArgumentException("var has an invalid format or value");
        }
        
        interpretation.put(var, Boolean.valueOf(value));
    }

    /**
     * Checks if a variable is part of this interpretation
     * @param var the variable to check
     * @return {@code true} iff {@code var} is part of this interpretation
     * @throws IllegalArgumentException if {@code var} doesn't follow the following conditions
     * <ul>
     * <li>is not {@code null}</li>
     * <li>is not empty</li>
     * <li>follows the format <letter>(<letter-or-number>)*, i.e.: a letter followed by zero or more letters or numbers</li>
     * </ul>
     */
    public boolean exists(String var) {
        if (var == null) {
            throw new IllegalArgumentException("var is null");
        }
        if (var.isEmpty()) {
            throw new IllegalArgumentException("var is empty");
        }
        if (!Variable.checkFormat(var)) {
            throw new IllegalArgumentException("var has an invalid format or value");
        }
        return interpretation.containsKey(var);
    }

    /**
     * Returns the associated boolean value of a variable in this interpretation
     * @param var the variable to check
     * @return the associated boolean value for {@code var} under this interpretation
     * @throws IllegalArgumentException if {@code var} doesn't follow the following conditions
     * <ul>
     * <li>is not {@code null}</li>
     * <li>is not empty</li>
     * <li>follows the format <letter>(<letter-or-number>)*, i.e.: a letter followed by zero or more letters or numbers</li>
     * </ul>
     * @throws IllegalArgumentException if {@code var} doesn't exist in this interpretation
     */
    public boolean valueOf(String var) {
        if (var == null) {
            throw new IllegalArgumentException("var cannot be null");
        }
        if (var.isEmpty()) {
            throw new IllegalArgumentException("var cannot be empty");
        }
        if (!Variable.checkFormat(var)) {
            throw new IllegalArgumentException("var has an invalid format or value");
        }
        if (!interpretation.containsKey(var)) {
            throw new IllegalArgumentException("var does not exist in this interpretation");
        }
        
        return interpretation.get(var);
    }

    @Override
    public Object clone() {
        Interpretation clone = new Interpretation();
        for (Map.Entry<String, Boolean> entry : interpretation.entrySet()) {
            clone.interpretation.put(new String(entry.getKey()), Boolean.valueOf(entry.getValue()));
        }
        return clone;
    }
    
    @Override
    public String toString() {
        ArrayList<String> singleVariableInterpretation = new ArrayList<>();
        int maxWidth = 0;

        for(Map.Entry<String, Boolean> entry : interpretation.entrySet()) {
            String s;
            if (entry.getValue()) {
                s = entry.getKey() + ": True  ";
            } else {
                s = entry.getKey() + ": False ";
            }

            maxWidth = Math.max(maxWidth, s.length());
            singleVariableInterpretation.add(s);
        }

        return String.join("| ", singleVariableInterpretation);
    }
   
    public boolean repOk(){
        if(this.interpretation == null){
            return false;
        }
        for(Map.Entry<String, Boolean> entry : interpretation.entrySet()){
            if(entry.getKey() == null || entry.getValue() == null){
                return false;
            }
            if(entry.getKey().isEmpty()){
                return false;
            }
            if(!Variable.checkFormat(entry.getKey())){
                return false;
            }
            if(!(entry.getValue() instanceof Boolean)){
                return false;
            }
        }
        return true;
    }
}
