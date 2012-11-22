package pl.edu.agh.draughts.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import pl.edu.agh.draughts.game.elements.PieceColor;

public class KingLabel extends PieceLabel {

    private static final long serialVersionUID = 1L;
    private static final int INNER_CIRCLE_DIFF = 5;

    public KingLabel(PieceColor color, int column, int row, int size) {
        super(color, column, row, size);
        setIcon(this);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(realColor);
        g.fillOval(0, 0, size, size);
        g.setColor(Color.GRAY);
        g.drawOval(INNER_CIRCLE_DIFF, INNER_CIRCLE_DIFF, size - 2*INNER_CIRCLE_DIFF, size - 2*INNER_CIRCLE_DIFF);
    }
 
}
