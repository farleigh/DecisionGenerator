package ca.farleigh.decisiongenerator.combination;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.ExpectedValue;
import ca.farleigh.decisiongenerator.LiteralExpectedValue;

/**
 * Combine two expected values by dividing one by the other
 * 
 * @author Clinton Farleigh
 *
 */
public class DivisionCombiner extends BinaryCombiner {
    public DivisionCombiner() {
        this(LiteralExpectedValue.ZERO, LiteralExpectedValue.ZERO);
    }

    public DivisionCombiner(final ExpectedValue dividend, final ExpectedValue divisor) {
        super("/", dividend, divisor);
    }

    @Override
    public Optional<BigDecimal> combine() {        
        final Optional<BigDecimal> dividend = this.getOperand1().calculate();
        final Optional<BigDecimal> divisor = this.getOperand2().calculate();
        BigDecimal value = BigDecimal.ZERO;
        if (dividend.isPresent()) {
            value = dividend.get().divide(divisor.get());
        } else {
            // Assume that 0 / x and x / 0 are both zero.
            value = BigDecimal.ZERO;
        }
        return Optional.of(value);
    }
    
    
}
