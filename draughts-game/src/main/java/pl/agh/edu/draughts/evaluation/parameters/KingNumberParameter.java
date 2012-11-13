package pl.agh.edu.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * number of kings
 * 
 * @author Krzysztof
 * 
 */
public class KingNumberParameter extends PieceNumberParameter {

	@Override
	public boolean isExpectedPiece(Piece piece) {
		return piece instanceof King;
	}

}
