package ca.farleigh.decisiongenerator.combination;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import ca.farleigh.decisiongenerator.ExpectedValue;

/**
 * 
 * 
 * @author Clinton Farleigh
 */
public class CombinerFactory {

    public CombinerFactory() {
    }

    /**
     * Helper factory to ease in the construction of a binary combiner (two
     * operands).
     * 
     * @param operationName
     *            The operation's name
     * @param expectedValues
     *            Collection of operands
     * @return The binary combiner expected value resulting from the operands
     *         and operation
     */
    public Optional<? extends ExpectedValue> getCombiner(final String operationName, final Collection<? extends ExpectedValue> expectedValues) {
        ExpectedValue result = null;
        switch (operationName) {
        case "+":
            result = new AdditionCombiner(getFirstOperand(expectedValues), getSecondOperand(expectedValues));
            break;
        case "-":
            result = new SubtractionCombiner(getFirstOperand(expectedValues), getSecondOperand(expectedValues));
            break;
        case "/":
            result = new DivisionCombiner(getFirstOperand(expectedValues), getSecondOperand(expectedValues));
            break;
        case "*":
            result = new MultiplicationCombiner(getFirstOperand(expectedValues), getSecondOperand(expectedValues));
            break;
        case "sum":
            result = new SummationCombiner(expectedValues);
        }
        return Optional.of(result);
    }

    private static ExpectedValue getFirstOperand(final Collection<? extends ExpectedValue> expectedValues) {
        if (expectedValues == null) {
            return null;
        }
        Iterator<? extends ExpectedValue> it = expectedValues.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    private static ExpectedValue getSecondOperand(final Collection<? extends ExpectedValue> operands) {
        if (operands == null) {
            return null;
        }
        Iterator<? extends ExpectedValue> it = operands.iterator();
        if (it.hasNext()) {
            it.next();
        }
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
