package pl.edu.agh.draughts.game.elements;

import java.util.List;

public class King extends Piece {

	public King(PieceColor pieceColor, Chessboard chessboard) {
		super(pieceColor, chessboard);
	}

	@Override
	public String toString() {
		return super.pieceColor.getKingString();
	}

	@Override
	public List<Move> getValidCaptureMoves(int row, int column,
			Chessboard chessboard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Move> getValidNoCaptureMoves(int row, int column,
			Chessboard chessboard) {
		// TODO Auto-generated method stub
		return null;
	}
}
