package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class EvaluationFunctionTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private EvaluationFunction evaluationFunction;

	private static final int MAX_PROMOTION_LINE_DISTANCE = 4 * (5 + 6 + 7);

	private double result;
	
	private double distanceWeight = 3.0;
	private double pawnWeight = 2.0;
	
	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		evaluationFunction = new EvaluationFunction();
		Map<IEvaluationParameter, Double> map = new HashMap<IEvaluationParameter, Double>();
		map.put(new PawnNumberParameter(), 2.0);
		map.put(new PromotionLineDistanceParameter(), 3.0);
		evaluationFunction.setEvaluationFunctionParameters(map);

		Pawn whitePawnMock = createPawnMock(PieceColor.WHITE);
		draughtsEngine.getChessboard().getChessboardTable()[2][0] = null;
		draughtsEngine.getChessboard().getChessboardTable()[3][1] = whitePawnMock;

		draughtsEngine.printChessboard();
		result = distanceWeight;
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(evaluationFunction.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == result);
			assertTrue(evaluationFunction.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == -1.0 * result);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
