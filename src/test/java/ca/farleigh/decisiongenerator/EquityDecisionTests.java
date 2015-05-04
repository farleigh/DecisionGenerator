package ca.farleigh.decisiongenerator;

import java.math.BigDecimal;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.farleigh.decisiongenerator.choice.Choice;
import ca.farleigh.decisiongenerator.choice.value.combination.AdditionCombiner;
import ca.farleigh.decisiongenerator.choice.value.combination.DivisionCombiner;
import ca.farleigh.decisiongenerator.choice.value.combination.MultiplicationCombiner;
import ca.farleigh.decisiongenerator.choice.value.combination.SubtractionCombiner;
import ca.farleigh.decisiongenerator.choice.value.impl.LiteralExpectedValue;
import ca.farleigh.decisiongenerator.decision.Decision;
import ca.farleigh.decisiongenerator.solver.impl.MaximumChoiceStrategy;

/**
 * Tests around making decisions about equities.
 */
public class EquityDecisionTests {
	
    /**
     * A simple test to decide whether I should buy Apple Inc. Shares
     */
	@Test
	public void shouldBuyAAPL() {
	    LiteralExpectedValue price = LiteralExpectedValue.get(BigDecimal.valueOf(130.28)); // Closing price April 24, 2015
	    LiteralExpectedValue earningsPerQuarter = LiteralExpectedValue.get(BigDecimal.valueOf(2.33));
	    LiteralExpectedValue growthRate = LiteralExpectedValue.get(BigDecimal.valueOf(0.10)); // Assume a 10% constant growth rate -- probably not a great assumption
	    LiteralExpectedValue dividendPerShareQuarter = LiteralExpectedValue.get(BigDecimal.valueOf(0.47)); // Dividend yield as of January 26, 2015
	    LiteralExpectedValue riskAdjustedDiscountRate = LiteralExpectedValue.get(BigDecimal.valueOf(0.10)); // Assume a 10% opportunity cost for another equally risky investment
		MaximumChoiceStrategy strategy = new MaximumChoiceStrategy();
		Decision decision = new Decision(strategy);
		// Buying is the question here - positive result indicates not buy.  This will be determined 
		// with the maximum strategy.
		Choice buy = new Choice("Buy");
		// Sum of perpetuities: Sum of perpetuities: P = (E * G / K^2) + D / K
		// D / K
		MultiplicationCombiner dividendPerSharePerYear = new MultiplicationCombiner(dividendPerShareQuarter, LiteralExpectedValue.get(BigDecimal.valueOf(4)));
		DivisionCombiner adjustedDividend = new DivisionCombiner(dividendPerSharePerYear, riskAdjustedDiscountRate);
		MultiplicationCombiner earningsPerYear = new MultiplicationCombiner(earningsPerQuarter, LiteralExpectedValue.get(BigDecimal.valueOf(4)));
		MultiplicationCombiner earningsTimesGrowth = new MultiplicationCombiner(earningsPerYear, growthRate);
		MultiplicationCombiner discountRateSquared = new MultiplicationCombiner(riskAdjustedDiscountRate, riskAdjustedDiscountRate);
		DivisionCombiner earningsGrowthOverDiscountRate = new DivisionCombiner(earningsTimesGrowth, discountRateSquared);
		AdditionCombiner earnGrowthPlusDividends = new AdditionCombiner(earningsGrowthOverDiscountRate, adjustedDividend);
		SubtractionCombiner lessPrice = new SubtractionCombiner(earnGrowthPlusDividends, price);
		buy.setExpectedValue(lessPrice);
		// Not buying anything results in a net gain of zero.
		Choice notBuy = new Choice("Do not buy", new LiteralExpectedValue(BigDecimal.ZERO));
		decision.addChoice(buy);
		decision.addChoice(notBuy);
		Choice answer = decision.decide().orElse(null);
		assertEquals(notBuy, answer);
	}
}
