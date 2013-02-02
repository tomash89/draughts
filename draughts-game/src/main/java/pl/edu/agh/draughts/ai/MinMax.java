package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class MinMax {

    private final PieceColor player;

    public MinMax(PieceColor pieceColor) {
        this.player = pieceColor;
    }

    public Move getMinMaxMove(Chessboard chessboard, PieceColor pieceColor,
            EvaluationFunction evaluationFunction, int callsLimit) {
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        // System.out.println(possibleMoves.size());
        // for (Move move : possibleMoves) {
        // System.out.println(move);
        // }
        if (possibleMoves.isEmpty()) {
            return null;
        }
        Double maxValue = null;
        Move maxMove = null;
        for (Move move : possibleMoves) {
            Chessboard evaluationChessboard = chessboard.copyOf();
            Move copyMove = move.copyOf();
            copyMove.doMove(evaluationChessboard);
            double value = getMaxMoveValue(evaluationChessboard, pieceColor.getOpponentColor(),
                    evaluationFunction, callsLimit - 1, Double.MIN_VALUE, Double.MAX_VALUE);
            if (maxValue == null || value > maxValue) {
                maxMove = move;
                maxValue = value;
            }
        }
        return maxMove;
    }

    public double evaluatePosition(Chessboard chessboard, EvaluationFunction evaluationFunction) {
        try {
            double playerValue = evaluationFunction.calculateValue(chessboard, player);
            double opponentValue = evaluationFunction.calculateValue(chessboard,
                    player.getOpponentColor());
            return playerValue - opponentValue;
        } catch (InvalidPieceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Double getMaxMoveValue(Chessboard chessboard, PieceColor pieceColor,
            EvaluationFunction evaluationFunction, int callsLimit, double alpha, double beta) {
        if (callsLimit <= 0) {
            return evaluatePosition(chessboard, evaluationFunction);
        }
        List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
        if (possibleMoves.isEmpty()) {
            if (pieceColor == player) {
                return Double.MAX_VALUE;
            } else {
                return Double.MIN_VALUE;
            }
        }
        if (pieceColor == player) {
            Double newAlpha = alpha;
            for (Move move : possibleMoves) {
                Chessboard evaluationChessboard = chessboard.copyOf();
                Move copyMove = move.copyOf();
                copyMove.doMove(evaluationChessboard);
                if (pieceColor == player) {
                    Double value = getMaxMoveValue(chessboard, pieceColor.getOpponentColor(),
                            evaluationFunction, callsLimit - 1, newAlpha, beta);
                    if (value != null && value > newAlpha) {
                        newAlpha = value;
                    }
                    if (newAlpha >= beta) {
                        return null;
                    }
                }
            }
            return newAlpha;
        } else {
            Double newBeta = beta;
            for (Move move : possibleMoves) {
                Chessboard evaluationChessboard = chessboard.copyOf();
                Move copyMove = move.copyOf();
                copyMove.doMove(evaluationChessboard);
                if (pieceColor == player) {
                    Double value = getMaxMoveValue(chessboard, pieceColor.getOpponentColor(),
                            evaluationFunction, callsLimit - 1, alpha, newBeta);
                    if (value != null && value < newBeta) {
                        newBeta = value;
                    }
                    if (newBeta <= alpha) {
                        return null;
                    }
                }
            }
            return newBeta;
        }
    }
}
