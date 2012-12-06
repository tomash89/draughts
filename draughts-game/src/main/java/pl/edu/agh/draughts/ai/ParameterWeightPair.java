package pl.edu.agh.draughts.ai;

import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class ParameterWeightPair {
    private final IEvaluationParameter evaluationParameter;
    private double weight;

    public ParameterWeightPair(IEvaluationParameter evaluationParameter, double weight) {
        this.evaluationParameter = evaluationParameter;
        this.weight = weight;
    }

    public double calculateValue(Chessboard chessboard, PieceColor pieceColor) throws InvalidPieceException {
        return weight * evaluationParameter.calculateValue(chessboard, pieceColor);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }
}
