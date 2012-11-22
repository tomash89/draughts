package pl.edu.agh.draughts.gui;

import java.awt.Component;
import java.awt.Graphics;

import pl.edu.agh.draughts.game.elements.PieceColor;

public class PawnLabel extends PieceLabel {

    private static final long serialVersionUID = 1L;

    public PawnLabel(PieceColor color, int column, int row, int size) {
        super(color, column, row, size);
        setIcon(this);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(realColor);
        g.fillOval(0, 0, size, size);
    }
    
}
