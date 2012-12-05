package pl.edu.agh.draughts.game;

import java.util.List;
import java.util.Observable;

import pl.edu.agh.draughts.ai.AIPlayer;
import pl.edu.agh.draughts.game.elements.Chessboard;
import pl.edu.agh.draughts.game.elements.Move;
import pl.edu.agh.draughts.game.elements.PieceColor;

public class DraughtsEngine extends Observable {

	private Chessboard chessboard;
    private PieceColor currentPlayerColor = PieceColor.WHITE;
    private AIPlayer whitePlayer;
    private AIPlayer blackPlayer;

	public void initializeGame() {
		this.chessboard = new Chessboard();
		System.out.println(this.chessboard);
        currentPlayerColor = PieceColor.WHITE;
	}

	public List<Move> getPossibleMoves(PieceColor pieceColor) {
        return chessboard.getPossibleMoves(pieceColor);
	}

    public void tryToMoveOpponent() {
        AIPlayer currentPlayer;
        if (currentPlayerColor == PieceColor.WHITE) {
            currentPlayer = whitePlayer;
        } else {
            currentPlayer = blackPlayer;
        }
        if (currentPlayer != null) {
            Move move = currentPlayer.suggestMove(chessboard, currentPlayerColor);
            if (move != null) {
                doMove(move);
            }
        }
    }

	public void doMove(Move move) {
		move.doMove(this.chessboard);
        this.currentPlayerColor = currentPlayerColor.getOpponentColor();
        setChanged();
        notifyObservers();
        tryToMoveOpponent();
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
