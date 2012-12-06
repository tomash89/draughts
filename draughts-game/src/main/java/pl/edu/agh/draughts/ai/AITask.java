package pl.edu.agh.draughts.ai;

import pl.edu.agh.draughts.game.DraughtsEngine;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class AITask implements Runnable {

    private final AIPlayer aiPlayer;
    private final Chessboard chessboard;
    private final PieceColor pieceColor;
    private final DraughtsEngine draughtsEngine;

    public AITask(AIPlayer aiPlayer, Chessboard chessboard, PieceColor pieceColor, DraughtsEngine draughtsEngine) {
        this.aiPlayer = aiPlayer;
        this.chessboard = chessboard;
        this.pieceColor = pieceColor;
        this.draughtsEngine = draughtsEngine;
    }

    @Override
    public void run() {
        draughtsEngine.doMove(aiPlayer.suggestMove(chessboard, pieceColor));
    }

}
