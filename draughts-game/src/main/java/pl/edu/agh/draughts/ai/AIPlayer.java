package pl.edu.agh.draughts.ai;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.elements.Player;

public interface AIPlayer extends Player {
    Move suggestMove(Chessboard chessboard, PieceColor pieceColor);
}
