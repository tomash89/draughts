package pl.agh.edu.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * number of pawns
 * 
 * @author Krzysztof
 * 
 */
public class PawnNumberParameter extends PieceNumberParameter {

	@Override
	protected boolean isExpectedPiece(Piece piece) {
		return piece instanceof Pawn;
	}

}
