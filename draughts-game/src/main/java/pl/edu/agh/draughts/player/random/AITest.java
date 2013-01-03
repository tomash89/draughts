package pl.edu.agh.draughts.player.random;

import java.util.Observable;
import java.util.Observer;

import pl.edu.agh.draughts.ai.RandomPlayer;
import pl.edu.agh.draughts.ai.SmartPlayer;
import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.GameResult;

public class AITest implements Observer {

    int blackWonTimes = 0;
    int whiteWonTimes = 0;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GameResult) {
            if (arg == GameResult.BLACK_WON) {
                blackWonTimes++;
            } else if (arg == GameResult.WHITE_WON) {
                whiteWonTimes++;
            }
        }
        System.out.println("Black: " + blackWonTimes + " white: " + whiteWonTimes);
    }

    public void test() {
        for (int i = 0; i < 100; i++) {
            DraughtsEngine draughtsEngine = new DraughtsEngine();
            draughtsEngine.addObserver(this);
            draughtsEngine.setWhitePlayer(new SmartPlayer(3));
            draughtsEngine.setBlackPlayer(new RandomPlayer(0));
            draughtsEngine.initializeGame();
            draughtsEngine.tryToMoveAutomatically();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        AITest aiTest = new AITest();
        aiTest.test();
    }

}
