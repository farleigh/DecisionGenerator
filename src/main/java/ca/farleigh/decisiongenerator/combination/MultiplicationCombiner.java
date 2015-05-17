package ca.farleigh.decisiongenerator.combination;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.ExpectedValue;
import ca.farleigh.decisiongenerator.LiteralExpectedValue;

/**
 * Combine two expected values by multiplying them together
 * 
 * @author Clinton Farleigh
 *
 */
public class MultiplicationCombiner extends BinaryCombiner {
    public MultiplicationCombiner() {
        this(LiteralExpectedValue.ZERO, LiteralExpectedValue.ZERO);
    }

    public MultiplicationCombiner(ExpectedValue multiplicand1, ExpectedValue multiplicand2) {
        super("*", multiplicand1, multiplicand2);
    }

    @Override
    public Optional<BigDecimal> combine() {
        final Optional<BigDecimal> multiplicand1 = this.getOperand1().calculate();
        final Optional<BigDecimal> multiplicand2 = this.getOperand2().calculate();
        BigDecimal value = BigDecimal.ZERO;
        if (multiplicand1.isPresent() && multiplicand2.isPresent()) {
            value = multiplicand1.get().multiply(multiplicand2.get());
        } else if (multiplicand1.isPresent()) {
            value = multiplicand1.get();
        } else if (multiplicand2.isPresent()) {
            value = multiplicand2.get();
        }
        return Optional.of(value);
    }
}
