package pl.agh.edu.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public abstract class SafePiecesNumberParameter extends
		AbstractEvaluationParameter {

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int safePieceCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		int row = 0;
		int column = 0;
		while (row < Chessboard.CHESSBOARD_SIZE - 1
				&& column < Chessboard.CHESSBOARD_SIZE) {
			if (isPieceSafe(chessboardTable[row][column], pieceColor)) {
				safePieceCount++;
			}

			if (isPieceSafe(chessboardTable[Chessboard.CHESSBOARD_SIZE - row
					- 1][Chessboard.CHESSBOARD_SIZE - column - 1], pieceColor)) {
				safePieceCount++;
			}

			if (column < Chessboard.CHESSBOARD_SIZE - 1) {
				column++;
			} else {
				row++;
			}
		}
		return safePieceCount;
	}

	private boolean isPieceSafe(Piece piece, PieceColor expectedPieceColor) {
		return piece != null
				&& piece.getPieceColor().equals(expectedPieceColor)
				&& isExpectedPiece(piece);
	}

	protected abstract boolean isExpectedPiece(Piece piece);

}
