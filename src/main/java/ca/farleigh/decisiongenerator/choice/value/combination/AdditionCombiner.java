package ca.farleigh.decisiongenerator.choice.value.combination;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;
import ca.farleigh.decisiongenerator.choice.value.impl.LiteralExpectedValue;

/**
 * Combine two ExpectedValues by adding them together
 * 
 * @author Clinton Farleigh
 *
 */
public final class AdditionCombiner extends BinaryCombiner {

    public AdditionCombiner() {
        this(LiteralExpectedValue.ZERO, LiteralExpectedValue.ZERO);
    }

    public AdditionCombiner(final ExpectedValue augend1, final ExpectedValue augend2) {
        super(augend1, augend2);
    }

    @Override
    protected Optional<BigDecimal> combine() {
        final BigDecimal operand1 = this.getOperand1().calculate().orElse(BigDecimal.ZERO);
        final BigDecimal operand2 = this.getOperand2().calculate().orElse(BigDecimal.ZERO);
        return Optional.of(operand1.add(operand2));
    }
    
    
}
