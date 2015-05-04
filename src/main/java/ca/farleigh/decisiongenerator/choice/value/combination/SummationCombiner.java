package ca.farleigh.decisiongenerator.choice.value.combination;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;

/**
 * Combine many expected values by summing them together
 * 
 * @author Clinton Farleigh
 *
 */
public class SummationCombiner extends AggregationCombiner {

    public SummationCombiner() {
        this(Collections.emptyList());
    }

    public SummationCombiner(Collection<ExpectedValue> expectedValues) {
        super(expectedValues);
    }

    @Override
    protected Optional<BigDecimal> combine() {
        final Collection<ExpectedValue> expectedValues = this.getExpectedValues();
        final Stream<BigDecimal> calculatedValues = expectedValues.parallelStream().map(
                ev -> ev.calculate().orElse(BigDecimal.ZERO));
        return calculatedValues.reduce((arg1, arg2) -> arg1.add(arg2));
    }

}
