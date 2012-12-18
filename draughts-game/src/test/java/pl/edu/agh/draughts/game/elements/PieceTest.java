package pl.edu.agh.draughts.game.elements;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.ParameterTest;
import pl.edu.agh.draughts.game.DraughtsEngine;

public class PieceTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
	}

	@Test
	public void testGetValidCaptureMoves() {
		Piece[][] chessboardTable = draughtsEngine.getChessboard()
				.getChessboardTable();
		for (int i = 0; i < chessboardTable.length; i++) {
			Arrays.fill(chessboardTable[i], null);
		}
		chessboardTable[6][6] = new King(PieceColor.WHITE);
		chessboardTable[2][2] = createPawnMock(PieceColor.BLACK);

		List<Move> moves = chessboardTable[6][6]
				.getValidCaptureMoves(6, 6, draughtsEngine.getChessboard());
		assertEquals(moves.size(), 2);

	}

}
