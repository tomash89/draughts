package pl.edu.agh.draughts.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChessboardPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int rows;
    private final int sizeForEachCell;
    
    public ChessboardPanel(int rows, int sizeForEachCell) {
        super();
        this.rows = rows;
        this.sizeForEachCell = sizeForEachCell;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(rows*sizeForEachCell, rows*sizeForEachCell);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<rows; i++) {
            for(int j=0; j<rows; j++) {
                g.setColor((i%2+j%2)%2 == 0 ? Color.WHITE : Color.DARK_GRAY);
                g.fillRect(i*sizeForEachCell, j*sizeForEachCell, sizeForEachCell, sizeForEachCell);
            }
        }
        
    }  
}
