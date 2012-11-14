package pl.agh.edu.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class CentrallyPositionedKingsParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private CentrallyPositionedKingsParameter centrallyPositionedKingsParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

		Pawn whitePawnMock = createPawnMock(PieceColor.WHITE);
		King whiteKingMock = createKingMock(PieceColor.WHITE);
		King blackKingMock = createKingMock(PieceColor.BLACK);
		
		draughtsEngine.getChessboard().getChessboardTable()[3][3] = whiteKingMock;
		draughtsEngine.getChessboard().getChessboardTable()[4][2] = whiteKingMock;
		draughtsEngine.getChessboard().getChessboardTable()[4][4] = whitePawnMock;

		draughtsEngine.getChessboard().getChessboardTable()[3][5] = blackKingMock;

		centrallyPositionedKingsParameter = new CentrallyPositionedKingsParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(centrallyPositionedKingsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 2);
			assertTrue(centrallyPositionedKingsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 1);
		} catch (InvalidPieceException e) {
			fail();
		}
	}
}
