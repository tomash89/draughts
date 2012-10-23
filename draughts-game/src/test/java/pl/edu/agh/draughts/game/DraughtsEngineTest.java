package pl.edu.agh.draughts.game;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class DraughtsEngineTest {

	private DraughtsEngine draughtsEngine;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

	}

	@Test
	public void testGetPossibleMoves() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoMove() {
		Piece[][] chessboardTable = draughtsEngine.getChessboard()
				.getChessboardTable();
		for (int i = 0; i < chessboardTable.length; i++) {
			Arrays.fill(chessboardTable[i], null);
		}
		chessboardTable[1][1] = new Pawn(PieceColor.BLACK,
				draughtsEngine.getChessboard());
		chessboardTable[1][3] = new Pawn(PieceColor.BLACK,
				draughtsEngine.getChessboard());
		chessboardTable[3][1] = new Pawn(PieceColor.BLACK,
				draughtsEngine.getChessboard());
		chessboardTable[3][3] = new Pawn(PieceColor.BLACK,
				draughtsEngine.getChessboard());
		chessboardTable[5][1] = new Pawn(PieceColor.BLACK,
				draughtsEngine.getChessboard());
		chessboardTable[4][2] = new Pawn(PieceColor.WHITE,
				draughtsEngine.getChessboard());
		draughtsEngine.printChessboard();

		draughtsEngine.doMove(draughtsEngine.getPossibleMoves(PieceColor.WHITE)
				.get(0));

		draughtsEngine.printChessboard();

	}

}
