package pl.agh.edu.draughts.evaluation.parameters;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class KingNumberParameterTest {

	private DraughtsEngine draughtsEngine;
	private KingNumberParameter kingNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		King kingMock = mock(King.class);
		when(kingMock.getPieceColor()).thenReturn(PieceColor.BLACK);
		draughtsEngine.getChessboard().getChessboardTable()[3][3] = kingMock;
		kingNumberParameter = new KingNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(kingNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 0);
			assertTrue(kingNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 1);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
