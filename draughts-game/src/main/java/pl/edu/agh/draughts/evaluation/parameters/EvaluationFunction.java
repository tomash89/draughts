package pl.edu.agh.draughts.evaluation.parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

	public Double getParameterValue(IEvaluationParameter evaluationParameter) {
		return this.evaluationFunctionParameters.get(evaluationParameter);
	}

	public void setParameterValue(IEvaluationParameter evaluationParameter,
			Double value) {
		this.evaluationFunctionParameters.put(evaluationParameter, value);
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
			float val = evaluationParameter.calculateValue(chessboard,
					pieceColor);
			// System.out.println(pieceColor.name() + ":"
			// +evaluationParameter.getClass().getCanonicalName() + ":"+val);
			result += evaluationFunctionParameters.get(evaluationParameter)
					* val;
		}
		return result;
	}

	public Properties toProperties() {
		Properties properties = new Properties();
		for (IEvaluationParameter evaluationParameter : this.evaluationFunctionParameters
				.keySet()) {
			properties.put(evaluationParameter.getClass().getCanonicalName(),
					this.evaluationFunctionParameters.get(evaluationParameter)
							.toString());
		}
		return properties;
	}

	public static EvaluationFunction getAllParametersEvaluationFunction() {
		EvaluationFunction evaluationFunction = new EvaluationFunction();
		Map<IEvaluationParameter, Double> parameters = new HashMap<IEvaluationParameter, Double>();

		parameters.put(new AttackingPawnsNumberParameter(), 1.0);
		parameters.put(new CentrallyPositionedKingsParameter(), 1.0);
		parameters.put(new CentrallyPositionedPawnsParameter(), 1.0);
		parameters.put(new DefenderPiecesNumberParameter(), 1.0);
		parameters.put(new KingNumberParameter(), 1.0);
		parameters.put(new LonerKingsNumberParameter(), 1.0);
		parameters.put(new LonerPawnsNumberParameter(), 1.0);
		parameters.put(new PawnNumberParameter(), 1.0);
		parameters.put(new PromotionLineDistanceParameter(), 1.0);
		parameters.put(new PromotionLineUnoccupiedFieldsParameter(), 1.0);
		parameters.put(new SafeKingsNumberParameter(), 1.0);
		parameters.put(new SafePawnsNumberParameter(), 1.0);
		// parameters.put(new TriangleParameter(), 1.0);

		evaluationFunction.setEvaluationFunctionParameters(parameters);

		return evaluationFunction;
	}

	public EvaluationFunction() {

	}

	public EvaluationFunction(String fileName) {
		Properties properties = new Properties();
		Map<IEvaluationParameter, Double> parameters = new HashMap<IEvaluationParameter, Double>();
		try {
			properties.load(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		for (Object className : properties.keySet()) {
			try {
				IEvaluationParameter evaluationParameter = (IEvaluationParameter) Class
						.forName((String) className).newInstance();
				double weight = Double.parseDouble((String) properties
						.get(className));
				parameters.put(evaluationParameter, weight);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setEvaluationFunctionParameters(parameters);
	}
}
