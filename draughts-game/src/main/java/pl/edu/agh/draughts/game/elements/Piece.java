package pl.edu.agh.draughts.game.elements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.edu.agh.draughts.game.exceptions.OutOfChessboardException;

public abstract class Piece {

	protected PieceColor pieceColor;

	public Piece(PieceColor pieceColor) {
		super();
		this.pieceColor = pieceColor;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public List<Move> getValidCaptureMoves(int row, int column,
			Chessboard chessboard) {
		List<Move> validCaptureMoves = new LinkedList<Move>();
		Map<ChessboardPosition, List<ChessboardPosition>> capturesMap = new HashMap<ChessboardPosition, List<ChessboardPosition>>();
		ChessboardPosition initialPosition = new ChessboardPosition(row, column);
		List<ChessboardPosition> uncheckedChessboardPositions = new LinkedList<ChessboardPosition>();
		uncheckedChessboardPositions.add(initialPosition);
		chessboard.capture(row, column);
		while (!uncheckedChessboardPositions.isEmpty()) {
			List<ChessboardPosition> newChessboardPositions = new LinkedList<ChessboardPosition>();
			for (ChessboardPosition chessboardPosition : uncheckedChessboardPositions) {
				if (!capturesMap.containsKey(chessboardPosition)) {
					List<ChessboardPosition> capturePositions = getCapturePositions(
							chessboardPosition.getRow(),
							chessboardPosition.getColumn(), chessboard);
					capturesMap.put(chessboardPosition, capturePositions);
					newChessboardPositions.addAll(capturePositions);
				}
			}
			uncheckedChessboardPositions = newChessboardPositions;
		}
		chessboard.movePiece(row, column, this);
		int maxMoveLength = 0;
		while (!capturesMap.get(initialPosition).isEmpty()) {
			HashSet<ChessboardPosition> capturedPositions = new HashSet<ChessboardPosition>();
			ChessboardPosition previousChessboardPosition = null;
			ChessboardPosition chessboardPosition = initialPosition;
			Move move = new Move(this, row, column);
			List<ChessboardPosition> nextPositions = capturesMap
					.get(chessboardPosition);

			while (!nextPositions.isEmpty()) {
				previousChessboardPosition = chessboardPosition;
				chessboardPosition = nextPositions.get(0);

				capturedPositions.add(getCaputredPosition(
						previousChessboardPosition, chessboardPosition,
						chessboard));

				move.addNextStep(chessboardPosition);
				nextPositions = capturesMap.get(chessboardPosition);
				nextPositions = removeUsedCaptures(chessboardPosition,
						nextPositions, capturedPositions, chessboard);
			}
			capturesMap.get(previousChessboardPosition).remove(
					chessboardPosition);
			int newMoveLength = move.getMoveLength();
			if (newMoveLength > maxMoveLength) {
				validCaptureMoves.clear();
				maxMoveLength = newMoveLength;
				validCaptureMoves.add(move);
			}
		}
		return validCaptureMoves;
	}

	protected abstract List<ChessboardPosition> getCapturePositions(int row,
			int column, Chessboard chessboard);

	public abstract List<Move> getValidNoCaptureMoves(int row, int column,
			Chessboard chessboard);

	private List<ChessboardPosition> removeUsedCaptures(
			ChessboardPosition chessboardPosition,
			List<ChessboardPosition> chessboardPositions,
			HashSet<ChessboardPosition> capturedPieces, Chessboard chessboard) {
		List<ChessboardPosition> unusedChessboardPositions = new LinkedList<ChessboardPosition>();
		for (ChessboardPosition nextChessboardPosition : chessboardPositions) {
			if (!capturedPieces.contains(getCaputredPosition(
					chessboardPosition, nextChessboardPosition, chessboard))) {
				unusedChessboardPositions.add(nextChessboardPosition);
			}
		}
		return unusedChessboardPositions;
	}

	private ChessboardPosition getCaputredPosition(
			ChessboardPosition chessboardPosition,
			ChessboardPosition nextChessboardPosition, Chessboard chessboard) {
		int positionRow = chessboardPosition.getRow();
		int positionColumn = chessboardPosition.getColumn();
		int rowDirection = Integer.signum(nextChessboardPosition.getRow()
				- positionRow);
		int columnDirection = Integer.signum(nextChessboardPosition.getColumn()
				- positionColumn);
		int moveLength = 1;
		try {
			while (chessboard.getPieceColorForField(positionRow + moveLength
					* rowDirection, positionColumn + moveLength
					* columnDirection) == null) {
				moveLength++;
			}
			return new ChessboardPosition(positionRow + moveLength
					* rowDirection, positionColumn + moveLength
					* columnDirection);
		} catch (OutOfChessboardException e) {
			return null;
		}
	}
}
