package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class LonerKingsNumberParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private LonerKingsNumberParameter lonerKingsNumberParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		lonerKingsNumberParameter = new LonerKingsNumberParameter();

		Piece[][] chessboardTable = draughtsEngine.getChessboard()
				.getChessboardTable();
		for (int i = 0; i < chessboardTable.length; i++) {
			Arrays.fill(chessboardTable[i], null);
		}

		King whiteKingMock = createKingMock(PieceColor.WHITE);
		Pawn blackPawnMock = createPawnMock(PieceColor.BLACK);
		
		chessboardTable[7][7] = whiteKingMock;
		chessboardTable[0][0] = whiteKingMock;
		chessboardTable[1][1] = whiteKingMock;
		
		chessboardTable[3][6] = blackPawnMock;
		chessboardTable[6][3] = whiteKingMock;

	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(lonerKingsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 2);
			assertTrue(lonerKingsNumberParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 0);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
