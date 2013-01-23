package pl.edu.agh.draughts.game.elements;

public enum GameResult {
    PENDING("Game in progress"), DRAW("Draw!"), BLACK_WON("Black player has won!"), WHITE_WON("White player has won!");
    
    private String description;
       
    public String getDescription() {
        return description;
    }

    private GameResult(String description) {
        this.description = description;
    }
}
