package pl.agh.edu.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * 
 * @author Krzysztof
 * 
 */
public abstract class PieceNumberParameter extends AbstractEvaluationParameter {

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int pieceCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		for (int i = 0; i < Chessboard.CHESSBOARD_SIZE; i++) {
			for (int j = 0; j < Chessboard.CHESSBOARD_SIZE; j++) {
				if (chessboardTable[i][j] != null
						&& chessboardTable[i][j].getPieceColor().equals(
								pieceColor)
						&& isExpectedPiece(chessboardTable[i][j])) {
					pieceCount++;
				}
			}
		}
		return pieceCount;
	}

	protected abstract boolean isExpectedPiece(Piece piece);

}
