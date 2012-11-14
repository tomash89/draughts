package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * Numbers of loner kings (a loner piece was de—ned as the one not adjacent to
 * any other piece);
 * 
 * @author Krzysztof
 * 
 */
public class LonerKingsNumberParameter extends LonerPiecesNumberParameter {

	@Override
	protected boolean isExpectedPiece(Piece piece) {
		return piece instanceof King;
	}

}
