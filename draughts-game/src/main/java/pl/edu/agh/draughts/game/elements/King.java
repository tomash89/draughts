package pl.edu.agh.draughts.game.elements;

public class King extends Piece {

	public King(PieceColor pieceColor) {
		super(pieceColor);
	}

	@Override
	public String toString() {
		return super.pieceColor.getKingString();
	}
}
