package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * number of defender pieces - i.e. together pawns and kings situated in the two
 * lowermost rows
 * 
 * @author Krzysztof
 * 
 */
public class DefenderPiecesNumberParameter extends AbstractEvaluationParameter {

	private static final int LOWERMOST_ROW_COUNT = 2;

	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		int firstLowermostRow = chessboard.getFirstLowermostRow(pieceColor,
				LOWERMOST_ROW_COUNT);
		int defenderPieceCount = 0;
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		for (int i = firstLowermostRow; i < firstLowermostRow
				+ LOWERMOST_ROW_COUNT; i++) {
			for (int j = 0; j < Chessboard.CHESSBOARD_SIZE; j++) {
				if (chessboardTable[i][j] != null
						&& chessboardTable[i][j].getPieceColor().equals(
								pieceColor)) {
					defenderPieceCount++;
				}
			}
		}
		return defenderPieceCount;
	}

}
