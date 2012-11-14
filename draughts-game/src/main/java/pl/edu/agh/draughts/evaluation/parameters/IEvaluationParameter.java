package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * 
 * @author Krzysztof
 * 
 */
public interface IEvaluationParameter {

	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException;

}
