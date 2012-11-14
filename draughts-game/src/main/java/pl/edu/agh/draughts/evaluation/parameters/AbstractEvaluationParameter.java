package pl.edu.agh.draughts.evaluation.parameters;

/**
 * Number of attacking pawns - i.e. positioned in three topmost rows
 * 
 * @author Krzysztof
 * 
 */
public abstract class AbstractEvaluationParameter implements
		IEvaluationParameter {

	private float weight;

	public float getWeight() {
		return this.weight;
	}

}
