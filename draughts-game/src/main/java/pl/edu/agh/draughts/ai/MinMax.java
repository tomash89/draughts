package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class MinMax {
    public static Move getMinMaxMove(Chessboard chessboard, PieceColor pieceColor, ParametersVector parametersVector, int callsLimit) {
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        if(possibleMoves.isEmpty() || callsLimit == 0) {
            return null;
        }
        Double maxValue = null;
        Move maxMove = null;
        for (Move move : possibleMoves) {
            Chessboard evaluationChessboard = new Chessboard(chessboard);
            Move copyMove = move.copyOf();
            copyMove.doMove(evaluationChessboard);
            double value = getMaxMoveValue(chessboard, pieceColor.getOpponentColor(), parametersVector, callsLimit - 1);
            if (maxValue == null || value > maxValue) {
                maxMove = move;
                maxValue = value;
            }
        }
        return maxMove;
    }
    
    public static double getMaxMoveValue(Chessboard chessboard, PieceColor pieceColor, ParametersVector parametersVector, int callsLimit) {
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        if(possibleMoves.isEmpty() || callsLimit == 0) {
            Chessboard evaluationChessboard = new Chessboard(chessboard);
            return parametersVector.calculateValue(evaluationChessboard, pieceColor);
        }
        Double maxValue = null;
        for (Move move : possibleMoves) {
            Chessboard evaluationChessboard = new Chessboard(chessboard);
            Move copyMove = move.copyOf();
            copyMove.doMove(evaluationChessboard);
            double value = getMaxMoveValue(chessboard, pieceColor.getOpponentColor(), parametersVector, callsLimit - 1);
            if (maxValue == null || value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
    
}
