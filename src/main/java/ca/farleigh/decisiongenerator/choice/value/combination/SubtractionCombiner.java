package ca.farleigh.decisiongenerator.choice.value.combination;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;
import ca.farleigh.decisiongenerator.choice.value.impl.LiteralExpectedValue;

/**
 * Combine two expected values by subtracting one from the other
 * 
 * @author Clinton Farleigh
 *
 */
public class SubtractionCombiner extends BinaryCombiner {

    public SubtractionCombiner() {
        this(LiteralExpectedValue.ZERO, LiteralExpectedValue.ZERO);
    }
    
    public SubtractionCombiner(ExpectedValue operand1, ExpectedValue operand2) {
        super(operand1, operand2);
    }

    @Override
    protected Optional<BigDecimal> combine() {
        final BigDecimal operand1 = this.getOperand1().calculate().orElse(BigDecimal.ZERO);
        final BigDecimal operand2 = this.getOperand2().calculate().orElse(BigDecimal.ZERO);
        return Optional.of(operand1.subtract(operand2));
    }
}
