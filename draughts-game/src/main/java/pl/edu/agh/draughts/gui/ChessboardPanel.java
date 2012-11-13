package pl.edu.agh.draughts.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.Piece;

public class ChessboardPanel extends JPanel implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    
    private final int rows;
    private final int sizeForEachCell;
    private final DraughtsEngine draughtsEngine;
    
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private ChessboardCell highlightedCell;
    
    public ChessboardPanel(int rows, int sizeForEachCell, DraughtsEngine draughtsEngine) {
        super();
        this.rows = rows;
        this.sizeForEachCell = sizeForEachCell;
        this.draughtsEngine = draughtsEngine;
        setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            ChessboardCell square = new ChessboardCell(new BorderLayout(), i / 8, i % 8, CellColor.values()[((i/8)%2+(i%2))%2]);
            add(square);
        }
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void addPieces(Piece[][] pieces) {
        for(int i=0; i<rows; i++) {
            for(int j=0; j<rows; j++) {
                Piece piece = pieces[i][j];
                if(piece != null) {
                    JLabel pieceLabel = new JLabel(new PawnIcon(sizeForEachCell, piece.getPieceColor()));
                    JPanel panel = (JPanel) getComponent(i*rows+j);
                    panel.add(pieceLabel);
                }
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(rows*sizeForEachCell, rows*sizeForEachCell);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            return;
        }
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        ((JLayeredPane) getParent().getParent()).add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    // Move the chess piece around

    @Override
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        Component c = findComponentAt(me.getX(), me.getY());
        if(c instanceof ChessboardCell) {
            if(highlightedCell != null) {
                highlightedCell.setHighlighted(false);
            }
            ChessboardCell newHighlightedCell = (ChessboardCell) c;
            ((ChessboardCell) c).setHighlighted(true);
            highlightedCell = newHighlightedCell;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    // Drop the chess piece back onto the chess board

    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        PawnIcon pawn = (PawnIcon)chessPiece.getIcon();
        List<Move> possibleMoves = draughtsEngine.getPossibleMoves(pawn.getColor());

        if(highlightedCell != null) {
            highlightedCell.setHighlighted(false);
            highlightedCell = null;
        }
        
        chessPiece.setVisible(false);
        Component c = findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(chessPiece);
        } else {
            Container parent = (Container) c;
            parent.add(chessPiece);
        }

        chessPiece.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<rows; i++) {
            for(int j=0; j<rows; j++) {
                g.setColor((i%2+j%2)%2 == 0 ? Color.WHITE : Color.DARK_GRAY);
                g.fillRect(i*sizeForEachCell, j*sizeForEachCell, sizeForEachCell, sizeForEachCell);
            }
        }
        
    } */ 
}
