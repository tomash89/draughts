package pl.edu.agh.draughts.gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ChessboardCell extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int column;
    private final int row;

    public ChessboardCell(LayoutManager layout, int column, int row) {
        super(layout);
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
