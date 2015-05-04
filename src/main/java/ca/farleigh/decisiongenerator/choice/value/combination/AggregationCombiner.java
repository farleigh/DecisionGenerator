package ca.farleigh.decisiongenerator.choice.value.combination;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;

/**
 * Abstract class that provides interface and implements shared behavior for 
 * dealing with a collection of ExpectedValues.  Subclasses are expected to override
 * the combine method to provide the combining behavior.
 * @author Clinton Farleigh
 *
 */
public abstract class AggregationCombiner implements ExpectedValue {

    private Collection<ExpectedValue> expectedValues;
    
    public AggregationCombiner() {
        this(Collections.emptyList());
    }
    
    public AggregationCombiner(final Collection<ExpectedValue> expectedValues) {
        this.setExpectedValues(expectedValues);
    }
    
    public final void setExpectedValues(final Collection<ExpectedValue> expectedValues) {
        this.expectedValues = expectedValues;
    }
    
    public Collection<ExpectedValue> getExpectedValues() {
        return this.expectedValues;
    }
    
    @Override
    public Optional<BigDecimal> calculate() {
        return this.combine();
    }
    
    protected abstract Optional<BigDecimal> combine();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expectedValues == null) ? 0 : expectedValues.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AggregationCombiner other = (AggregationCombiner) obj;
        if (expectedValues == null) {
            if (other.expectedValues != null)
                return false;
        } else if (!expectedValues.equals(other.expectedValues))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return this.calculate().orElse(BigDecimal.ZERO).toString();
    }
}
