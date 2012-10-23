package pl.edu.agh.draughts.game.elements;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.draughts.game.exceptions.OutOfChessboardException;

public class Pawn extends Piece {

	public Pawn(PieceColor pieceColor, Chessboard chessboard) {
		super(pieceColor, chessboard);
	}

	@Override
	public String toString() {
		return super.pieceColor.getPawnString();
	}

	@Override
	protected List<ChessboardPosition> getCapturePositions(int row, int column,
			Chessboard chessboard) {
		List<ChessboardPosition> capturePositions = new LinkedList<ChessboardPosition>();
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				try {
					if (this.pieceColor.isAnotherColor(chessboard
							.getPieceColorForField(row + i, column + j))
							&& chessboard.getPieceColorForField(row + 2 * i,
									column + 2 * j) == null) {
						capturePositions.add(new ChessboardPosition(
								row + 2 * i, column + 2 * j));
					}
				} catch (OutOfChessboardException e) {
				}
			}
		}
		return capturePositions;
	}

	@Override
	public List<Move> getValidNoCaptureMoves(int row, int column,
			Chessboard chessboard) {
		List<Move> validNoCaptureMoves = new LinkedList<Move>();
		int moveRow = row + 1;
		if (this.pieceColor.equals(PieceColor.BLACK))
			moveRow = row - 1;
		for (int i = -1; i <= 1; i += 2) {
			try {
				if (chessboard.getPieceColorForField(moveRow, column + i) == null) {
					Move move = new Move(this, row, column);
					move.addNextStep(new ChessboardPosition(moveRow, column + i));
					validNoCaptureMoves.add(move);
				}
			} catch (OutOfChessboardException e) {
			}
		}
		return validNoCaptureMoves;
	}
}
