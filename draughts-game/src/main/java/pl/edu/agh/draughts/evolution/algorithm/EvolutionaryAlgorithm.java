package pl.edu.agh.draughts.evolution.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;

public class EvolutionaryAlgorithm {

	private static final int POPULATION_SIZE = 30;

	private static final double MIN_INITIAL_VALUE = 0.0;

	private static final double MAX_INITIAL_VALUE = 1.0;

	private static final Random RANDOM = new Random();

	private List<EvaluationFunction> population = null;

	private static final int WINNER_POINTS = 3;

	public EvaluationFunction compute(EvaluationFunction evaluationFunction) {
		EvaluationFunction winner = null;
		prepareInitialPopulation(evaluationFunction);
		return winner;
	}

	public void prepareInitialPopulation(EvaluationFunction evaluationFunction) {
		this.population = new LinkedList<EvaluationFunction>();

		for (int i = 0; i < POPULATION_SIZE; i++) {
			EvaluationFunction newEvaluationFunction = new EvaluationFunction();
			Map<IEvaluationParameter, Double> evaluationFunctionParameters = new HashMap<IEvaluationParameter, Double>();
			for (IEvaluationParameter evaluationParameter : evaluationFunction
					.getEvaluationFunctionParameters().keySet()) {
				evaluationFunctionParameters.put(evaluationParameter,
						MIN_INITIAL_VALUE + RANDOM.nextDouble()
								* (MAX_INITIAL_VALUE - MIN_INITIAL_VALUE));
			}
			newEvaluationFunction
					.setEvaluationFunctionParameters(evaluationFunctionParameters);
			this.population.add(newEvaluationFunction);
		}
	}

	public Map<EvaluationFunction, Integer> evaluatePopulation() {
		Map<EvaluationFunction, Integer> evaluation = new HashMap<EvaluationFunction, Integer>();
		for (EvaluationFunction evaluationFunction : this.population) {
			for(int i = this.population.indexOf(evaluationFunction) + 1; i < this.population.size() ; i++) {
				EvaluationFunction opponentEvaluationFunction = this.population.get(i);
				//play(evaluationFunction, opponentEvaluationFunction);
			}
		}
		return evaluation;
	}

	public List<EvaluationFunction> getPopulation() {
		return this.population;
	}
}
