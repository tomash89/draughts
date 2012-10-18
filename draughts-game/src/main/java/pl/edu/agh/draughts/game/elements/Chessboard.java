package pl.edu.agh.draughts.game.elements;

public class Chessboard {

	private static final int CHESSBOARD_SIZE = 8;

	private static final int INITIAL_PAWNS__ROWS_COUNT = 3;

	private Piece[][] chessboardTable;

	public Chessboard() {
		this.chessboardTable = new Piece[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
		initializeChessboard();
	}

	public boolean doMove(Move move) {
		boolean result = false;
		return result;
	}

	private void initializeChessboard() {
		for (int i = 0; i < INITIAL_PAWNS__ROWS_COUNT; i++) {
			for (int j = i % 2; j < CHESSBOARD_SIZE; j += 2) {
				chessboardTable[i][j] = new Pawn(PieceColor.WHITE);
				chessboardTable[CHESSBOARD_SIZE - i - 1][CHESSBOARD_SIZE - j
						- 1] = new Pawn(PieceColor.BLACK);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = CHESSBOARD_SIZE - 1; i >= 0; i--) {
			for (int j = 0; j < CHESSBOARD_SIZE; j++) {
				stringBuilder.append("|");
				if (chessboardTable[i][j] == null) {
					stringBuilder.append(" ");
				} else {
					stringBuilder.append(chessboardTable[i][j]);
				}
			}
			stringBuilder.append("|\n");
		}
		return stringBuilder.toString();
	}
}
