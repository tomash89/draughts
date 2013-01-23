package pl.edu.agh.draughts.evaluation.parameters;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

/**
 * Aggregated distance of the pawns to promotion line;
 * 
 * @author Krzysztof
 * 
 */
public class PromotionLineDistanceParameter extends AbstractEvaluationParameter {

	private static final int MAX_PROMOTION_LINE_DISTANCE = 4 * (5 + 6 + 7);
	private static final int MAX_PAWNS_COUNT = 12;
	private static final int AVERAGE_MAX_DISTANCE = 6;
	
	@Override
	public float calculateValue(Chessboard chessboard, PieceColor pieceColor)
			throws InvalidPieceException {
		Piece[][] chessboardTable = chessboard.getChessboardTable();
		// return promotion line row number
		int promotionLineNumber = chessboard.getFirstTopmostRow(pieceColor, 1);
		int firstLineNumber = Chessboard.CHESSBOARD_SIZE - promotionLineNumber
				- 1;
		int direction = Integer.signum(promotionLineNumber - firstLineNumber);
		int aggregatedDistane = 0;
		
		int notExistingCount = MAX_PAWNS_COUNT;
		
		for (int i = firstLineNumber; i != promotionLineNumber; i += direction) {
			for (int j = 0; j < Chessboard.CHESSBOARD_SIZE; j++) {
				if (chessboardTable[i][j] != null
						&& chessboardTable[i][j].getPieceColor().equals(
								pieceColor)
						&& chessboardTable[i][j] instanceof Pawn) {
					aggregatedDistane += Math.abs(promotionLineNumber - i);
					notExistingCount--;
				}
			}
		}
		return MAX_PROMOTION_LINE_DISTANCE - aggregatedDistane - notExistingCount * AVERAGE_MAX_DISTANCE;
	}

}
