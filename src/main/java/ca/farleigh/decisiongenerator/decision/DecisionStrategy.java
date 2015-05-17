package ca.farleigh.decisiongenerator.decision;

import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.*;

/**
 * Represents the ability to configure how the decision is made.
 * 
 * @author Clinton Farleigh
 *
 */
public interface DecisionStrategy {
    // This will work for now - need some type of "result" that describes
    // more than just this.
    Optional<Choice> decide(Decision decision);
}
