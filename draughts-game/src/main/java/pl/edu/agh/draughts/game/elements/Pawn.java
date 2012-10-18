package pl.edu.agh.draughts.game.elements;

public class Pawn extends Piece {

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
	}

	@Override
	public String toString() {
		return super.pieceColor.getPawnString();
	}
}
