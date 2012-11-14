package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.CentrallyPositionedPawnsParameter;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class CentrallyPositionedPawnsParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private CentrallyPositionedPawnsParameter centrallyPositionedPawnsParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		
		Pawn whitePawnMock = createPawnMock(PieceColor.WHITE);
		Pawn blackPawnMock = createPawnMock(PieceColor.BLACK);
		
		draughtsEngine.getChessboard().getChessboardTable()[3][3] = whitePawnMock ;
		draughtsEngine.getChessboard().getChessboardTable()[4][2] = whitePawnMock ;
		
		draughtsEngine.getChessboard().getChessboardTable()[3][5] = blackPawnMock ;
		
		centrallyPositionedPawnsParameter = new CentrallyPositionedPawnsParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(centrallyPositionedPawnsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 2);
			assertTrue(centrallyPositionedPawnsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 1);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
