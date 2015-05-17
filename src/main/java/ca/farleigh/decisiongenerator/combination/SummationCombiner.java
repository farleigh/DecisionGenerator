package ca.farleigh.decisiongenerator.combination;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import ca.farleigh.decisiongenerator.ExpectedValue;

/**
 * Combine many expected values by summing them together
 * 
 * @author Clinton Farleigh
 *
 */
public class SummationCombiner extends AggregationCombiner {

    private static final String SUMMATION_NAME = "sum";
    
    public SummationCombiner() {
        this(Collections.emptyList());
    }

    public SummationCombiner(Collection<? extends ExpectedValue> expectedValues) {
        super(SUMMATION_NAME, expectedValues);
    }

    @Override
    protected Optional<BigDecimal> combine() {
        final Collection<? extends ExpectedValue> expectedValues = this.getExpectedValues();
        final Stream<BigDecimal> calculatedValues = expectedValues.parallelStream().map(
                ev -> ev.calculate().orElse(BigDecimal.ZERO));
        return calculatedValues.reduce((arg1, arg2) -> arg1.add(arg2));
    }

}
