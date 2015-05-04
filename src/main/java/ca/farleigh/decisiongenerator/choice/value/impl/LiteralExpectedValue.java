package ca.farleigh.decisiongenerator.choice.value.impl;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;

/**
 * Represents a literal value.  Calculate returns the value itself.
 * 
 * @author Clinton Farleigh
 *
 */
public class LiteralExpectedValue implements ExpectedValue {
	
    public static final LiteralExpectedValue ZERO = new LiteralExpectedValue();
    
    /**
     * Factory method for LiteralExpectedValues.  Leave constructors public for now.
     * Later on, we can add some type of hashing of values.
     * @param value The bigdecimal value that the literalvalue will represent
     * @return A literalexpectedvalue that holds the value parameter.
     */
    public static LiteralExpectedValue get(final BigDecimal value) {
        return new LiteralExpectedValue(value);
    }
    
	private BigDecimal value;
	
	public LiteralExpectedValue() {
	    this(BigDecimal.ZERO);
	}
	
    public LiteralExpectedValue(BigDecimal value) {
        this.value = value;
    }
	
	public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

	public final Optional<BigDecimal> calculate() {
		return Optional.of(value);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LiteralExpectedValue other = (LiteralExpectedValue) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return this.calculate().orElse(BigDecimal.ZERO).toString();
    }
}
