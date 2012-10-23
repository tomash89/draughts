package pl.edu.agh.draughts.game.elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	public List<Move> getValidCaptureMoves(int row, int column,
			Chessboard chessboard) {
		List<Move> validCaptureMoves = new LinkedList<Move>();
		Map<ChessboardPosition, List<ChessboardPosition>> capturesMap = new HashMap<ChessboardPosition, List<ChessboardPosition>>();
		ChessboardPosition initialPosition = new ChessboardPosition(row, column);
		List<ChessboardPosition> unckeckedChessboardPositions = new LinkedList<ChessboardPosition>();
		unckeckedChessboardPositions.add(initialPosition);
		while (!unckeckedChessboardPositions.isEmpty()) {
			List<ChessboardPosition> newChessboardPositions = new LinkedList<ChessboardPosition>();
			for (ChessboardPosition chessboardPosition : unckeckedChessboardPositions) {
				if (!capturesMap.containsKey(chessboardPosition)) {
					List<ChessboardPosition> capturePositions = getCapturePositions(
							chessboardPosition.getRow(),
							chessboardPosition.getColumn(), chessboard);
					capturesMap.put(chessboardPosition, capturePositions);
					newChessboardPositions.addAll(capturePositions);
				}
			}
			unckeckedChessboardPositions = newChessboardPositions;
		}
		int maxMoveLength = 0;
		while (!capturesMap.get(initialPosition).isEmpty()) {
			ChessboardPosition previousChessboardPosition = null;
			ChessboardPosition chessboardPosition = initialPosition;
			Move move = new Move(this, row, column);
			List<ChessboardPosition> nextPositions = capturesMap
					.get(chessboardPosition);

			while (!nextPositions.isEmpty()) {
				previousChessboardPosition = chessboardPosition;
				chessboardPosition = nextPositions.get(0);
				move.addNextStep(chessboardPosition);
				nextPositions = capturesMap.get(chessboardPosition);
				nextPositions.remove(previousChessboardPosition);
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

	private List<ChessboardPosition> getCapturePositions(int row, int column,
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
