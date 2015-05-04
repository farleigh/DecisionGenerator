package ca.farleigh.decisiongenerator.decision;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import ca.farleigh.decisiongenerator.choice.Choice;
import ca.farleigh.decisiongenerator.choice.value.ExpectedValue;
import ca.farleigh.decisiongenerator.solver.DecisionStrategy;

/**
 * Represents a single decision point.  Ultimately, decisions are composed of choices 
 * that will have to be made by the user -- but that DecisionGenerator is here to help
 * with.
 * 
 * @author Clinton Farleigh
 */
public final class Decision implements ExpectedValue {

	private final DecisionStrategy strategy;
	private String name = "";
	private Collection<Choice> choices = new ArrayList<Choice>();
	
	/**
	 * Default constructor
	 */
	public Decision(final DecisionStrategy strategy) {
	    this(strategy, "", Collections.emptyList());
	}

	/**
	 * Constructor used to set all state for this object
	 * @param name identifier for the decision (a string that describes the decision)
	 * @param choices All choices available for the decision
	 */
	public Decision(final DecisionStrategy strategy, final String name, final Collection<Choice> choices) {
		this.strategy = strategy;
		this.name = name;
		this.choices = new ArrayList<Choice>(choices);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Collection<Choice> getChoices() {
		return choices;
	}

	/**
	 * Set all choices for this decision
	 * @param choices The set of all choices that will be used for this decision
	 */
	public void setChoices(final Collection<Choice> choices) {
		this.choices.clear();
		this.choices.addAll(choices);
	}
	
	/**
	 * Add a choice for this decision
	 * @param choice The choice to be added
	 */
	public void addChoice(final Choice choice) {
		this.choices.add(choice);
	}
	
	/**
	 * 
	 */
	@Override
	public Optional<BigDecimal> calculate() {
		Optional<Choice> choice = this.strategy.decide(this);
		if(choice.isPresent()) {
			return choice.get().calculate();
		}
		return Optional.of(BigDecimal.ZERO);
	}
	
	public Optional<Choice> decide() {
	    return this.strategy.decide(this);
	}
}
