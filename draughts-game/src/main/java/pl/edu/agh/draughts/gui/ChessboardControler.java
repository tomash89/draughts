package pl.edu.agh.draughts.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.ChessboardPosition;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class ChessboardControler {
    
    private ChessboardPanel chessboardPanel;
    private DraughtsEngine draughtsEngine;
    private Random random = new Random();
    
    public static LinkedList<ChessboardPosition> makePostionsLine(ChessboardPosition start, ChessboardPosition stop) {
        int columnDiff = stop.getColumn() - start.getColumn();
        int rowDiff = stop.getRow() - start.getRow();
        if(Math.abs(rowDiff) != Math.abs(columnDiff)) {
            return null;
        }
        
        int rowDirection = Integer.signum(rowDiff);
        int columnDirection = Integer.signum(columnDiff);
        LinkedList<ChessboardPosition> result = new LinkedList<ChessboardPosition>();
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
    
    public ChessboardPanel getChessboardPanel() {
        return chessboardPanel;
    }

    public void setChessboardPanel(ChessboardPanel chessboardPanel) {
        this.chessboardPanel = chessboardPanel;
    }

    public DraughtsEngine getDraughtsEngine() {
        return draughtsEngine;
    }

    public void setDraughtsEngine(DraughtsEngine draughtsEngine) {
        this.draughtsEngine = draughtsEngine;
    }

    public void testMove(PieceLabel piece, ChessboardCell cell) {
        List<Move> possibleMoves = draughtsEngine.getPossibleMoves(piece.getColor());
        Move move = new Move(new Pawn(piece.getColor()), piece.getRow(), piece.getColumn()); // TODO: King
        LinkedList<ChessboardPosition> steps = makePostionsLine(piece.getChessboardPosition(), cell.getChessboardPosition());
        move.setSteps(steps);
        if(possibleMoves.contains(move)) {
           draughtsEngine.doMove(move);
           List<Move> possibleMovesForOpponent = draughtsEngine.getPossibleMoves(PieceColor.BLACK);
           if (!possibleMoves.isEmpty()) {
               int moveSelection = random.nextInt(possibleMoves.size());
               draughtsEngine.doMove(possibleMovesForOpponent.get(moveSelection));
           }
           chessboardPanel.clearPieces();
           chessboardPanel.addPieces(draughtsEngine.getChessboard().getChessboardTable());
        }
    }
}
