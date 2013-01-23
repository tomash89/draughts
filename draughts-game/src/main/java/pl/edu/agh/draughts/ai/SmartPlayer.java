package pl.edu.agh.draughts.ai;

import java.util.List;

import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;
import pl.edu.agh.draughts.game.exceptions.InvalidPieceException;

public class SmartPlayer implements AIPlayer {

    private final ParametersVector parametersVector = ParametersVector.getAllParametersVector();
    private final int depth;

    public SmartPlayer(int depth) {
        this.depth = depth;
    }

    @Override
    public Move suggestMove(Chessboard chessboard, PieceColor pieceColor) {
        
//        try {
//            Thread.sleep(delay);
//        } catch (InterruptedException e) {
//            e.printStackTrace(System.err);
//        }
        
        return MinMax.getMinMaxMove(chessboard, pieceColor, parametersVector, depth);
    }

    @Override
    public boolean isUserControllable() {
        return false;
    }
}
