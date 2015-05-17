package ca.farleigh.decisiongenerator.decision.solver;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.Choice;
import ca.farleigh.decisiongenerator.decision.Decision;
import ca.farleigh.decisiongenerator.decision.DecisionStrategy;

/**
 * This strategy selects the single best choice for a decision. The idea here is
 * that you follow a single branch on the decision tree until you reach the best
 * possible outcome. This method will return the best choice.
 * 
 * @author Clinton Farleigh
 */
public class MaximumChoiceStrategy implements DecisionStrategy {

    /**
     * Find the best choice
     */
    @Override
    public Optional<Choice> decide(Decision decision) {
        Comparator<? super Choice> decider = (opt1, opt2) -> 
            opt1.calculate().orElse(BigDecimal.ZERO).compareTo(opt2.calculate().orElse(BigDecimal.ZERO));
        Collection<Choice> choices = decision.getChoices();
        return choices.stream().max(decider);
    }
}
