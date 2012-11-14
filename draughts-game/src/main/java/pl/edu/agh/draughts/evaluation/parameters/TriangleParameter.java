package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * 
 * A Triangle - white pawns on squares 27, 31 and 32;
 * 
 * @author Krzysztof
 * 
 */
public class TriangleParameter extends AbstractEvaluationParameter {

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		throw new UnsupportedOperationException();
	}

}
