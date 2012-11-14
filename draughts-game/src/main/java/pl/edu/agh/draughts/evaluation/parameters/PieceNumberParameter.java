package pl.edu.agh.draughts.evaluation.parameters;

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

	protected int startRow = 0;
	protected int startColumn = 0;
	protected int endRow = Chessboard.CHESSBOARD_SIZE;
	protected int endColumn = Chessboard.CHESSBOARD_SIZE;

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int pieceCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		for (int i = startRow; i < endRow; i++) {
			for (int j = startColumn; j < endColumn; j++) {
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
