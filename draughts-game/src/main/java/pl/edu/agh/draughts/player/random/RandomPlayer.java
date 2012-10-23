package pl.edu.agh.draughts.player.random;

import java.util.List;
import java.util.Random;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class RandomPlayer {

	public static void main(String args[]) {
		Random random = new Random();
		DraughtsEngine draughtsEngine = new DraughtsEngine();
		draughtsEngine.initializeGame();
		int move = 0;
		PieceColor[] pieceColors = PieceColor.values();

		List<Move> possibleMoves = draughtsEngine
				.getPossibleMoves(pieceColors[move]);
		while (!possibleMoves.isEmpty()) {
			int moveSelection = random.nextInt(possibleMoves.size());
			draughtsEngine.doMove(possibleMoves.get(moveSelection));
			move = (move + 1) % 2;
			draughtsEngine.printChessboard();
			possibleMoves = draughtsEngine.getPossibleMoves(pieceColors[move]);
		}
	}

}
