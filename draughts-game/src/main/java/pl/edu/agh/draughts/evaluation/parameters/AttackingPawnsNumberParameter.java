package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * Number of attacking pawns - i.e. positioned in three topmost rows
 * 
 * @author Krzysztof
 * 
 */
public class AttackingPawnsNumberParameter extends AbstractEvaluationParameter {

	private static final int TOPMOST_ROW_COUNT = 3;

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int firstLowermostRow = chessboard.getFirstTopmostRow(pieceColor,
				TOPMOST_ROW_COUNT);
		int attackingPieceCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		for (int i = firstLowermostRow; i < firstLowermostRow
				+ TOPMOST_ROW_COUNT; i++) {
			for (int j = 0; j < Chessboard.CHESSBOARD_SIZE; j++) {
				if (chessboardTable[i][j] != null
						&& chessboardTable[i][j].getPieceColor().equals(
								pieceColor)
						&& chessboardTable[i][j] instanceof Pawn) {
					attackingPieceCount++;
				}
			}
		}
		return attackingPieceCount;
	}

}
