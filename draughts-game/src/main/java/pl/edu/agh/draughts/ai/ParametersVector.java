package pl.edu.agh.draughts.ai;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.draughts.evaluation.parameters.AttackingPawnsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.CentrallyPositionedKingsParameter;
import pl.edu.agh.draughts.evaluation.parameters.CentrallyPositionedPawnsParameter;
import pl.edu.agh.draughts.evaluation.parameters.DefenderPiecesNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.IEvaluationParameter;
import pl.edu.agh.draughts.evaluation.parameters.KingNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.LonerKingsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.LonerPawnsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.PawnNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.PromotionLineDistanceParameter;
import pl.edu.agh.draughts.evaluation.parameters.PromotionLineUnoccupiedFieldsParameter;
import pl.edu.agh.draughts.evaluation.parameters.SafeKingsNumberParameter;
import pl.edu.agh.draughts.evaluation.parameters.SafePawnsNumberParameter;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class ParametersVector {
    private final List<ParameterWeightPair> parameters = new ArrayList<ParameterWeightPair>();

    public static ParametersVector getAllParametersVector() {
        ParametersVector parametersVector = new ParametersVector();
        parametersVector.addParameter(new AttackingPawnsNumberParameter(), 1.0);
        parametersVector.addParameter(new CentrallyPositionedKingsParameter(), 1.0);
        parametersVector.addParameter(new CentrallyPositionedPawnsParameter(), 1.0);
        parametersVector.addParameter(new DefenderPiecesNumberParameter(), 1.0);
        parametersVector.addParameter(new KingNumberParameter(), 1.0);
        parametersVector.addParameter(new LonerKingsNumberParameter(), 1.0);
        parametersVector.addParameter(new LonerPawnsNumberParameter(), 1.0);
        parametersVector.addParameter(new PawnNumberParameter(), 1.0);
        parametersVector.addParameter(new PromotionLineDistanceParameter(), 1.0);
        parametersVector.addParameter(new PromotionLineUnoccupiedFieldsParameter(), 1.0);
        parametersVector.addParameter(new SafeKingsNumberParameter(), 1.0);
        parametersVector.addParameter(new SafePawnsNumberParameter(), 1.0);
        return parametersVector;
    }

    public List<ParameterWeightPair> getParameters() {
        return parameters;
    }

    public void addParameter(IEvaluationParameter evaluationParameter, double weight) {
        parameters.add(new ParameterWeightPair(evaluationParameter, weight));
    }

    public void addParameter(ParameterWeightPair parameterWeightPair) {
        parameters.add(parameterWeightPair);
    }

    public double calculateValue(Chessboard chessboard, PieceColor pieceColor) throws InvalidPieceException {
        double sum = 0.0;
        for (ParameterWeightPair parameterWeightPair : parameters) {
            sum += parameterWeightPair.calculateValue(chessboard, pieceColor);
        }
        return sum;
    }

}
