package ca.farleigh.decisiongenerator.choice.value.combination;

import java.math.BigDecimal;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;

/**
 * Abstract class that provides interface and shared behavior for binary operations.  Subclasses
 * are expected to override the combine method to provide combining behavior.
 * @author Clinton Farleigh
 *
 */
public abstract class BinaryCombiner implements ExpectedValue {

    private ExpectedValue operand1;
    private ExpectedValue operand2;

    public BinaryCombiner(final ExpectedValue operand1, final ExpectedValue operand2) {
        this.setOperand1(operand1);
        this.setOperand2(operand2);
    }

    public ExpectedValue getOperand1() {
        return operand1;
    }

    public void setOperand1(final ExpectedValue operand1) {
        this.operand1 = operand1;
    }

    public ExpectedValue getOperand2() {
        return operand2;
    }

    public void setOperand2(final ExpectedValue operand2) {
        this.operand2 = operand2;
    }

    protected abstract Optional<BigDecimal> combine();

    @Override
    public Optional<BigDecimal> calculate() {
        return this.combine();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operand1 == null) ? 0 : operand1.hashCode());
        result = prime * result + ((operand2 == null) ? 0 : operand2.hashCode());
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
        BinaryCombiner other = (BinaryCombiner) obj;
        if (operand1 == null) {
            if (other.operand1 != null)
                return false;
        } else if (!operand1.equals(other.operand1))
            return false;
        if (operand2 == null) {
            if (other.operand2 != null)
                return false;
        } else if (!operand2.equals(other.operand2))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return this.calculate().orElse(BigDecimal.ZERO).toString();
    }
}
