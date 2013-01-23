package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class MinMax {
	static class BestMoveHolder {
		private Move bestMove;

		public Move getBestMove() {
			return bestMove;
		}

		public void setBestMove(Move bestMove) {
			this.bestMove = bestMove;
		}
	}

	public static Move getMinMaxMove(Chessboard chessboard,
			PieceColor pieceColor, EvaluationFunction evaluationFunction,
			int callsLimit) {
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
			BestMoveHolder bestMoveHolder = new BestMoveHolder();
			double value = getMaxMoveValue(evaluationChessboard,
					pieceColor.getOpponentColor(), evaluationFunction,
					callsLimit - 1, bestMoveHolder);
			if (maxValue == null || value > maxValue) {
				maxMove = move;
				maxValue = value;
			}
		}
		return maxMove;
	}

	public static double getMaxMoveValue(Chessboard chessboard,
			PieceColor pieceColor, EvaluationFunction evaluationFunction,
			int callsLimit, BestMoveHolder bestMoveHolder) {
		if (callsLimit <= 0) {
			Chessboard evaluationChessboard = new Chessboard(chessboard);
			try {
				return evaluationFunction.calculateValue(evaluationChessboard,
						pieceColor.getOpponentColor());
			} catch (InvalidPieceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Move> possibleMoves = chessboard.getPossibleMoves(pieceColor);
		if (possibleMoves.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		Double maxValue = null;
		for (Move move : possibleMoves) {
			Chessboard evaluationChessboard = new Chessboard(chessboard);
			Move copyMove = move.copyOf();
			copyMove.doMove(evaluationChessboard);
			double value = getMaxMoveValue(chessboard,
					pieceColor.getOpponentColor(), evaluationFunction,
					callsLimit - 1, bestMoveHolder);
			if (maxValue == null || value > maxValue) {
				maxValue = value;
			}
		}
		return maxValue;
	}
}
