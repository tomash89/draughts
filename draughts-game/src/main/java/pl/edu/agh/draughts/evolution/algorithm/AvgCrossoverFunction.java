package pl.edu.agh.draughts.evolution.algorithm;

import java.util.HashMap;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;

/**
 * 
 * @author Krzysztof
 *
 */
public class AvgCrossoverFunction implements ICrossoverFunction {

	@Override
	public EvaluationFunction crossover(EvaluationFunction evaluationFunction1,
			EvaluationFunction evaluationFunction2) {
		EvaluationFunction newEvaluationFunction = new EvaluationFunction();
		HashMap<IEvaluationParameter, Double> newFunctionParameters = new HashMap<IEvaluationParameter, Double>();
		for (IEvaluationParameter evaluationParameter : evaluationFunction1
				.getEvaluationFunctionParameters().keySet()) {
			Double newValue = (evaluationFunction1
					.getParameterValue(evaluationParameter) + evaluationFunction2
					.getParameterValue(evaluationParameter)) / 2.0;
			newFunctionParameters.put(evaluationParameter, newValue);
		}
		newEvaluationFunction
				.setEvaluationFunctionParameters(newFunctionParameters);
		return newEvaluationFunction;
	}
}
