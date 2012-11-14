package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class PromotionLineUnoccupiedFieldsParameterTest extends ParameterTest {

	private DraughtsEngine draughtsEngine;
	private PromotionLineUnoccupiedFieldsParameter promotionLineUnoccupiedFieldsParameter;

	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();

		Pawn whitePawnMock = createPawnMock(PieceColor.WHITE);
		draughtsEngine.getChessboard().getChessboardTable()[0][0] = null;
		draughtsEngine.getChessboard().getChessboardTable()[7][5] = whitePawnMock;

		promotionLineUnoccupiedFieldsParameter = new PromotionLineUnoccupiedFieldsParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(promotionLineUnoccupiedFieldsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.WHITE) == 0);
			assertTrue(promotionLineUnoccupiedFieldsParameter.calculateValue(
					draughtsEngine.getChessboard(), PieceColor.BLACK) == 1);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
