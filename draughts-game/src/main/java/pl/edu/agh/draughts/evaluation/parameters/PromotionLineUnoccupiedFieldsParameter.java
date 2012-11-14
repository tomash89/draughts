package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * Number of unoccupied fields on promotion line.
 * 
 * @author Krzysztof
 * 
 */
public class PromotionLineUnoccupiedFieldsParameter extends
		AbstractEvaluationParameter {

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		// return promotion line row number
		int promotionLineNumber = chessboard.getFirstTopmostRow(pieceColor, 1);
		int unoccupiedFieldsNumber = Chessboard.CHESSBOARD_SIZE / 2;
		for (int i = 0; i < Chessboard.CHESSBOARD_SIZE; i++) {
			if (chessboardTable[promotionLineNumber][i] != null) {
				unoccupiedFieldsNumber--;
			}
		}
		return unoccupiedFieldsNumber;
	}

}
