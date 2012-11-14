package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;

/**
 * Numbers of loner pawns
 * 
 * @author Krzysztof
 * 
 */
public class LonerPawnsNumberParameter extends LonerPiecesNumberParameter {

	@Override
	protected boolean isExpectedPiece(Piece piece) {
		return piece instanceof Pawn;
	}

}
