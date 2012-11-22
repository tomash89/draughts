package pl.edu.agh.draughts.gui;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JLabel;

import pl.edu.agh.draughts.game.elements.ChessboardPosition;
import pl.edu.agh.draughts.game.elements.PieceColor;

public abstract class PieceLabel extends JLabel implements Icon {
    private static final long serialVersionUID = 1L;
    private int column;
    private int row;
    
    protected final int size;
    protected final PieceColor color;
    protected final Color realColor;
    
    protected PieceLabel(PieceColor color, int column, int row, int size) {
        this.column = column;
        this.row = row;
        this.size = size;
        this.color = color;
        this.realColor = color == PieceColor.BLACK ? Color.BLACK : Color.WHITE;
    }

    // basic logic
    
    public int getColumn() {
        return column;
    }
    
    public int getRow() {
        return row;
    }

    public ChessboardPosition getChessboardPosition() {
        return new ChessboardPosition(row, column);
    }
    
    // Icon

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    public PieceColor getColor() {
        return color;
    }
    
}
