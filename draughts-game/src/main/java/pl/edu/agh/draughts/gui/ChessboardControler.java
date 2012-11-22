package pl.edu.agh.draughts.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.ChessboardPosition;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class ChessboardControler {

    private ChessboardPanel chessboardPanel;
    private DraughtsEngine draughtsEngine;
    private Random random = new Random();

    public static LinkedList<ChessboardPosition> makePostionsLine(ChessboardPosition start,
            ChessboardPosition stop) {
        int columnDiff = stop.getColumn() - start.getColumn();
        int rowDiff = stop.getRow() - start.getRow();
        if (Math.abs(rowDiff) != Math.abs(columnDiff)) {
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

    public void testMove(PieceLabel piece, ChessboardCell cell) throws NotValidMoveException {
        List<Move> possibleMoves = draughtsEngine.getPossibleMoves(piece.getColor());
        Move move = new Move(new Pawn(piece.getColor()), piece.getRow(), piece.getColumn()); // TODO:
                                                                                             // King
        LinkedList<ChessboardPosition> steps = new LinkedList<ChessboardPosition>();
        steps.add(cell.getChessboardPosition());
                
                /*makePostionsLine(piece.getChessboardPosition(),
                cell.getChessboardPosition());*/
        move.setSteps(steps);
        if (possibleMoves.contains(move)) {
            draughtsEngine.doMove(move);
            draughtsEngine.printChessboard();
            List<Move> possibleMovesForOpponent = draughtsEngine.getPossibleMoves(PieceColor.BLACK); // TODO
            if (!possibleMovesForOpponent.isEmpty()) {
                int moveSelection = random.nextInt(possibleMovesForOpponent.size());
                draughtsEngine.doMove(possibleMovesForOpponent.get(moveSelection));
                draughtsEngine.printChessboard();
            }
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    chessboardPanel.setVisible(false);
                    chessboardPanel.clearPieces();
                    chessboardPanel.addPieces(draughtsEngine.getChessboard().getChessboardTable());
                    chessboardPanel.setVisible(true);
                }
            });

        } else {
            System.err.println("Tried:");
            System.err.println(move);
            System.err.println("Possible:");
            for(Move possible : possibleMoves) {
                System.err.println(possible);
            }
            
            throw new NotValidMoveException();
        }
    }
}
