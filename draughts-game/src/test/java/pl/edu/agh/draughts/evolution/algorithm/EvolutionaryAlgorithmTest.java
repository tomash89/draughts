package pl.edu.agh.draughts.evolution.algorithm;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.AttackingPawnsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.CentrallyPositionedKingsParameter;
import pl.edu.agh.draughts.evaluation.parameters.CentrallyPositionedPawnsParameter;
import pl.edu.agh.draughts.evaluation.parameters.DefenderPiecesNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;
import pl.edu.agh.draughts.evaluation.parameters.KingNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.LonerKingsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.LonerPawnsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.PawnNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.PromotionLineDistanceParameter;
import pl.edu.agh.draughts.evaluation.parameters.PromotionLineUnoccupiedFieldsParameter;
import pl.edu.agh.draughts.evaluation.parameters.SafeKingsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.SafePawnsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.TriangleParameter;

public class EvolutionaryAlgorithmTest {

	private EvaluationFunction evaluationFunction;

	private EvolutionaryAlgorithm evolutionaryAlgorithm;

	@Before
	public void setUp() {
		evaluationFunction = new EvaluationFunction();
		Map<IEvaluationParameter, Double> parameters = new HashMap<IEvaluationParameter, Double>();

		parameters.put(new AttackingPawnsNumberParameter(), null);
		parameters.put(new CentrallyPositionedKingsParameter(), null);
		parameters.put(new CentrallyPositionedPawnsParameter(), null);
		parameters.put(new DefenderPiecesNumberParameter(), null);
		parameters.put(new KingNumberParameter(), null);
		parameters.put(new LonerKingsNumberParameter(), null);
		parameters.put(new LonerPawnsNumberParameter(), null);
		parameters.put(new PawnNumberParameter(), null);
		parameters.put(new PromotionLineDistanceParameter(), null);
		parameters.put(new PromotionLineUnoccupiedFieldsParameter(), null);
		parameters.put(new SafeKingsNumberParameter(), null);
		parameters.put(new SafePawnsNumberParameter(), null);
		parameters.put(new TriangleParameter(), null);

		evaluationFunction.setEvaluationFunctionParameters(parameters);

		this.evolutionaryAlgorithm = new EvolutionaryAlgorithm();
	}

	@Test
	public void testPrepareInitialPopulation() {
		this.evolutionaryAlgorithm
				.prepareInitialPopulation(this.evaluationFunction);
		for (EvaluationFunction function : this.evolutionaryAlgorithm
				.getPopulation()) {
			System.out.println("- function");
			for (IEvaluationParameter parameter : function
					.getEvaluationFunctionParameters().keySet()) {
				System.out.println(parameter.getClass().getName()
						+ " = "
						+ function.getEvaluationFunctionParameters().get(
								parameter));
			}
		}
	}

}
