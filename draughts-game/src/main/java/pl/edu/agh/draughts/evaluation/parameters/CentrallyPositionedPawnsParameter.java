package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;

/**
 * Numbers of centrally positioned - i.e. situated on the eight central squares
 * of the board pawns
 * 
 * @author Krzysztof
 * 
 */
public class CentrallyPositionedPawnsParameter extends PawnNumberParameter {

	public static final int CENTER_WIDTH = 4;
	public static final int CENTER_HEIGHT = 2;

	public CentrallyPositionedPawnsParameter() {
		super.startRow = (Chessboard.CHESSBOARD_SIZE - CENTER_HEIGHT) / 2;
		super.endRow = startRow + CENTER_HEIGHT;

		super.startColumn = (Chessboard.CHESSBOARD_SIZE - CENTER_WIDTH) / 2;
		super.endColumn = startColumn + CENTER_WIDTH;

	}
}
