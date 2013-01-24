package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class MinMax {

    private PieceColor player;
    
    public MinMax(PieceColor pieceColor) {
        this.player = pieceColor;
    }
    
	public Move getMinMaxMove(Chessboard chessboard, PieceColor pieceColor, EvaluationFunction evaluationFunction, int callsLimit) {
		List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
		if (possibleMoves.isEmpty()) {
			return null;
		}
		Double maxValue = null;
		Move maxMove = null;
		for (Move move : possibleMoves) {
			Chessboard evaluationChessboard = new Chessboard(chessboard);
			Move copyMove = move.copyOf();
			copyMove.doMove(evaluationChessboard);
			double value = getMaxMoveValue(evaluationChessboard, pieceColor.getOpponentColor(), evaluationFunction, callsLimit - 1);
			if (maxValue == null || value > maxValue) {
				maxMove = move;
				maxValue = value;
			}
		}
		return maxMove;
	}

	public double evaluatePosition(Chessboard chessboard, EvaluationFunction evaluationFunction) {
	    try {
            float playerValue = evaluationFunction.calculateValue(chessboard, player);
            float opponentValue = evaluationFunction.calculateValue(chessboard, player.getOpponentColor());
            return playerValue - opponentValue;
        } catch (InvalidPieceException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}
	
	public double getMaxMoveValue(Chessboard chessboard, PieceColor pieceColor, EvaluationFunction evaluationFunction,
			int callsLimit) {
		if (callsLimit <= 0) {
			return evaluatePosition(chessboard, evaluationFunction);
		}
		List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
		if (possibleMoves.isEmpty()) {
		    if(pieceColor == player) {
		        return Integer.MAX_VALUE;
		    } else {
		        return Integer.MIN_VALUE;
		    }
		}
		Double maxValue = null;
		for (Move move : possibleMoves) {
			Chessboard evaluationChessboard = new Chessboard(chessboard);
			Move copyMove = move.copyOf();
			copyMove.doMove(evaluationChessboard);
			double value = - getMaxMoveValue(chessboard,
					pieceColor.getOpponentColor(), evaluationFunction,
					callsLimit - 1);
			if (maxValue == null || value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}
}
