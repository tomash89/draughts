package pl.edu.agh.draughts.game;

import java.util.List;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class DraughtsEngine {

	private Chessboard chessboard;

	public void initializeGame() {
		this.chessboard = new Chessboard();
		System.out.println(this.chessboard);
	}

	public List<Move> getPossibleMoves(PieceColor pieceColor) {
		List<Move> result = chessboard.getCaptureMoves(pieceColor);
		if (result == null || result.isEmpty()) {
			result = chessboard.getNoCaptureMoves(pieceColor);
		}
		return result;
	}

	public void doMove(Move move) {
		move.doMove(this.chessboard);
	}

	public void printChessboard() {
		System.out.println(this.chessboard);
	}
}
