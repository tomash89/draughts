package pl.edu.agh.draughts.game.elements;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Move {

	private Piece piece;

	private int pieceRow;

	private int pieceColumn;

	private Queue<ChessboardPosition> steps = new LinkedList<ChessboardPosition>();


    public Move(Piece piece, int pieceRow, int pieceColumn) {
		this.piece = piece;
		this.pieceRow = pieceRow;
		this.pieceColumn = pieceColumn;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public Queue<ChessboardPosition> getSteps() {
	    return steps;
	}
	
	public void setSteps(Queue<ChessboardPosition> steps) {
	    this.steps = steps;
	}

	public void addNextStep(ChessboardPosition chessboardPosition) {
		steps.add(chessboardPosition);
	}

	public void doMove(Chessboard chessboard) {
		for (ChessboardPosition chessboardPosition : steps) {
			int rowDirection = Integer.signum(chessboardPosition.getRow()
					- this.pieceRow);
			int columnDirection = Integer.signum(chessboardPosition.getColumn()
					- this.pieceColumn);
			while (chessboardPosition.getRow() != this.pieceRow) {
				chessboard.capture(pieceRow, pieceColumn);
				pieceRow += rowDirection;
				pieceColumn += columnDirection;
			}
			chessboard.movePiece(pieceRow, pieceColumn, piece);
		}

		if ((piece.getPieceColor().equals(PieceColor.WHITE) && pieceRow == Chessboard.CHESSBOARD_SIZE - 1)
				|| (piece.getPieceColor().equals(PieceColor.BLACK) && pieceRow == 0)) {
			piece = new King(piece.getPieceColor());
			chessboard.movePiece(pieceRow, pieceColumn, piece);

			System.out.println("New king!");
		}
	}

	public int getMoveLength() {
		return steps.size();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;
        if (pieceRow != other.pieceRow)
            return false;
        if (pieceColumn != other.pieceColumn)
            return false;
        if(piece == null || other.getPiece() == null) {
            return false;
        }
        if(piece.pieceColor != other.getPiece().pieceColor) {
            return false;
        }
        //Checking for King/Pawn is unnecessary in our context
        Iterator<ChessboardPosition> otherStepsIt = other.getSteps().iterator();
        if(steps == null) { // This is actually weird as f..
            if(other.getSteps() == null) {
                return true;
            } else {
                return false;
            }
        }
        for(ChessboardPosition chessboardPosition : steps) {
            if(!otherStepsIt.hasNext()) {
                return false;
            }
            if(!chessboardPosition.equals(otherStepsIt.next())) {
                return false;
            }
        }
        if(otherStepsIt.hasNext()) {
            return false;
        }
        return true;
	    
	}
	
	@Override
	public int hashCode() {
	    // TODO
	    return super.hashCode();
	}
}
