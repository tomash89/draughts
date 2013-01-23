package pl.edu.agh.draughts.evolution.algorithm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;

/**
 * 
 * @author Krzysztof
 * 
 */
public class EvolutionaryAlgorithm {

	private static final int POPULATION_SIZE = 30;

	private static final double MIN_INITIAL_VALUE = 0.0;

	private static final double MAX_INITIAL_VALUE = 1.0;

	private static final Random RANDOM = new Random();

	private List<EvaluationFunction> population = null;

	private static final int WINNER_POINTS = 3;

	private static final int DRAW_POINTS = 1;

	private static final int STEPS_COUNT = 50;

	private static final boolean PRESERVE_WINNER = true;

	private ICrossoverFunction crossoverFunction = new AvgCrossoverFunction();

	private IMutationOperator mutationOperator = new UniformMutationOperator();

	public EvaluationFunction compute(EvaluationFunction evaluationFunction) {
		prepareInitialPopulation(evaluationFunction);
		Map<EvaluationFunction, Integer> evaluation = evaluatePopulation();
		for (int i = 0; i < STEPS_COUNT; i++) {
			createNextPopulation(evaluation);
			evaluation = evaluatePopulation();
		}
		return orderEvaluationFunctions(evaluation).lastEntry().getValue()
				.get(0);
	}

	public EvaluationFunction compute(EvaluationFunction evaluationFunction,
			String file) {
		EvaluationFunction winner = this.compute(evaluationFunction);
		Properties properties = winner.toProperties();
		try {
			properties.store(new FileOutputStream(file), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
						generateParameterValue());
			}
			newEvaluationFunction
					.setEvaluationFunctionParameters(evaluationFunctionParameters);
			this.population.add(newEvaluationFunction);
		}
	}

	public Map<EvaluationFunction, Integer> evaluatePopulation() {
		Map<EvaluationFunction, Integer> evaluation = new HashMap<EvaluationFunction, Integer>();
		for (EvaluationFunction evaluationFunction : this.population) {
			evaluation.put(evaluationFunction, 0);
		}
		for (EvaluationFunction evaluationFunction : this.population) {
			Iterator<EvaluationFunction> opponentIterator = this.population
					.listIterator(this.population.indexOf(evaluationFunction));
			while (opponentIterator.hasNext()) {
				EvaluationFunction opponentEvaluationFunction = opponentIterator
						.next();
				EvaluationFunction winnerFunction = play(evaluationFunction,
						opponentEvaluationFunction);
				if (winnerFunction == null) {
					evaluation.put(evaluationFunction,
							evaluation.get(evaluationFunction) + DRAW_POINTS);
					evaluation.put(opponentEvaluationFunction,
							evaluation.get(opponentEvaluationFunction)
									+ DRAW_POINTS);
				} else {
					evaluation.put(winnerFunction,
							evaluation.get(winnerFunction) + WINNER_POINTS);
				}
			}
		}
		return evaluation;
	}

	public List<EvaluationFunction> getPopulation() {
		return this.population;
	}

	public EvaluationFunction play(EvaluationFunction evaluationFunction1,
			EvaluationFunction evaluationFunction2) {
		// TODO
		int selection = RANDOM.nextInt() % 3;
		if (selection == 0) {
			return null;
		} else if (selection == 1) {
			return evaluationFunction1;
		} else {
			return evaluationFunction2;
		}
	}

	public void createNextPopulation(Map<EvaluationFunction, Integer> evaluation) {
		List<EvaluationFunction> oldPopulation = this.population;
		this.population = new LinkedList<EvaluationFunction>();

		TreeMap<Integer, List<EvaluationFunction>> sortedEvaluationsMap = orderEvaluationFunctions(evaluation);

		EvaluationFunction functionsRank[] = new EvaluationFunction[oldPopulation
				.size()];
		int points[] = new int[oldPopulation.size()];
		int rankIndex = 0;
		for (Integer value : sortedEvaluationsMap.descendingKeySet()) {
			for (EvaluationFunction evaluationFunction : sortedEvaluationsMap
					.get(value)) {
				functionsRank[rankIndex] = evaluationFunction;
				int prevPoints = 0;
				if (rankIndex > 0) {
					prevPoints = points[rankIndex - 1];
				}
				points[rankIndex] = prevPoints + value;
				rankIndex++;
			}
		}
		int maxRandom = points[oldPopulation.size() - 1];

		if (PRESERVE_WINNER) {
			this.population.addAll(sortedEvaluationsMap.lastEntry().getValue());
		}

		while (population.size() < POPULATION_SIZE) {
			int function1 = getPoint(points, maxRandom);
			int function2 = getPoint(points, maxRandom);
			EvaluationFunction newEvaluationFunction = this.crossoverFunction
					.crossover(functionsRank[function1],
							functionsRank[function2]);
			this.mutationOperator.mutate(newEvaluationFunction);
			this.population.add(newEvaluationFunction);
		}
	}

	private int getPoint(int points[], int maxRandom) {
		int function = RANDOM.nextInt(maxRandom) + 1;
		int result = Arrays.binarySearch(points, function);
		if (result < 0) {
			result = -1 * (result + 1);
		}
		return result;
	}

	public static double generateParameterValue() {
		return MIN_INITIAL_VALUE + RANDOM.nextDouble()
				* (MAX_INITIAL_VALUE - MIN_INITIAL_VALUE);
	}

	private TreeMap<Integer, List<EvaluationFunction>> orderEvaluationFunctions(
			Map<EvaluationFunction, Integer> evaluation) {
		Map<Integer, List<EvaluationFunction>> evaluationsMap = new HashMap<Integer, List<EvaluationFunction>>();
		for (EvaluationFunction evaluationFunction : evaluation.keySet()) {
			Integer value = evaluation.get(evaluationFunction);
			if (!evaluationsMap.containsKey(value)) {
				evaluationsMap.put(value, new LinkedList<EvaluationFunction>());
			}
			evaluationsMap.get(value).add(evaluationFunction);
		}
		TreeMap<Integer, List<EvaluationFunction>> sortedEvaluationsMap = new TreeMap<Integer, List<EvaluationFunction>>(
				evaluationsMap);
		return sortedEvaluationsMap;
	}
}
