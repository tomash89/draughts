package pl.edu.agh.draughts.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.ChessboardPosition;
import pl.edu.agh.draughts.game.elements.King;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.Pawn;
import pl.edu.agh.draughts.game.elements.Piece;

public class ChessboardControler implements Observer {

    private ChessboardPanel chessboardPanel;
    private DraughtsEngine draughtsEngine;
    private Move currentMove;

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
        draughtsEngine.deleteObserver(this);
        this.draughtsEngine = draughtsEngine;
        draughtsEngine.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                chessboardPanel.setVisible(false);
                chessboardPanel.clearPieces();
                chessboardPanel.addPieces(draughtsEngine.getChessboard().getChessboardTable());
                chessboardPanel.setVisible(true);
            }
        });
    }

    public void doMove(Move move) {
        draughtsEngine.doMove(move);
        //draughtsEngine.printChessboard();
        currentMove = null;
    }

    public ChessboardAction testMove(PieceLabel piece, ChessboardCell cell) {
        List<Move> possibleMoves = draughtsEngine.getPossibleMoves(piece.getColor());
        createOrUpdadeCurrentMove(piece, cell);
        if (currentMove == null) {
            return ChessboardAction.ROLLBACK; // invalid part of the move, rollback everything
        }
        if (possibleMoves.contains(currentMove)) {
            doMove(currentMove);
            return ChessboardAction.DO_NOTHING;
        } else {
            for (Move possibleMove : possibleMoves) {
                if (possibleMove.isValidMovePrefix(currentMove)) {
                    return ChessboardAction.MOVE_TEMPORARILY;
                }
            }
            System.err.println("Tried:");
            System.err.println(currentMove);
            System.err.println("Possible:");
            for(Move possible : possibleMoves) {
                System.err.println(possible);
            }
            currentMove = null;
            return ChessboardAction.ROLLBACK;
        }
    }

    private void createOrUpdadeCurrentMove(PieceLabel piece, ChessboardCell cell) {
        if (currentMove == null) {
            currentMove = createMove(piece, cell);
        } else {
            if (piece.getColumn() == currentMove.getPieceColumn() && piece.getRow() == currentMove.getPieceRow()) {
                currentMove.addNextStep(cell.getChessboardPosition());
            } else {
                currentMove = null;
            }
        }
    }

    private Move createMove(PieceLabel piece, ChessboardCell cell) {
        Piece logicPiece = null;
        if (piece instanceof PawnLabel) {
            logicPiece = new Pawn(piece.getColor());
        } else {
            logicPiece = new King(piece.getColor());
        }
        Move move = new Move(logicPiece, piece.getRow(), piece.getColumn());
        LinkedList<ChessboardPosition> steps = new LinkedList<ChessboardPosition>();
        steps.add(cell.getChessboardPosition());
        move.setSteps(steps);
        return move;
    }
}
