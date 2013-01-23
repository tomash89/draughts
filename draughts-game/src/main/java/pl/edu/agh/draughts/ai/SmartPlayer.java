package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class SmartPlayer implements AIPlayer {

    private final ParametersVector parametersVector = ParametersVector.getAllParametersVector();
    private final int delay;

    public SmartPlayer(int delay) {
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
        Double bestValue = null;
        Move bestMove = null;
        for (Move move : possibleMoves) {
            Chessboard evaluationChessboard = new Chessboard(chessboard);
            Move copyMove = move.copyOf();
            copyMove.doMove(evaluationChessboard);
            try {
                double value = parametersVector.calculateValue(evaluationChessboard, pieceColor);
                if (bestValue == null || value > bestValue) {
                    bestMove = move;
                    bestValue = value;
                }
            } catch (InvalidPieceException e) {
                e.printStackTrace(System.err);
            }
        }
        return bestMove;
    }

    @Override
    public boolean isUserControllable() {
        return false;
    }
}
