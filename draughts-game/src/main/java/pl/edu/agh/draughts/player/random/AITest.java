package pl.edu.agh.draughts.player.random;

import java.util.Observable;
import java.util.Observer;

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
            draughtsEngine.setWhitePlayer(new SmartPlayer(2));
            draughtsEngine.setBlackPlayer(new RandomPlayer(0));
            draughtsEngine.initializeGame();
            GameResult result = draughtsEngine.testGame();
            if (result == GameResult.BLACK_WON) {
                blackWonTimes++;
            } else if (result == GameResult.WHITE_WON) {
                whiteWonTimes++;
            } else if(result == GameResult.DRAW) {
                draws++;
            }
        }
        System.out.println("Black: " + blackWonTimes + " white: " + whiteWonTimes + " draws: " + draws);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        AITest aiTest = new AITest();
        aiTest.test();
    }

}
