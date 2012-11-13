package pl.edu.agh.draughts.gui;

import java.awt.Color;

public enum CellColor {
    FAIR(new Color(255f/255f, 206f/255f, 158f/255f)),
    DARK(new Color(209f/255f, 139f/255f, 71f/255f));
    
    private Color color;
    
    private CellColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    public Color getHighlightedColor() {
        return color.darker();
    }
}
