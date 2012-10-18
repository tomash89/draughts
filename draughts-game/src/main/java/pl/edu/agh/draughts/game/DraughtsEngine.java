package pl.edu.agh.draughts.game;

import pl.edu.agh.draughts.game.elements.Chessboard;

public class DraughtsEngine {

	private Chessboard chessboard;

	public void initializeGame() {
		this.chessboard = new Chessboard();
		System.out.println(this.chessboard);
	}

	public static void main(String args[]) {
		new DraughtsEngine().initializeGame();
	}
}
