package pl.edu.agh.draughts.evolution.algorithm;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;

/**
 * 
 * @author Krzysztof
 * 
 */
public interface IMutationOperator {

	public void mutate(EvaluationFunction evaluationFunction);
}
