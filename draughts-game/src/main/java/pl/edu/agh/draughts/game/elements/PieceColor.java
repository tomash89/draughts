package pl.edu.agh.draughts.game.elements;

public enum PieceColor {

	BLACK("x", "X"), WHITE("o", "O");

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

}
