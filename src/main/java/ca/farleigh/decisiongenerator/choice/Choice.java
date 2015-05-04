package ca.farleigh.decisiongenerator.choice;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;
import ca.farleigh.decisiongenerator.choice.value.impl.LiteralExpectedValue;

/**
 * A choice can have further expected values. An expected value can be modeled
 * as a decision (requires user input), another choice, or another type of
 * expected value (such as a calculation or a literal expected value). A choice
 * really represents a wrapper around another type of expected value that
 * embellishes the expected value with a name (or textual identifier).
 * 
 * @author Clinton Farleigh
 */
public final class Choice implements ExpectedValue {

    private String name;
    private ExpectedValue expectedValue;

    /**
     * The empty default constructor. For a newly created object, the name will
     * be empty and the expected value will be 0.
     */
    public Choice() {
        this("");
    }

    /**
     * The name constructor that specifies the identifier for this choice. The
     * expected value will be 0 unless it is set via the setter.
     * 
     * @param name
     *            The name or identifier for the choice.
     */
    public Choice(final String name) {
        this(name, LiteralExpectedValue.ZERO);
    }

    /**
     * Constructor that takes the name and expected value of the object.
     * 
     * @param name
     *            An identifier for the choice (e.g. Eat more toast).
     * @param expectedValue
     *            The benefit or cost of the choice
     */
    public Choice(final String name, final ExpectedValue expectedValue) {
        this.name = name;
        this.expectedValue = expectedValue;
    }

    /**
     * Gets the identifier for this choice. An identifier can be a descriptive
     * statement such as "Eat more toast" or it can be an identifier to can be
     * used to find the appropriate text for presentation of the results.
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the identifier for this choice on the object.
     * 
     * @param name
     *            The name or textual identifier for the choice
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the expected value for this choice
     * 
     * @param expectedValue
     *            The expected benefit (or cost) of this choice
     */
    public void setExpectedValue(final ExpectedValue expectedValue) {
        this.expectedValue = expectedValue;
    }

    /**
     * Gets the value of the choice. This may be a literal or calculated value.
     */
    @Override
    public Optional<BigDecimal> calculate() {
        return this.expectedValue.calculate();
    }

    /**
     * Hash code for the expected value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expectedValue == null) ? 0 : expectedValue.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
	 * Verify equality
	 */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Choice other = (Choice) obj;
        if (expectedValue == null) {
            if (other.expectedValue != null)
                return false;
        } else if (!expectedValue.equals(other.expectedValue))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
