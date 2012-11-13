package pl.agh.edu.draughts.evaluation.parameters;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class DefenderPiecesNumberParameterTest {

	private DraughtsEngine draughtsEngine;
	private DefenderPiecesNumberParameter defenderPiecesNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		King kingMock = mock(King.class);
		when(kingMock.getPieceColor()).thenReturn(PieceColor.BLACK);
		draughtsEngine.getChessboard().getChessboardTable()[0][2] = kingMock;
		draughtsEngine.getChessboard().getChessboardTable()[7][3] = null;
		defenderPiecesNumberParameter = new DefenderPiecesNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(defenderPiecesNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 7);
			assertTrue(defenderPiecesNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 7);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
