package pl.edu.agh.draughts.evaluation.parameters;

import java.util.Map;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class EvaluationFunction implements IEvaluationParameter {

	private Map<IEvaluationParameter, Double> evaluationFunctionParameters;

	public void setEvaluationFunctionParameters(
			Map<IEvaluationParameter, Double> evaluationFunctionParameters) {
		this.evaluationFunctionParameters = evaluationFunctionParameters;
	}

	public Map<IEvaluationParameter, Double> getEvaluationFunctionParameters() {
		return this.evaluationFunctionParameters;
	}

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		PieceColor opponentColor;
		float result = 0;
		switch (pieceColor) {
		case BLACK:
			opponentColor = PieceColor.WHITE;
			break;
		case WHITE:
			opponentColor = PieceColor.BLACK;
			break;
		default:
			throw new InvalidPieceException();
		}
		for (IEvaluationParameter evaluationParameter : this.evaluationFunctionParameters
				.keySet()) {
			result += evaluationFunctionParameters.get(evaluationParameter)
					* (evaluationParameter.calculateValue(chessboard,
							pieceColor) - evaluationParameter.calculateValue(
							chessboard, opponentColor));
		}
		return result;
	}

}
