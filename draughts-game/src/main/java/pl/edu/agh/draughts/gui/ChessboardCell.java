package pl.edu.agh.draughts.gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ChessboardCell extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int column;
    private final int row;
    private final CellColor color;
    
    private boolean highlighted;

    public ChessboardCell(LayoutManager layout, int column, int row, CellColor color) {
        super(layout);
        this.column = column;
        this.row = row;
        this.color = color;
        this.setBackground(color.getColor());
    }

    public CellColor getColor() {
        return color;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        if(highlighted) {
            setBackground(color.getHighlightedColor());
        } else {
            setBackground(color.getColor());
        }
    }

    public boolean isHighlighted() {
        return highlighted;
    }
}
