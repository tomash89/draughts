package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class OldSmartPlayer implements AIPlayer {

    private final ParametersVector parametersVector = ParametersVector.getAllParametersVector();

    @Override
    public boolean isUserControllable() {
        return false;
    }

    @Override
    public Move suggestMove(Chessboard chessboard, PieceColor pieceColor) {
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        Double bestValue = null;
        Move bestMove = null;
        for (Move move : possibleMoves) {
            Chessboard evaluationChessboard = new Chessboard(chessboard);
            Move copyMove = move.copyOf();
            copyMove.doMove(evaluationChessboard);

            double value = parametersVector.calculateValue(evaluationChessboard, pieceColor);
            if (bestValue == null || value > bestValue) {
                bestMove = move;
                bestValue = value;
            }
        }
        return bestMove;
    }

}
