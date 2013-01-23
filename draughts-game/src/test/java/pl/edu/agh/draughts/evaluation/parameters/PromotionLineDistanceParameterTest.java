package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.PromotionLineDistanceParameter;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class PromotionLineDistanceParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private PromotionLineDistanceParameter promotionLineDistanceParameter;
	private int initialPromotionLineDistance;

	private static final int MAX_PROMOTION_LINE_DISTANCE = 4 * (5 + 6 + 7);

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		initialPromotionLineDistance = MAX_PROMOTION_LINE_DISTANCE - (Chessboard.CHESSBOARD_SIZE
				- Chessboard.INITIAL_PAWNS__ROWS_COUNT + 1)
				* Chessboard.INITIAL_PAWNS__ROWS_COUNT
				* Chessboard.CHESSBOARD_SIZE / 2;

		Pawn pawnMock = createPawnMock(PieceColor.BLACK);
		draughtsEngine.getChessboard().getChessboardTable()[0][3] = pawnMock;

		King kingMock = createKingMock(PieceColor.WHITE);
		draughtsEngine.getChessboard().getChessboardTable()[3][3] = kingMock;

		promotionLineDistanceParameter = new PromotionLineDistanceParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(promotionLineDistanceParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == initialPromotionLineDistance);
			assertTrue(promotionLineDistanceParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == initialPromotionLineDistance);
		} catch (InvalidPieceException e) {
			fail();
		}
	}
}
