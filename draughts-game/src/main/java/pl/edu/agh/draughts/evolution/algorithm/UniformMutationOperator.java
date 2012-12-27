package pl.edu.agh.draughts.evolution.algorithm;

import java.util.Random;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;

/**
 * 
 * @author Krzysztof
 * 
 *         This operator replaces the value of the chosen gene with a uniform
 *         random value selected between the user-specified upper and lower
 *         bounds for that gene
 */
public class UniformMutationOperator implements IMutationOperator {

	private static final Random RANDOM = new Random();

	private static final double MUTATION_PROBABLITY = 0.01;

	@Override
	public void mutate(EvaluationFunction evaluationFunction) {

		for (IEvaluationParameter evaluationParameter : evaluationFunction
				.getEvaluationFunctionParameters().keySet()) {
			if (RANDOM.nextDouble() < MUTATION_PROBABLITY) {
				evaluationFunction.setParameterValue(evaluationParameter,
						EvolutionaryAlgorithm.generateParameterValue());			}
		}

	}

}
