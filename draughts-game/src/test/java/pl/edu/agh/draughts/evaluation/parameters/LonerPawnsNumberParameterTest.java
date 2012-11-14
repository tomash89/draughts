package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class LonerPawnsNumberParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private LonerPawnsNumberParameter lonerPawnsNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		lonerPawnsNumberParameter = new LonerPawnsNumberParameter();

		Piece[][] chessboardTable = draughtsEngine.getChessboard()
				.getChessboardTable();
		for (int i = 0; i < chessboardTable.length; i++) {
			Arrays.fill(chessboardTable[i], null);
		}

		Pawn whitePawnMock = createPawnMock(PieceColor.WHITE);

		chessboardTable[0][0] = whitePawnMock;
		chessboardTable[2][2] = whitePawnMock;
		chessboardTable[3][3] = whitePawnMock;
		chessboardTable[3][6] = whitePawnMock;

	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(lonerPawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 2);
			assertTrue(lonerPawnsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 0);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
