package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * Numbers of safe kings;
 * 
 * @author Krzysztof
 * 
 */
public class SafeKingsNumberParameter extends SafePiecesNumberParameter {

	@Override
	protected boolean isExpectedPiece(Piece piece) {
		return piece instanceof King;
	}

}
