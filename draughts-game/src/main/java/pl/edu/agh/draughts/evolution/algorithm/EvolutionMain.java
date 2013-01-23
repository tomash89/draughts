package pl.edu.agh.draughts.evolution.algorithm;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;

public class EvolutionMain {

	public static void main(String[] args) {
		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm();
		evolutionaryAlgorithm.compute(
				EvaluationFunction.getAllParametersEvaluationFunction(),
				"src/main/resources/winner.txt");

	}
}
