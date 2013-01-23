package pl.edu.agh.draughts.game;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import pl.edu.agh.draughts.ai.AIPlayer;
import pl.edu.agh.draughts.ai.AITask;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.GameResult;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class DraughtsEngine extends Observable {

    private Chessboard chessboard;
    private PieceColor currentPlayerColor = PieceColor.WHITE;
    private AIPlayer whitePlayer;
    private AIPlayer blackPlayer;
    private GameResult gameResult;
    private final ScheduledThreadPoolExecutor aiExecutor = new ScheduledThreadPoolExecutor(1);

    public void initializeGame() {
        this.chessboard = new Chessboard();
        System.out.println(this.chessboard);
        currentPlayerColor = PieceColor.WHITE;
        gameResult = GameResult.PENDING;
    }

    public List<Move> getPossibleMoves(PieceColor pieceColor) {
        return chessboard.getPossibleMoves(pieceColor);
    }
    
    public GameResult testGame() {
        Move move = null;
        while(gameResult == GameResult.PENDING) {
            move = whitePlayer.suggestMove(chessboard, PieceColor.WHITE);
            if(move == null) {
                gameResult = GameResult.BLACK_WON;
                break;
            }
            move.doMove(chessboard);
            move = blackPlayer.suggestMove(chessboard, PieceColor.BLACK);
            if(move == null) {
                gameResult = GameResult.WHITE_WON;
                break;
            }
            move.doMove(chessboard);
        }
        System.out.println(this.chessboard);
        return gameResult;
    }

    public void tryToMoveAutomatically() {
        AIPlayer currentPlayer;
        if (currentPlayerColor == PieceColor.WHITE) {
            currentPlayer = whitePlayer;
        } else {
            currentPlayer = blackPlayer;
        }
        if (currentPlayer != null) {
            aiExecutor.execute(new AITask(currentPlayer, chessboard, currentPlayerColor, this));
        }
    }

    public void doMove(Move move) {
        if (move != null) {
            move.doMove(this.chessboard);
            this.currentPlayerColor = currentPlayerColor.getOpponentColor();
            setChanged();
            notifyObservers();
            tryToMoveAutomatically();
        } else {
            if (currentPlayerColor == PieceColor.BLACK) {
                gameResult = GameResult.WHITE_WON;
                System.out.println("White won");
            } else {
                gameResult = GameResult.BLACK_WON;
                System.out.println("Black won");
            }
            setChanged();
            notifyObservers(gameResult);
        }
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public AIPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(AIPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public AIPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(AIPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public void printChessboard() {
        System.out.println(this.chessboard);
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
}
