package pl.edu.agh.draughts.player.random;

import pl.edu.agh.draughts.ai.AIPlayer;
import pl.edu.agh.draughts.ai.RandomPlayer;
import pl.edu.agh.draughts.ai.SmartPlayer;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.GameResult;

public class AITest {

	private int blackWonTimes = 0;
	private int whiteWonTimes = 0;
	private int draws = 0;

	public void test() {
		for (int i = 0; i < 100; i++) {
			DraughtsEngine draughtsEngine = new DraughtsEngine();
			// draughtsEngine.setWhitePlayer(new SmartPlayer(2));
            AIPlayer a = new SmartPlayer(1, "src/main/resources/winner.txt");
			AIPlayer b = new RandomPlayer(0);
			draughtsEngine.setWhitePlayer(a);
			draughtsEngine.setBlackPlayer(b);
			draughtsEngine.initializeGame();
			GameResult result = draughtsEngine.testGame();
			if (result == GameResult.BLACK_WON) {
				blackWonTimes++;
			} else if (result == GameResult.WHITE_WON) {
				whiteWonTimes++;
			} else if (result == GameResult.DRAW) {
				draws++;
			}
		}
		System.out.println("White: " + whiteWonTimes + "\nBlack: "
				+ blackWonTimes + "\nDraws: " + draws);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AITest aiTest = new AITest();
		aiTest.test();
	}

}
