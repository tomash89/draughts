package pl.agh.edu.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;

public class CentrallyPositionedKingsParameter extends KingNumberParameter {

	public CentrallyPositionedKingsParameter() {
		super.startRow = (Chessboard.CHESSBOARD_SIZE - CentrallyPositionedPawnsParameter.CENTER_HEIGHT) / 2;
		super.endRow = startRow + CentrallyPositionedPawnsParameter.CENTER_HEIGHT;

		super.startColumn = (Chessboard.CHESSBOARD_SIZE - CentrallyPositionedPawnsParameter.CENTER_WIDTH) / 2;
		super.endColumn = startColumn + CentrallyPositionedPawnsParameter.CENTER_WIDTH;

	}

}
