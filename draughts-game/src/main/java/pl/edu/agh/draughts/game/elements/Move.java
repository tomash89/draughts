package pl.edu.agh.draughts.game.elements;

import java.util.LinkedList;
import java.util.Queue;

public class Move {

	private Piece piece;

	private int pieceRow;

	private int pieceColumn;

	private Queue<ChessboardPosition> steps = new LinkedList<ChessboardPosition>();

	public Move(Piece piece, int pieceRow, int pieceColumn) {
		this.piece = piece;
		this.pieceRow = pieceRow;
		this.pieceColumn = pieceColumn;
	}

	public Piece getPiece() {
		return piece;
	}

	public void addNextStep(ChessboardPosition chessboardPosition) {
		steps.add(chessboardPosition);
	}

	public void doMove(Chessboard chessboard) {
		for (ChessboardPosition chessboardPosition : steps) {
			int rowDirection = Integer.signum(chessboardPosition.getRow()
					- this.pieceRow);
			int columnDirection = Integer.signum(chessboardPosition.getColumn()
					- this.pieceColumn);
			while (chessboardPosition.getRow() != this.pieceRow) {
				chessboard.capture(pieceRow, pieceColumn);
				pieceRow += rowDirection;
				pieceColumn += columnDirection;
			}
			chessboard.movePiece(pieceRow, pieceColumn, piece);
		}
	}

	public int getMoveLength() {
		return steps.size();
	}
}
