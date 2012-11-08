package pl.edu.agh.draughts.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import pl.edu.agh.draughts.game.elements.PieceColor;

public class PawnIcon implements Icon {
    private final int size;
    private final PieceColor color;
    private final Color realColor;
    
    public PawnIcon(int size, PieceColor color) {
        this.size = size;
        this.color = color;
        this.realColor = color == PieceColor.BLACK ? Color.BLACK : Color.WHITE;
    }
    
    @Override
    public int getIconHeight() {
        return size;
    }
    @Override
    public int getIconWidth() {
        return size;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(realColor);
        g.fillOval(0, 0, size, size);
    }

    public PieceColor getColor() {
        return color;
    }

}
