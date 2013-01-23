package pl.edu.agh.draughts.ai;

import pl.edu.agh.draughts.evaluation.parameters.EvaluationFunction;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class SmartPlayer implements AIPlayer {

	private final int depth;
	private final EvaluationFunction evaluationFunction;

	public SmartPlayer(int depth, EvaluationFunction evaluationFunction) {
		this.depth = depth;
		this.evaluationFunction = evaluationFunction;
	}

	public SmartPlayer(int depth) {
		this.depth = depth;
		this.evaluationFunction = EvaluationFunction
				.getAllParametersEvaluationFunction();
	}

	public SmartPlayer(int depth, String evaluationFunctionFile) {
		this.depth = depth;
		this.evaluationFunction = new EvaluationFunction(evaluationFunctionFile);
	}

	@Override
	public Move suggestMove(Chessboard chessboard, PieceColor pieceColor) {

		// try {
		// Thread.sleep(delay);
		// } catch (InterruptedException e) {
		// e.printStackTrace(System.err);
		// }

		return MinMax.getMinMaxMove(chessboard, pieceColor,
				this.evaluationFunction, depth);
	}

	@Override
	public boolean isUserControllable() {
		return false;
	}
}
