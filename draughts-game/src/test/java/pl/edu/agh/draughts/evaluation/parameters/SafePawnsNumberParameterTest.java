package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.SafePawnsNumberParameter;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class SafePawnsNumberParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private SafePawnsNumberParameter safePawnsNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

		Pawn blackPawnMock = createPawnMock(PieceColor.BLACK);

		draughtsEngine.getChessboard().getChessboardTable()[1][7] = blackPawnMock;

		safePawnsNumberParameter = new SafePawnsNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(safePawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 5);
			assertTrue(safePawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 7);
		} catch (InvalidPieceException e) {
			fail();
		}
	}
}
