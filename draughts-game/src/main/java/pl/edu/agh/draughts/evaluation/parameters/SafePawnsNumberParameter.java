package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * Numbers of safe - i.e. adjacent to the edge of the board pawns
 * 
 * @author Krzysztof
 * 
 */
public class SafePawnsNumberParameter extends SafePiecesNumberParameter {

	@Override
	protected boolean isExpectedPiece(Piece piece) {
		return piece instanceof Pawn;
	}

}
