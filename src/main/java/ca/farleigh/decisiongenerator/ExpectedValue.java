package ca.farleigh.decisiongenerator;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The expected value interface. This is the basis for deciding upon which
 * choice to use
 * 
 * @author Clinton Farleigh
 *
 */
public interface ExpectedValue {
    
	/**
	 * Get the resulting calculated value
	 * @return Expected vale if it can be provided
	 */
	Optional<BigDecimal> calculate();
}