package pl.agh.edu.draughts.evaluation.parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class AttackingPawnsNumberParameterTest {

	private DraughtsEngine draughtsEngine;
	private AttackingPawnsNumberParameter attackingPawnsNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

		Pawn whitePawnMock = mock(Pawn.class);
		when(whitePawnMock.getPieceColor()).thenReturn(PieceColor.WHITE);

		Pawn blackPawnMock = mock(Pawn.class);
		when(blackPawnMock.getPieceColor()).thenReturn(PieceColor.BLACK);

		draughtsEngine.getChessboard().getChessboardTable()[0][2] = blackPawnMock;
		draughtsEngine.getChessboard().getChessboardTable()[1][2] = blackPawnMock;
		draughtsEngine.getChessboard().getChessboardTable()[2][2] = blackPawnMock;

		draughtsEngine.getChessboard().getChessboardTable()[7][3] = whitePawnMock;

		attackingPawnsNumberParameter = new AttackingPawnsNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(attackingPawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 1);
			assertTrue(attackingPawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 3);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
