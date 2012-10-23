package pl.edu.agh.draughts.game.elements;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.draughts.game.exceptions.OutOfChessboardException;

public class King extends Piece {

	public King(PieceColor pieceColor, Chessboard chessboard) {
		super(pieceColor, chessboard);
	}

	@Override
	public String toString() {
		return super.pieceColor.getKingString();
	}

	@Override
	public List<Move> getValidNoCaptureMoves(int row, int column,
			Chessboard chessboard) {
		List<Move> noCaptureMoves = new LinkedList<Move>();
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				int moveLength = 1;
				try {
					while (chessboard.getPieceColorForField(row + i
							* moveLength, column + j * moveLength) == null) {
						Move move = new Move(this, row, column);
						move.addNextStep(new ChessboardPosition(row
								+ moveLength * i, column + moveLength * j));
						noCaptureMoves.add(move);
						moveLength++;
					}
				} catch (OutOfChessboardException e) {
				}
			}
		}
		return noCaptureMoves;
	}

	@Override
	protected List<ChessboardPosition> getCapturePositions(int row, int column,
			Chessboard chessboard) {
		List<ChessboardPosition> capturePositions = new LinkedList<ChessboardPosition>();
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				int moveLength = 1;
				try {
					while (chessboard.getPieceColorForField(row + i
							* moveLength, column + j * moveLength) == null) {
						moveLength++;
					}
					if (this.pieceColor.isAnotherColor(chessboard
							.getPieceColorForField(row + i * moveLength, column
									+ j * moveLength))) {
						moveLength++;
						while (chessboard.getPieceColorForField(row + i
								* moveLength, column + j * moveLength) == null) {
							capturePositions.add(new ChessboardPosition(row + i
									* moveLength, column + j * moveLength));
							moveLength++;
						}

					}
				} catch (OutOfChessboardException e) {
				}
			}
		}
		return capturePositions;
	}
}
