package pl.edu.agh.draughts.gui;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.draughts.game.elements.ChessboardPosition;

public class ChessboardControler {
    public static List<ChessboardPosition> makePostionsLine(ChessboardPosition start, ChessboardPosition stop) {
        int columnDiff = stop.getColumn() - start.getColumn();
        int rowDiff = stop.getRow() - start.getRow();
        if(Math.abs(rowDiff) != Math.abs(columnDiff)) {
            return null;
        }
        
        int rowDirection = Integer.signum(rowDiff);
        int columnDirection = Integer.signum(columnDiff);
        List<ChessboardPosition> result = new LinkedList<ChessboardPosition>();
        int pieceRow = start.getRow();
        int pieceColumn = start.getColumn();
        ChessboardPosition lastAddedPosition = null;
        while (!stop.equals(lastAddedPosition)) {
            pieceRow += rowDirection;
            pieceColumn += columnDirection;
            lastAddedPosition = new ChessboardPosition(pieceRow, pieceColumn);
            result.add(lastAddedPosition);
        }
        return result;
    }
}
