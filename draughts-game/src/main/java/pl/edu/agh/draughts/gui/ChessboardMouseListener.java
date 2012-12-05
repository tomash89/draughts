package pl.edu.agh.draughts.gui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLayeredPane;

public class ChessboardMouseListener extends MouseAdapter implements MouseMotionListener {

    private ChessboardPanel chessboardPanel;
    private ChessboardControler chessboardControler;

    private PieceLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private ChessboardCell highlightedCell;

    public ChessboardPanel getChessboardPanel() {
        return chessboardPanel;
    }

    public void setChessboardPanel(ChessboardPanel chessboardPanel) {
        this.chessboardPanel = chessboardPanel;
    }

    public ChessboardControler getChessboardControler() {
        return chessboardControler;
    }

    public void setChessboardControler(ChessboardControler chessboardControler) {
        this.chessboardControler = chessboardControler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessboardPanel.findComponentAt(e.getX(), e.getY());

        if (c instanceof PieceLabel) {
            Point parentLocation = c.getParent().getLocation();
            xAdjustment = parentLocation.x - e.getX();
            yAdjustment = parentLocation.y - e.getY();
            chessPiece = (PieceLabel) c;
            chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
            ((JLayeredPane) chessboardPanel.getParent().getParent()).add(chessPiece, JLayeredPane.DRAG_LAYER);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }

        Component c = chessboardPanel.findComponentAt(me.getX(), me.getY());
        if (c instanceof PieceLabel) {
            c = c.getParent();
        }
        if (c instanceof ChessboardCell) {
            if (highlightedCell != null) {
                highlightedCell.setHighlighted(false);
            }
            ChessboardCell newHighlightedCell = (ChessboardCell) c;
            ((ChessboardCell) c).setHighlighted(true);
            highlightedCell = newHighlightedCell;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        if (highlightedCell != null) {
            highlightedCell.setHighlighted(false);
            highlightedCell = null;
        }

        Component c = chessboardPanel.findComponentAt(e.getX(), e.getY());

        boolean rollback = false;
        if (c instanceof ChessboardCell) {
            ChessboardCell dropCell = (ChessboardCell) c;

            ChessboardAction returnedAction = chessboardControler.testMove(chessPiece, dropCell);
            if (returnedAction == ChessboardAction.ROLLBACK) {
                System.err.println("Not a valid move dude!");
                System.err.println(String.format("%s to %s", chessPiece.getChessboardPosition(),
                        dropCell.getChessboardPosition()));
                rollback = true;
            } else if (returnedAction == ChessboardAction.MOVE_TEMPORARILY) {
                chessPiece.setVisible(false);
                dropCell.setPiece(chessPiece);
                chessPiece.setVisible(true);
            }
        } else {
            rollback = true;
        }
        if (rollback) {
            chessPiece.setVisible(false);
            ChessboardCell rollbackCell = chessboardPanel
                    .getChessboardCell(chessPiece.getRow(), chessPiece.getColumn());
            rollbackCell.setPiece(chessPiece);
            chessPiece.setVisible(true);
        } else {
            ((JLayeredPane) chessboardPanel.getParent().getParent()).remove(chessPiece);
        }

    }
}
