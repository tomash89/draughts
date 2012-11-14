package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public abstract class LonerPiecesNumberParameter extends
		AbstractEvaluationParameter {

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int lonerPiecesCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		for (int row = 0; row < Chessboard.CHESSBOARD_SIZE; row++) {
			for (int column = 0; column < Chessboard.CHESSBOARD_SIZE; column++) {
				if (chessboardTable[row][column] != null
						&& chessboardTable[row][column].getPieceColor().equals(
								pieceColor)
						&& isExpectedPiece(chessboardTable[row][column])) {
					boolean isLoner = true;
					for (int i = row - 1; i <= row + 1 && isLoner; i += 2) {
						for (int j = column - 1; j <= column + 1 && isLoner; j += 2) {
							if (i >= 0 && j >= 0
									&& i < Chessboard.CHESSBOARD_SIZE
									&& j < Chessboard.CHESSBOARD_SIZE
									&& chessboardTable[i][j] != null) {
								isLoner = false;
							}
						}
					}
					if (isLoner) {
						lonerPiecesCount++;
					}
				}
			}
		}
		return lonerPiecesCount;
	}

	protected abstract boolean isExpectedPiece(Piece piece);
}
