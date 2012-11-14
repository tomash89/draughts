package pl.edu.agh.draughts.evaluation.parameters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.draughts.evaluation.parameters.PawnNumberParameter;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class PawnNumberParameterTest {

	private DraughtsEngine draughtsEngine;
	private PawnNumberParameter pawnNumberParameter;
	
	@Before
	public void setUp() {
		draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		pawnNumberParameter = new PawnNumberParameter();
	}

	@Test
	public void testCalculateValue() {
		try {
			assertTrue(pawnNumberParameter.calculateValue(draughtsEngine.getChessboard(), PieceColor.WHITE) ==  12);
			assertTrue(pawnNumberParameter.calculateValue(draughtsEngine.getChessboard(), PieceColor.BLACK) ==  12);
		} catch (InvalidPieceException e) {
			fail();
		}
	}

}
