package pl.edu.agh.draughts.player.random;

import pl.edu.agh.draughts.ai.AIPlayer;
import pl.edu.agh.draughts.ai.OldSmartPlayer;
import pl.edu.agh.draughts.ai.RandomPlayer;
import pl.edu.agh.draughts.ai.SmartPlayer;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.GameResult;

public class AITest {

	private int blackWonTimes = 0;
	private int whiteWonTimes = 0;
	private int draws = 0;

	public void test() {
        for (int i = 0; i < 1000; i++) {
			DraughtsEngine draughtsEngine = new DraughtsEngine();
			// draughtsEngine.setWhitePlayer(new SmartPlayer(2));
            AIPlayer a = new SmartPlayer(3, "src/main/resources/my_guess.txt");
            AIPlayer a1 = new SmartPlayer(2, "src/main/resources/winner_d2.txt");
            AIPlayer b = new SmartPlayer(1);// new SmartPlayer(1);
            AIPlayer c = new RandomPlayer(0);
            AIPlayer d = new OldSmartPlayer();
            draughtsEngine.setWhitePlayer(a);
            draughtsEngine.setBlackPlayer(b);
			draughtsEngine.initializeGame();
			GameResult result = draughtsEngine.testGame();
			System.out.println(draughtsEngine.getChessboard());
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
