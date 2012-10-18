package pl.edu.agh.draughts.game.elements;

public class Piece {

	protected PieceColor pieceColor;

	public Piece(PieceColor pieceColor) {
		super();
		this.pieceColor = pieceColor;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

}
