package ca.farleigh.decisiongenerator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import ca.farleigh.decisiongenerator.ExpectedValue;
import ca.farleigh.decisiongenerator.LiteralExpectedValue;
import ca.farleigh.decisiongenerator.choice.Choice;
import ca.farleigh.decisiongenerator.combination.MultiplicationCombiner;
import ca.farleigh.decisiongenerator.combination.SummationCombiner;
import ca.farleigh.decisiongenerator.decision.Decision;
import ca.farleigh.decisiongenerator.decision.DecisionStrategy;
import ca.farleigh.decisiongenerator.decision.solver.MinimumChoiceStrategy;

public class SimpleDecisionTests {

    /**
     * A simple test to verify that minimum "cost" is selected.  This test also includes 
     * multipliers that are intended to indicate probabilities of outcomes occurring.
     */
    @Test
	public void testSimpleYesNoDecisionWithProbability() {
		// Need to provide 1) the decision options. 
        // 2) for each option, the expected gain and potential loss
        // Use multiplier to consider probability of each issue
		ExpectedValue positiveValueYes = new MultiplicationCombiner(LiteralExpectedValue.get(BigDecimal.valueOf(2)), LiteralExpectedValue.get(BigDecimal.valueOf(0.5)));
        ExpectedValue negativeValueYes = new MultiplicationCombiner(LiteralExpectedValue.get(BigDecimal.valueOf(-1)), LiteralExpectedValue.get(BigDecimal.valueOf(0.5)));
		List<ExpectedValue> yesValues = new ArrayList<ExpectedValue>();
		yesValues.add(positiveValueYes);
		yesValues.add(negativeValueYes);
		SummationCombiner positiveSummer = new SummationCombiner(yesValues);
        ExpectedValue positiveValueNo = new MultiplicationCombiner(LiteralExpectedValue.get(BigDecimal.valueOf(1)), LiteralExpectedValue.get(BigDecimal.valueOf(0.5)));
        ExpectedValue negativeValueNo = new MultiplicationCombiner(LiteralExpectedValue.get(BigDecimal.valueOf(-1)), LiteralExpectedValue.get(BigDecimal.valueOf(0.5)));
		List<ExpectedValue> noValues = new ArrayList<ExpectedValue>();
		noValues.add(positiveValueNo);
		noValues.add(negativeValueNo);
        SummationCombiner negativeSummer = new SummationCombiner(noValues);		
		Choice optionYes = new Choice("Yes", positiveSummer);
		Choice optionNo = new Choice("No", negativeSummer);
		List<Choice> decisionOptions = new ArrayList<Choice>();
		decisionOptions.add(optionYes);
		decisionOptions.add(optionNo);
		DecisionStrategy maxStrategy = new MinimumChoiceStrategy();
		Decision decision = new Decision(maxStrategy, "Yes or No?", decisionOptions);
		Optional<Choice> option = decision.decide();
		assertEquals(optionNo, option.get());
	}
}