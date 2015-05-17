package ca.farleigh.decisiongenerator.combination;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import ca.farleigh.decisiongenerator.ExpectedValue;

/**
 * Abstract class that provides interface and implements shared behavior for 
 * dealing with a collection of ExpectedValues.  Subclasses are expected to override
 * the combine method to provide the combining behavior.
 * @author Clinton Farleigh
 *
 */
public abstract class AggregationCombiner implements ExpectedValue {

    private String operator;
    private Collection<? extends ExpectedValue> expectedValues;
    
    public AggregationCombiner() {
        this("");
    }
    
    public AggregationCombiner(final String name) {
        this(name, Collections.emptyList());
    }
    
    public AggregationCombiner(final String name, final Collection<? extends ExpectedValue> expectedValues) {
        this.setOperator(name);
        this.setExpectedValues(expectedValues);
    }
    
    public final void setOperator(final String name) {
        this.operator = name;
    }

    public String getOperator() {
        return this.operator;
    }
    
    public final void setExpectedValues(final Collection<? extends ExpectedValue> expectedValues) {
        this.expectedValues = expectedValues;
    }
    
    public Collection<? extends ExpectedValue> getExpectedValues() {
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
