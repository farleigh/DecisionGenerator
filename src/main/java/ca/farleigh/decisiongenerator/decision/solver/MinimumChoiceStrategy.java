package ca.farleigh.decisiongenerator.decision.solver;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.Choice;
import ca.farleigh.decisiongenerator.decision.Decision;
import ca.farleigh.decisiongenerator.decision.DecisionStrategy;

/**
 * This strategy minimizes the cost? of the choices in the decision. 
 * 
 * @author Clinton Farleigh
 *
 */
public class MinimumChoiceStrategy implements DecisionStrategy {

    @Override
    public Optional<Choice> decide(Decision decision) {
        Comparator<? super Choice> decider = (opt1, opt2) -> opt1.calculate().orElse(BigDecimal.ZERO)
                .compareTo(opt2.calculate().orElse(BigDecimal.ZERO));
        Collection<Choice> choices = decision.getChoices();
        return choices.stream().min(decider);
    }

}
