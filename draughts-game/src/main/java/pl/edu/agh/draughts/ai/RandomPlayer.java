package pl.edu.agh.draughts.ai;

import java.util.List;
import java.util.Random;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class RandomPlayer implements AIPlayer {

    private final Random random = new Random();
    private final int delay;

    public RandomPlayer(int delay) {
        this.delay = delay;
    }

    @Override
    public Move suggestMove(Chessboard chessboard, PieceColor pieceColor) {
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        if (!possibleMoves.isEmpty()) {
            int moveSelection = random.nextInt(possibleMoves.size());
            return possibleMoves.get(moveSelection);
        }
        return null;
    }

}
