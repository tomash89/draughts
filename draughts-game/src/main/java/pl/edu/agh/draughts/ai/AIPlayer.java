package pl.edu.agh.draughts.ai;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public interface AIPlayer {
    Move suggestMove(Chessboard chessboard, PieceColor pieceColor);
}
