package pl.edu.agh.draughts.game.elements;

public enum PieceColor {

	WHITE("o", "O"), BLACK("x", "X");

	private String pawnString;
	private String kingString;

	private PieceColor(String pawnString, String kingString) {
		this.pawnString = pawnString;
		this.kingString = kingString;
	}

	public String getPawnString() {
		return pawnString;
	}

	public String getKingString() {
		return kingString;
	}

	public boolean isAnotherColor(PieceColor pieceColor) {
		if (pieceColor == null)
			return false;
		return !this.equals(pieceColor);
	}
}
