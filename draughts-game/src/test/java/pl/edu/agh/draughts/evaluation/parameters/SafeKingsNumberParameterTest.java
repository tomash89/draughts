package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.SafeKingsNumberParameter;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class SafeKingsNumberParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private SafeKingsNumberParameter safeKingsNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

		King blackKingMock = createKingMock(PieceColor.BLACK);
		King whiteKingMock = createKingMock(PieceColor.WHITE);

		draughtsEngine.getChessboard().getChessboardTable()[0][6] = blackKingMock;
		draughtsEngine.getChessboard().getChessboardTable()[1][6] = blackKingMock;

		draughtsEngine.getChessboard().getChessboardTable()[7][7] = whiteKingMock;
		draughtsEngine.getChessboard().getChessboardTable()[0][0] = whiteKingMock;

		safeKingsNumberParameter = new SafeKingsNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(safeKingsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 2);
			assertTrue(safeKingsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 1);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
