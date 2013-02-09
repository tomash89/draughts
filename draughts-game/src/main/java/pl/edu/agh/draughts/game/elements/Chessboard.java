package pl.edu.agh.draughts.game.elements;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;
import pl.edu.agh.draughts.game.exceptions.OutOfChessboardException;

public class Chessboard {

	public static final int CHESSBOARD_SIZE = 8;

	public static final int INITIAL_PAWNS__ROWS_COUNT = 3;

	private final Piece[][] chessboardTable;

	public Chessboard() {
		this.chessboardTable = new Piece[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
		initializeChessboard();
	}

    public Chessboard(Chessboard chessboard) {
        this.chessboardTable = new Piece[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                if (chessboard.chessboardTable[i][j] != null) {
                    this.chessboardTable[i][j] = chessboard.chessboardTable[i][j].copyOf();
                }
            }
        }
    }

    public Chessboard copyOf() {
        return new Chessboard(this);
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
	
	public int getPiecesCount() {
	    int count = 0;
	    for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                if(this.chessboardTable[i][j] != null) {
                    count++;
                }
            }
	    }
	    return count;
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

	public PieceColor getPieceColorForField(int row, int column)
			throws OutOfChessboardException {
		try {
			if (this.chessboardTable[row][column] == null) {
				return null;
			}
		} catch (IndexOutOfBoundsException exception) {
			throw new OutOfChessboardException();
		}
		return this.chessboardTable[row][column].getPieceColor();
	}

	void capture(int row, int column) {
		this.chessboardTable[row][column] = null;
	}

	void movePiece(int row, int column, Piece piece) {
		this.chessboardTable[row][column] = piece;
	}

    public List<Move> getPossibleMoves(PieceColor pieceColor) {
        List<Move> result = getCaptureMoves(pieceColor);
        if (result == null || result.isEmpty()) {
            result = getNoCaptureMoves(pieceColor);
        }
        return result;
    }

	public List<Move> getCaptureMoves(PieceColor pieceColor) {
		List<Move> captureMoves = new LinkedList<Move>();
		int maxMoveLength = 0;
		for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			for (int j = 0; j < CHESSBOARD_SIZE; j++) {
				Piece piece = this.chessboardTable[i][j];
				if (piece != null && piece.getPieceColor().equals(pieceColor)) {
					List<Move> newCaptureMoves = piece.getValidCaptureMoves(i,
							j, this);
					if (!newCaptureMoves.isEmpty()) {
						int newMoveLength = newCaptureMoves.get(0)
								.getMoveLength();
						if (newMoveLength > maxMoveLength) {
							maxMoveLength = newMoveLength;
							captureMoves.clear();
						}
						if (newMoveLength >= maxMoveLength) {
							captureMoves.addAll(newCaptureMoves);
						}
					}
				}
			}
		}
		return captureMoves;
	}

	public List<Move> getNoCaptureMoves(PieceColor pieceColor) {
		List<Move> moves = new LinkedList<Move>();
		for (int i = 0; i < CHESSBOARD_SIZE; i++) {
			for (int j = 0; j < CHESSBOARD_SIZE; j++) {
				Piece piece = this.chessboardTable[i][j];
				if (piece != null && piece.getPieceColor().equals(pieceColor)) {
					moves.addAll(piece.getValidNoCaptureMoves(i, j, this));
				}
			}
		}
		return moves;
	}

	public Piece[][] getChessboardTable() {
		return this.chessboardTable;
	}

	public int getFirstLowermostRow(PieceColor pieceColor,
			int lowermostRowsCount) throws InvalidPieceException {
		if (pieceColor == null) {
			throw new InvalidPieceException();
		}
		if (pieceColor.equals(PieceColor.WHITE)) {
			return 0;
		}
		return CHESSBOARD_SIZE - lowermostRowsCount;
	}

	public int getFirstTopmostRow(PieceColor pieceColor, int topmostRowsCount)
			throws InvalidPieceException {
		if (pieceColor == null) {
			throw new InvalidPieceException();
		}
		if (pieceColor.equals(PieceColor.WHITE)) {
			return CHESSBOARD_SIZE - topmostRowsCount;
		}
		return 0;

	}



}
