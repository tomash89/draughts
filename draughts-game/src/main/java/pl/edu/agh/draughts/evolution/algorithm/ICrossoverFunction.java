package pl.edu.agh.draughts.evolution.algorithm;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;

/**
 * 
 * @author Krzysztof
 * 
 */
public interface ICrossoverFunction {

	public EvaluationFunction crossover(EvaluationFunction evaluationFunction1,
			EvaluationFunction evaluationFunction2);
}
