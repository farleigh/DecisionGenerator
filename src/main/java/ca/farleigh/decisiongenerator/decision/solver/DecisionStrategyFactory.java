package ca.farleigh.decisiongenerator.decision.solver;

import ca.farleigh.decisiongenerator.decision.DecisionStrategy;


/**
 * Class to get a decision strategy factory.
 * 
 * @author Clinton Farleigh
 *
 */
public class DecisionStrategyFactory {
    
    /**
     * Gets one of the supported strategy instances by name.
     * @param strategyType The type of strategy to return.
     * @return Get a strategy.
     */
    public DecisionStrategy getStrategy(String strategyType) {
        DecisionStrategy result = null;
        switch (strategyType.toLowerCase()) {
        case "minimum":
            result = new MinimumChoiceStrategy();
            break;
        case "maximum":
            result = new MaximumChoiceStrategy();
            break;
        }
        return result;
    }
}
