package pl.edu.agh.draughts.game.elements;

import java.util.List;

public abstract class Piece {

	protected PieceColor pieceColor;

	private Chessboard chessboard;

	public Piece(PieceColor pieceColor, Chessboard chessboard) {
		super();
		this.pieceColor = pieceColor;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public Chessboard getChessboard() {
		return this.chessboard;
	}

	public abstract List<Move> getValidCaptureMoves(int row, int column,
			Chessboard chessboard);

	public abstract List<Move> getValidNoCaptureMoves(int row, int column,
			Chessboard chessboard);
}
